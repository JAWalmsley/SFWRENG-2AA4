package ca.mcmcaster.cas.se2aa4.a2.island.adt;

import ca.mcmaster.cas.se2aa4.a2.generator.Polygon;

public class Tile {
    Polygon polygon;
    public Tile(Polygon p) {
        this.polygon = p;
    }

    public Polygon getPolygon() {
        return polygon;
    }
}
