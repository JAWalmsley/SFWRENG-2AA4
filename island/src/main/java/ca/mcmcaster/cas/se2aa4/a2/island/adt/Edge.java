package ca.mcmcaster.cas.se2aa4.a2.island.adt;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;

public class Edge {
    Segment segment;
    Colour colour;
    int thickness;
    boolean road;

    public Edge(Segment s) {
        this.segment = s;
        this.colour = new Colour(0, 255, 0);
        this.thickness = 0;
        this.road = false;
    }

    public Edge(Edge e) {
        this.segment = e.segment;
        this.colour = e.colour;
        this.thickness = e.thickness;
    }

    public boolean isRoad() {
        return road;
    }

    public void setRoad(boolean road) {
        this.road = road;
    }

    public Property thicknessProperty() {
        return Property.newBuilder().setKey("thickness").setValue(String.valueOf(this.thickness)).build();
    }

    public Property roadProperty() {
        return Property.newBuilder().setKey("road").setValue(this.isRoad() ? "true" : "false").build();
    }

    public Segment getSegment() {
        Segment.Builder builder = Segment.newBuilder(this.segment);
        // Remove colour and thickness properties
        while (builder.getPropertiesCount() > 0)
            builder.removeProperties(0);
        return builder.addProperties(this.colour.toProperty()).addProperties(this.thicknessProperty()).addProperties(this.roadProperty()).build();
    }
}
