package ca.mcmcaster.cas.se2aa4.a2.island.adt;

public class LakeTile extends Tile {
    public LakeTile(Tile t) {
        super(t);
        this.colour = new Colour(235, 255, 0);
    }
}
