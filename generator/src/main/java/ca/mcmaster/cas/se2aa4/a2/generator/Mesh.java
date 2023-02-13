package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.ArrayList;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

public class Mesh {
    private ArrayList<Segment> segments;

    public ArrayList<Vertex> vertices = new ArrayList<>();
    public ArrayList<Polygon> polygons = new ArrayList<>();

    public int width;
    public int height;
    public int rows;
    public int columns;

    public Mesh(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Structs.Mesh getIOMesh() {
        ArrayList<Structs.Vertex> IOVertices = new ArrayList<>();
        ArrayList<Structs.Segment> IOSegments = new ArrayList<>();
        ArrayList<Structs.Polygon> IOPolygons = new ArrayList<>();
        for (Vertex v : vertices) {
            IOVertices.add(Structs.Vertex.newBuilder().setX(v.getX()).setY(v.getY())
                    .addProperties(colourToProperty(v.getColour())).build());
        }
        for (Polygon p : polygons) {
            ArrayList<Integer> segmentIdxs = new ArrayList<>();
            for (Segment s : p.getSegments()) {
                Property v1Colour = colourToProperty(s.getV1().getColour());
                Property v2Colour = colourToProperty(s.getV2().getColour());
                IOVertices.add(Structs.Vertex.newBuilder()
                        .setX(s.getV1().getX())
                        .setY(s.getV1().getY())
                        .addProperties(v1Colour)
                        .build());
                IOVertices.add(Structs.Vertex.newBuilder()
                        .setX(s.getV2().getX())
                        .setY(s.getV2().getY())
                        .addProperties(v2Colour)
                        .build());

                Property sColour = colourToProperty(s.getColour());
                IOSegments.add(Structs.Segment.newBuilder()
                        .setV1Idx(IOVertices.size() - 2)
                        .setV2Idx(IOVertices.size() - 1)
                        .addProperties(sColour)
                        .build());
                segmentIdxs.add(IOSegments.size() - 1);
            }
            Property pColour = colourToProperty(p.getColour());
            IOPolygons.add(Structs.Polygon.newBuilder()
                    .addAllSegmentIdxs(segmentIdxs)
                    .addProperties(pColour)
                    .build());
        }
        return Structs.Mesh.newBuilder()
                .addAllVertices(IOVertices)
                .addAllSegments(IOSegments)
                .addAllPolygons(IOPolygons)
                .build();
    }

    private static Property colourToProperty(int[] colour) {
        String colorCode = colour[0] + "," + colour[1] + "," + colour[2];
        Property colourProp = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        return colourProp;
    }
}
