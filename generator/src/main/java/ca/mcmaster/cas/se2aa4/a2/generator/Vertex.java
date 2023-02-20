package ca.mcmaster.cas.se2aa4.a2.generator;


public class Vertex {
    private float x;
    private float y;
    private int[] colour;

    public Vertex(float x, float y) {
        this.x = x;
        this.y = y;
        colour = new int[]{0,0,0};
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }
    public int[] getColour() {
        return colour;
    }
    public void setColour(int[] colourToSet) {
        colour = colourToSet;
    }

}