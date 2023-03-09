package ca.mcmcaster.cas.se2aa4.a2.island.adt;

import ca.mcmaster.cas.se2aa4.a2.generator.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

public class OceanTile extends Tile {
    public OceanTile(Tile t) {
        super(t);
        this.polygon.setColour(new int[] {235, 255, 0, 255});
    }

}
