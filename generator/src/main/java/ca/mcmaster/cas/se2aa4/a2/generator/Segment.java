package ca.mcmaster.cas.se2aa4.a2.generator;

public class Segment {
    private Vertex v1;
    private Vertex v2;

    public Segment(Vertex v1, Vertex v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Vertex getV1() {
        return v1;
    }

    public Vertex getV2() {
        return v2;
    }
}