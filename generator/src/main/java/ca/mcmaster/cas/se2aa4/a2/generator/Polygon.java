package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.ArrayList;

public class Polygon {
    private Vertex centroid;
    private ArrayList<Segment> segments = new ArrayList<>();
    public ArrayList<Vertex> verticies = new ArrayList<>();

    public Polygon(Vertex centroid) {
        this.centroid = centroid;
    }

    public void addSegment(Segment seg) {
        if(this.segments.size() == 0) {
            this.segments.add(seg);
            return;
        }
        // Find segment with a matching vertex and add it afterward
        for(int i = 0; i < this.segments.size(); i++) {
            if(this.segments.get(i).getV2() == seg.getV1()) {
                this.segments.add(i+1, seg);
                return;
            }
        }
        this.segments.add(seg);
    }

    public ArrayList<Segment> getSegments() {
        return this.segments;
    }
}
