package ca.mcmcaster.cas.se2aa4.a2.island.adt;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;

public class RiverEdge extends Edge {
    public RiverEdge(Segment s) {
        super(s);
        this.colour = new Colour(0, 0, 255);
        this.thickness = 1;
    }
}
