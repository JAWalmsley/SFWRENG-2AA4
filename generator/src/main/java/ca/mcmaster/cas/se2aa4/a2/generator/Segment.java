package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.ArrayList;

public class Segment {
    private Vertex v1;
    private Vertex v2;
    public ArrayList<Integer> index = new ArrayList<>();
    private int[] colour;

    public Segment(Vertex v1, Vertex v2) {
        this.v1 = v1;
        this.v2 = v2;
        this.colour = new int[]{0, 0, 0, 255};
    }

    public Vertex getV1() {
        return v1;
    }

    public Vertex getV2() {
        return v2;
    }

    public int[] getColour() {
        return colour;
    }
    public void setColour(int[] colourToSet) {
        colour = colourToSet;
    }
}