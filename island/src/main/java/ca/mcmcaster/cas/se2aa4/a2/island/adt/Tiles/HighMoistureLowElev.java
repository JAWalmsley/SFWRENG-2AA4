package ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Colour;

public class HighMoistureLowElev extends LandTile {
    public HighMoistureLowElev(Tile t, String biome) {
        super(t);
        switch (biome) {
            case "grassland":
                this.colour = new Colour(0, 70, 30);
                break;
            case "tundra":
                this.colour = new Colour(100, 200, 80);
                break;
            case "desert":
                this.colour = new Colour(190, 200, 50);
                break;
        }

    }
}
