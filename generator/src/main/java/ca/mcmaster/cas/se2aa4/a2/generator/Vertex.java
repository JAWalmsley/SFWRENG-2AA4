package ca.mcmaster.cas.se2aa4.a2.generator;

public class Vertex {
    private float x;
    private float y;
    private int[] colour;

    public Vertex(float x, float y) {
        this.x = x;
        this.y = y;
        colour = new int[] { 0, 0, 0 };
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

    /**
     * Checks if this vertex is equal to another vertex at given precision level
     * 
     * @param v         The vertex to compare to
     * @param precision The digits after the decimal place to round to
     * @return boolean True if equal, false otherwise
     */
    public boolean isEqual(Vertex v, int precision) {
        // Rounding by multiplying by 10^digits, then rounding, then dividing again
        // It's weird but floats don't round in Java sooooo
        float factor = (float) Math.pow(10, precision);
        float roundedX = Math.round(this.x * (factor)) / (factor);
        float roundedY = Math.round(this.y * (factor)) / (factor);
        float roundedVX = Math.round(v.getX() * (factor)) / (factor);
        float roundedVY = Math.round(v.getY() * (factor)) / (factor);
        
        return roundedX == roundedVX && roundedY == roundedVY;
    }

    public void setColour(int[] colourToSet) {
        colour = colourToSet;
    }

}