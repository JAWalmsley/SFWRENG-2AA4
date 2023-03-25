package ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Colour;

public class LowMoistureTile extends LandTile {
    public LowMoistureTile(Tile t, String biome) {
        super(t);
        this.colour = new Colour(200, 170, 50);
    }
}
