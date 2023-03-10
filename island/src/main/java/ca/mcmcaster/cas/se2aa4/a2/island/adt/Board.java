package ca.mcmcaster.cas.se2aa4.a2.island.adt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

public class Board {
    private List<Tile> tiles;
    private Structs.Mesh mesh;
    private int width;
    private int height;

    public Board(Structs.Mesh m) {
        this.mesh = m;
        this.tiles = new ArrayList<Tile>();

        for (Structs.Polygon p : m.getPolygonsList()) {
            Structs.Vertex centroid = m.getVerticesList().get(p.getCentroidIdx());
            this.tiles.add(new Tile(p, (float) centroid.getX(), (float) centroid.getY()));
        }
    }

    public void addTile(Tile tile) {
        tiles.add(tile);
    }

    public List<Tile> getTiles() {
        return this.tiles;
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

    public void export(String output) throws IOException {
        Structs.Mesh.Builder meshBuilder = Structs.Mesh.newBuilder(this.mesh);
        // Remove all polygons so we can readd our coloured versions (the datastructure
        // is immutable)
        for (int i = 0; i < this.mesh.getPolygonsCount(); i++) {
            meshBuilder.removePolygons(i);
        }
        for (Tile t : this.tiles) {
            meshBuilder.addPolygons(t.getPolygon());
        }
        MeshFactory factory = new MeshFactory();
        factory.write(meshBuilder.build(), output);
    }
}
