package ca.mcmcaster.cas.se2aa4.a2.island.adt;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.generator.Polygon;

public class Tile {
    Polygon polygon;
    List<Tile> neighbours;
    public Tile(Polygon p) {
        this.polygon = p;
        this.neighbours = new ArrayList<Tile>();
    }

    public Tile(Tile t) {
        this.polygon = t.polygon;
        this.neighbours = t.neighbours;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public float getX() {
        return this.polygon.getCentroid().getX();
    }

    public float getY() {
        return this.polygon.getCentroid().getY();
    }

    public void addNeighbour(Tile t) {
        this.neighbours.add(t);
    }

    public List<Tile> getNeighbours() {
        return this.neighbours;
    }
}
