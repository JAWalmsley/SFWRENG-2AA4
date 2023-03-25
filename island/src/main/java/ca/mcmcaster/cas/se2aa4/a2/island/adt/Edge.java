package ca.mcmcaster.cas.se2aa4.a2.island.adt;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;

public class Edge {
    private Segment segment;
    private Colour colour;

    public Edge(Segment s) {
        this.segment = s;
        this.colour = new Colour(0, 255, 0);
    }

    public Edge(Edge e) {
        this.segment = e.segment;
        this.colour = e.colour;
    }

    public Segment getSegment(){
        Segment.Builder builder = Segment.newBuilder(this.segment);
        if (builder.getPropertiesCount() > 0) {
            builder.removeProperties(0);
        }
        return builder.addProperties(this.colour.toProperty()).build();
    }
}
