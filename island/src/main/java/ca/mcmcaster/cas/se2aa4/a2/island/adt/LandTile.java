package ca.mcmcaster.cas.se2aa4.a2.island.adt;

import ca.mcmaster.cas.se2aa4.a2.generator.Polygon;

public class LandTile extends Tile {
    public LandTile(Polygon p) {
        super(p);
        this.polygon.setColour(new int[] {235, 255, 0, 255});
    }
}
