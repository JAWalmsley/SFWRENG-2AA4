package ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Colour;

public class LowMoistureHighElev extends LandTile {
    public LowMoistureHighElev(Tile t, String biome) {
        super(t);

        switch (biome) {
            case "Grassland":
                this.colour = new Colour(200, 170, 50);
                break;
            case "Tundra":
                this.colour = new Colour(220, 250, 250);
                break;
            case "Desert":
                this.colour = new Colour(250, 250, 100);
                break;
        }
    }
}
