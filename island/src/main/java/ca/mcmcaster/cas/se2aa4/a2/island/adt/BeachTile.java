package ca.mcmcaster.cas.se2aa4.a2.island.adt;

import ca.mcmaster.cas.se2aa4.a2.generator.Polygon;

public class BeachTile extends Tile {
    public BeachTile(Polygon p) {
        super(p);
        this.polygon.setColour(new int[] {235, 255, 190, 255});
    }
}
