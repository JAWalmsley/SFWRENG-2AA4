package ca.mcmcaster.cas.se2aa4.a2.island.adt;

public class RiverEdge extends Edge {
    public RiverEdge(Edge e) {
        super(e);
        this.colour = new Colour(0, 0, 255);
        this.thickness = 20;
    }
}
