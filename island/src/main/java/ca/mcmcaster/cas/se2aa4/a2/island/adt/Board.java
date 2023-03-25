package ca.mcmcaster.cas.se2aa4.a2.island.adt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.Tile;

public class Board {
    private List<Tile> tiles;
    private List<Edge> edges;
    private List<Point> points;
    private Structs.Mesh mesh;
    private int width;
    private int height;
    public Random rand;

    public Board(Structs.Mesh m, long seed) {
        this.mesh = m;
        this.tiles = new ArrayList<Tile>();
        this.edges = new ArrayList<Edge>();
        this.points = new ArrayList<Point>();
        this.createTiles();
        this.createPoints();
        this.createEdges();
        this.setDimensions();
        this.rand = new Random(seed);
    }

    private void createTiles() {
        for (Structs.Polygon p : this.mesh.getPolygonsList()) {
            Structs.Vertex centroid = this.mesh.getVerticesList().get(p.getCentroidIdx());
            this.tiles.add(new Tile(p, (float) centroid.getX(), (float) centroid.getY()));
        }
    }

    private void createEdges() {
        for (Structs.Segment s : this.mesh.getSegmentsList()) {
            this.edges.add(new Edge(s));
        }
    }

    private void createPoints() {
        for (Structs.Vertex v : this.mesh.getVerticesList()) {
            this.points.add(new Point(v));
        }
    }

    private void setDimensions() {
        double max_x = Double.MIN_VALUE;
        double max_y = Double.MIN_VALUE;
        for (Structs.Vertex v : this.mesh.getVerticesList()) {
            max_x = (Double.compare(max_x, v.getX()) < 0 ? v.getX() : max_x);
            max_y = (Double.compare(max_y, v.getY()) < 0 ? v.getY() : max_y);
        }
        this.width = (int) Math.ceil(max_x);
        this.height = (int) Math.ceil(max_y);
    }

    public void addTile(Tile tile) {
        tiles.add(tile);
    }

    public void setTile(int index, Tile tile) {
        tiles.set(index, tile);
    }

    public Tile getTile(int index) {
        return this.tiles.get(index);
    }

    public int getNumTiles() {
        return this.tiles.size();
    }

    public List<Point> getPoints() {
        return this.points;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    /**
     * Get tiles bordering the given tile
     * @param t: Tile
     * @return List<Tile>
     */
    public List<Tile> getNeighbourTiles(Tile t) {
        ArrayList<Tile> n = new ArrayList<Tile>();
        for (int idx : t.getPolygon().getNeighborIdxsList()) {
            n.add(this.tiles.get(idx));
        }
        return n;
    }

    /**
     * Get tiles touching the given point
     * @param p: Point
     * @return List<Tile>
     */
    public List<Tile> getNeighbourTiles(Point p) {
        int pIndex = this.points.indexOf(p);
        HashSet<Tile> n = new HashSet<Tile>();
        for(Tile t: this.tiles) {
            for(int idx: t.getPolygon().getSegmentIdxsList()) {
                Structs.Segment s = this.mesh.getSegmentsList().get(idx);
                if(s.getV1Idx() ==  pIndex || s.getV2Idx() == pIndex) {
                    n.add(t);
                }
            }
        }
        return new ArrayList<>(n);
    }

    /**
     * Get points that are adjacent to the given tile (tile's vertices)
     * @param t: Tile
     * @return List<Point>
     */
    public List<Point> getNeighbourPoints(Tile t) {
        Set<Point> n = new HashSet<Point>();
        for (int idx : t.getPolygon().getSegmentIdxsList()) {
            Structs.Segment s = this.mesh.getSegmentsList().get(idx);
            n.add(this.points.get(s.getV1Idx()));
            n.add(this.points.get(s.getV2Idx()));
        }
        return new ArrayList<>(n);
    }

    /**
     * Get points connected to this point by edges
     * @param p: Point
     * @return List<Point>
     */
    public List<Point> getNeighbourPoints(Point p) {
        int pIndex = this.points.indexOf(p);
        Set<Point> n = new HashSet<Point>();
        for(Tile t: this.tiles) {
            for(int idx: t.getPolygon().getSegmentIdxsList()) {
                Structs.Segment s = this.mesh.getSegmentsList().get(idx);
                if(s.getV1Idx() == pIndex) {
                    n.add(this.points.get(s.getV2Idx()));
                } else if(s.getV2Idx() == pIndex) {
                    n.add(this.points.get(s.getV1Idx()));
                }
            }
        }
        return new ArrayList<>(n);
    }

    public List<Integer> getNeighboursID(Tile t) {
        ArrayList<Integer> n = new ArrayList<Integer>();
        for (int idx : t.getPolygon().getNeighborIdxsList()) {
            n.add(idx);
        }
        return n;
    }

    public void export(String output) throws IOException {
        Structs.Mesh.Builder meshBuilder = Structs.Mesh.newBuilder(this.mesh);
        // Remove all polygons so that we can read our coloured versions (the data
        // structure
        // is immutable )
        for (int i = this.mesh.getPolygonsCount() - 1; i >= 0; i--) {
            meshBuilder.removePolygons(i);
        }
        for (Tile t : this.tiles) {
            meshBuilder.addPolygons(t.getPolygon());
        }

        // Replace all vertices
        for(int i = this.mesh.getVerticesCount() - 1; i >= 0; i--) {
            meshBuilder.removeVertices(i);
        }
        for(Point p: this.points) {
            meshBuilder.addVertices(p.getVertex());
        }

        //Replace all segments
        for(int i = this.mesh.getSegmentsCount() - 1; i >= 0; i--) {
            meshBuilder.removeSegments(i);
        }
        for(Edge e: this.edges) {
            meshBuilder.addSegments(e.getSegment());
        }
        MeshFactory factory = new MeshFactory();
        factory.write(meshBuilder.build(), output);
    }
}
