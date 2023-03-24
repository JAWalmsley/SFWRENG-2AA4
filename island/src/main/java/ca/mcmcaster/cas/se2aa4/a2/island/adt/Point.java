package ca.mcmcaster.cas.se2aa4.a2.island.adt;

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

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public int getElevation() {
        return this.elevation;
    }
}
