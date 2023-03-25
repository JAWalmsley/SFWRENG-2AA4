package ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Colour;

public class HighMoistureHighElev extends LandTile {
    public HighMoistureHighElev(Tile t, String biome) {
        super(t);

        switch (biome) {
            case "Grassland":
                this.colour = new Colour(144, 137, 53);
                break;
            case "Tundra":
                this.colour = new Colour(70, 160, 240);
                break;
            case "Desert":
                this.colour = new Colour(200, 100, 15);
                break;
        }
    }
}
