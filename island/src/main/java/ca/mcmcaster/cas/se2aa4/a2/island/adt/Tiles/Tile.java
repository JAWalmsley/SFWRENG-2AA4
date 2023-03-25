package ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Colour;

public class Tile {
    Structs.Polygon polygon;
    Colour colour;
    float x;
    float y;
    float elevation;
    Boolean isAquifier = false;

    int moistureLevel = 0;
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

    public void setMoistureLevel(int moistureLevel) {
        this.moistureLevel = moistureLevel;
    }

    public int getMoistureLevel() {
        if (this.moistureLevel>25) {
            this.moistureLevel = 25;
        }
        return this.moistureLevel;
    }

    public void setIsAquifier(Boolean isAquifier) {
        this.isAquifier = isAquifier;
    }

    public Boolean getIsAquifier() {
        return this.isAquifier;
    }

    public Structs.Polygon getPolygon() {
        Polygon.Builder builder = Structs.Polygon.newBuilder(this.polygon);
        if(builder.getPropertiesCount() > 0) {
            builder.removeProperties(0);
        }
        return builder.addProperties(this.colour.toProperty()).build();
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public float getElevation() {
        return this.elevation;
    }
}
