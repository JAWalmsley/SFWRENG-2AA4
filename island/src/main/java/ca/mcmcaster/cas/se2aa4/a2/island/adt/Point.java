package ca.mcmcaster.cas.se2aa4.a2.island.adt;

import java.util.Objects;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

public class Point {
    private Structs.Vertex vertex;
    
    private int elevation;
    public Point(Structs.Vertex v) {
        this.vertex = v;
        this.elevation = 0;
    }

    public Point(Point p) {
        this.vertex = p.vertex;
        this.elevation = p.elevation;
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
