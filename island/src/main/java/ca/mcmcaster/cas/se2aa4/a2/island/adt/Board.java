package ca.mcmcaster.cas.se2aa4.a2.island.adt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.Tile;

public class Board {
    private List<Tile> tiles;
    private Structs.Mesh mesh;
    private int width;
    private int height;
    public Random rand;

    public Board(Structs.Mesh m, long seed) {
        this.mesh = m;
        this.tiles = new ArrayList<Tile>();
        this.createTiles();
        this.setDimensions();
        this.rand = new Random(seed);
    }

    private void createTiles(){
        for (Structs.Polygon p : this.mesh.getPolygonsList()) {
            Structs.Vertex centroid = this.mesh.getVerticesList().get(p.getCentroidIdx());
            this.tiles.add(new Tile(p, (float) centroid.getX(), (float) centroid.getY()));
        }
    }

    private void setDimensions(){
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

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }


    public List<Tile> getNeighbours(Tile t) {
        ArrayList<Tile> n = new ArrayList<Tile>();
        for (int idx : t.getPolygon().getNeighborIdxsList()) {
            n.add(this.tiles.get(idx));
        }
        return n;
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
        // Remove all polygons so that we can read our coloured versions (the data structure
        // is immutable )
        for (int i = this.mesh.getPolygonsCount() - 1; i >= 0; i--) {
            meshBuilder.removePolygons(i);
        }
        for (Tile t : this.tiles) {
            meshBuilder.addPolygons(t.getPolygon());
        }
        MeshFactory factory = new MeshFactory();
        factory.write(meshBuilder.build(), output);
    }
}
