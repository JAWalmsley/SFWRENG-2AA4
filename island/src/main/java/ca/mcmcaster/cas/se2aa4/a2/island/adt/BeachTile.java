package ca.mcmcaster.cas.se2aa4.a2.island.adt;

import ca.mcmaster.cas.se2aa4.a2.generator.Polygon;

public class BeachTile extends Tile {
    public BeachTile(Tile t) {
        super(t);
        this.polygon.setColour(new int[] {235, 255, 200, 255});
    }
}
