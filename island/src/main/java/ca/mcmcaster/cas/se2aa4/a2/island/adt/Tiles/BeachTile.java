package ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Colour;

public class BeachTile extends Tile {
    public BeachTile(Tile t) {
        super(t);
        this.colour = new Colour(255, 255, 217);
    }
}
