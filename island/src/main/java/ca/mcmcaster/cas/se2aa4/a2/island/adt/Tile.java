package ca.mcmcaster.cas.se2aa4.a2.island.adt;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

public class Tile {
    Structs.Polygon polygon;
    Colour colour;
    float x;
    float y;
    public Tile(Structs.Polygon p, float x, float y) {
        this.polygon = p;
        this.colour = new Colour(0, 0, 0);
        this.x = x;
        this.y = y;
    }

    public Tile(Tile t) {
        this.polygon = t.polygon;
        this.colour = t.colour;
        this.x = t.x;
        this.y = t.y;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public Structs.Polygon getPolygon() {
        return Structs.Polygon.newBuilder(this.polygon).removeProperties(0).addProperties(this.colour.toProperty()).build();
    }
}
