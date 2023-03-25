package ca.mcmcaster.cas.se2aa4.a2.island.adt;

public class RiverPoint extends Point {
    int thickness;
    public RiverPoint(Point p) {
        super(p);
        this.setColour(new Colour(0, 0, 255));
        this.thickness = 1;
    }

    public int getThickness() {
        return this.thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

}
