package ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Colour;

public class LandTile extends Tile {
    public LandTile(Tile t) {
        super(t);
        this.colour = new Colour(144, 137, 53);
    }
}
