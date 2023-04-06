package ca.mcmcaster.cas.se2aa4.a2.island.adt;

import java.util.Objects;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

public class Point {
    Structs.Vertex vertex;
    int elevation;
    Colour colour;
    boolean city;

    public Point(Structs.Vertex v) {
        this.vertex = v;
        this.elevation = 0;
        this.colour = new Colour(0, 0, 0);
        this.city = false;
    }

    public Point(Point p) {
        this.vertex = p.vertex;
        this.elevation = p.elevation;
        this.colour = p.colour;
    }

    public boolean isCity() {
        return city;
    }

    public void setCity(boolean city) {
        this.city = city;
    }

    public float getX() {
        return (float) this.vertex.getX();
    }

    public float getY() {
        return (float) this.vertex.getY();
    }

    public void setElevation(int f) {
        this.elevation = f;
    }

    public int getElevation() {
        return this.elevation;
    }

    public Colour getColour() {
        return this.colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public Property cityProperty() {
        return Property.newBuilder().setKey("city").setValue(this.isCity() ? "true" : "false").build();
    }

    public Structs.Vertex getVertex() {
        Structs.Vertex.Builder builder = Structs.Vertex.newBuilder(this.vertex);
        while (builder.getPropertiesCount() > 0)
            builder.removeProperties(0);
        return builder.addProperties(this.colour.toProperty()).addProperties(this.cityProperty()).build();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Point)) {
            return false;
        }
        Point point = (Point) o;
        return Objects.equals(vertex, point.vertex) && elevation == point.elevation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertex, elevation);
    }

}
