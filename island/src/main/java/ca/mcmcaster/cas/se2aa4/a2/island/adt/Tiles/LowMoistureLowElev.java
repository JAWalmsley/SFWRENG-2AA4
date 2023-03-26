package ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Colour;
public class LowMoistureLowElev extends LandTile {
    public LowMoistureLowElev(Tile t, String biome) {
        super(t);

        switch (biome) {
            case "grassland":
                this.colour = new Colour(50, 200, 50);
                break;
            case "tundra":
                this.colour = new Colour(150, 240, 250);
                break;
            case "desert":
                this.colour = new Colour(230, 230, 25);
                break;
        }
    }
}