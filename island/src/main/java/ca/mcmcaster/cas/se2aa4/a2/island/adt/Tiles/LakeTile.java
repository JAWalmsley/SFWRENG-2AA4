package ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Colour;

public class LakeTile extends Tile {
    public LakeTile(Tile t) {
        super(t);
        this.colour = new Colour(4, 100, 151);
    }
}
