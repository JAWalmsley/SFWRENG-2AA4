package ca.mcmcaster.cas.se2aa4.a2.island.adt;

import ca.mcmaster.cas.se2aa4.a2.generator.Polygon;

public class LakeTile extends Tile {
    public LakeTile(Polygon p) {
        super(p);
        this.polygon.setColour(new int[] {80, 80, 255, 255});
    }
}
