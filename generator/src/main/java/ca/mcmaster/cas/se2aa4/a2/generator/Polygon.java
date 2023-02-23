package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.ArrayList;

public class Polygon {
    private Vertex centroid;
    private ArrayList<Segment> segments = new ArrayList<>();
    public ArrayList<Vertex> verticies = new ArrayList<>();
    private int[] colour;


    public Polygon(Vertex centroid) {
        this.centroid = centroid;
        this.colour = new int[] { 0, 0, 0, 255 };
    }

    public void addSegment(Segment seg) {
        if (this.segments.size() == 0) {
            this.segments.add(seg);
            return;
        }
        // Find segment with a matching vertex and add it afterward
        for (int i = 0; i < this.segments.size(); i++) {
            if (this.segments.get(i).getV2() == seg.getV1()) {
                this.segments.add(i + 1, seg);
                return;
            }
        }
        this.segments.add(seg);
    }

    public Vertex getCentroid() {
        return this.centroid;
    }

    public void setCentroid(Vertex v) {
        this.centroid = v;
    }

    public Vertex centerOfMass() {
        float x = 0;
        float y = 0;
        for (Vertex v : verticies) {
            x += v.getX();
            y += v.getY();
        }
        return new Vertex(x / verticies.size(), y / verticies.size());
    }

    public ArrayList<Segment> getSegments() {
        return this.segments;
    }

    public int[] getColour() {
        return colour;
    }

    public void setColour(int[] colourToSet) {
        colour = colourToSet;
    }
}
