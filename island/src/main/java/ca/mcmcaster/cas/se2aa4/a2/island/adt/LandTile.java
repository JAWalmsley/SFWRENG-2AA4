package ca.mcmcaster.cas.se2aa4.a2.island.adt;

import ca.mcmaster.cas.se2aa4.a2.generator.Polygon;

public class LandTile extends Tile {
    public LandTile(Tile t) {
        super(t);
        this.polygon.setColour(new int[] {180, 180, 0, 255});
    }
}
