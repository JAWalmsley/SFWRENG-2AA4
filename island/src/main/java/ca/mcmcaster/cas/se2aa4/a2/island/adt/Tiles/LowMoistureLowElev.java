package ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Colour;
public class LowMoistureLowElev extends LandTile {
    public LowMoistureLowElev(Tile t, String biome) {
        super(t);
        this.colour = new Colour(50, 200, 50);
    }
}