package ca.mcmcaster.cas.se2aa4.a2.island.adt;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.generator.Polygon;

public class Tile {
    Polygon polygon;
    public Tile(Polygon p) {
        this.polygon = p;
    }

    public Tile(Tile t) {
        this.polygon = t.polygon;
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
}
