package ca.mcmcaster.cas.se2aa4.a2.island.adt;

import ca.mcmaster.cas.se2aa4.a2.generator.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

public class OceanTile extends Tile {
    public OceanTile(Polygon p) {
        super(p);
        this.polygon.setColour(new int[] {0, 0, 130, 255});
    }

}
