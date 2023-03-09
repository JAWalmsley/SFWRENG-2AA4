package ca.mcmcaster.cas.se2aa4.a2.island.adt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.generator.Mesh;
import ca.mcmaster.cas.se2aa4.a2.generator.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

public class Board {
    private List<Tile> tiles;
    private int width;
    private int height;
    private int precision;

    public Board(Mesh m) {
        this.tiles = new ArrayList<Tile>();
        this.width = m.width;
        this.height = m.height;
        this.precision = m.precision;

        for (Polygon p : m.polygons) {
            Tile t;
            float x = p.getCentroid().getX();
            if (x < 500) {
                t = new OceanTile(p);
            } else if (x < 1000) {
                t = new BeachTile(p);
            } else if (x > 1300 && x < 1500) {
                t = new LakeTile(p);
            } 
            else {
                t = new LandTile(p);
            }
            tiles.add(t);
        }
    }

    public void addTile(Tile tile) {
        tiles.add(tile);
    }

    public void export(String output) throws IOException {
        Mesh newMesh = new Mesh(this.width, this.height, this.precision);
        for (Tile t : tiles) {
            Polygon p = t.getPolygon();
            newMesh.polygons.add(p);
        }
        Structs.Mesh myMesh = newMesh.getIOMesh();
        MeshFactory factory = new MeshFactory();
        factory.write(myMesh, output);
    }
}
