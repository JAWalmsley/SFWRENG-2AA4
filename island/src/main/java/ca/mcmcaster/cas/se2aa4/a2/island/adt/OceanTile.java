package ca.mcmcaster.cas.se2aa4.a2.island.adt;

public class OceanTile extends Tile {
    public OceanTile(Tile t) {
        super(t);
        this.colour = new Colour(1, 64, 98);
    }
}
