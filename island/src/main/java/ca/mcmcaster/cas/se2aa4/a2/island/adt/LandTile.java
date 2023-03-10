package ca.mcmcaster.cas.se2aa4.a2.island.adt;

public class LandTile extends Tile {
    public LandTile(Tile t) {
        super(t);
        this.colour = new Colour(180, 180, 0);
    }
}
