package ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Colour;

public class HighMoisture extends LandTile {
    public HighMoisture(Tile t, String biome) {
        super(t);
        this.colour = new Colour(0, 70, 30);
    }
}
