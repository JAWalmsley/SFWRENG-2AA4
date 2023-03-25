package ca.mcmcaster.cas.se2aa4.a2.island.adt;

public class RiverPoint extends Point {
    public RiverPoint(Point p) {
        super(p);
        this.setColour(new Colour(0, 0, 255));
    }
}
