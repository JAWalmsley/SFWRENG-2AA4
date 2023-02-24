package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.ArrayList;
import java.util.List;

public class Polygon {
    private Vertex centroid;
    private ArrayList<Segment> segments = new ArrayList<>();
    public ArrayList<Vertex> verticies = new ArrayList<>();
    private int[] colour;
    private ArrayList<Polygon> neighbours;


    public Polygon(Vertex centroid) {
        this.centroid = centroid;
        this.colour = new int[] { 0, 0, 0, 255 };
        this.neighbours = new ArrayList<Polygon>();
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

    public void addNeighbour(Polygon p) {
        if(!this.neighbours.contains(p))
        {
            this.neighbours.add(p);
        }
    }

    public List<Polygon> getNeighbours() {
        return this.neighbours;
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
