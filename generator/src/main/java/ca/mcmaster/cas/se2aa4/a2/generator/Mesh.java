package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.ArrayList;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

public class Mesh {
    private ArrayList<Segment> segments;

    public ArrayList<Vertex> vertices = new ArrayList<>();
    public ArrayList<Polygon> polygons = new ArrayList<>();

    public int width;
    public int height;

    public Mesh(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Structs.Mesh getIOMesh() {
        ArrayList<Structs.Vertex> IOVertices = new ArrayList<>();
        ArrayList<Structs.Segment> IOSegments = new ArrayList<>();
        ArrayList<Structs.Polygon> IOPolygons = new ArrayList<>();
        for (Vertex v : vertices) {
            IOVertices.add(Structs.Vertex.newBuilder().setX(v.getX()).setY(v.getY()).build());
        }
        for (Polygon p : polygons) {
            ArrayList<Integer> segmentIdxs = new ArrayList<>();
            for (Segment s : p.getSegments()) {
                IOVertices.add(Structs.Vertex.newBuilder().setX(s.getV1().getX()).setY(s.getV1().getY()).build());
                IOVertices.add(Structs.Vertex.newBuilder().setX(s.getV2().getX()).setY(s.getV2().getY()).build());
                IOSegments.add(Structs.Segment.newBuilder().setV1Idx(IOVertices.size() - 2)
                        .setV2Idx(IOVertices.size() - 1).build());
                segmentIdxs.add(IOSegments.size() - 1);
            }
            IOPolygons.add(Structs.Polygon.newBuilder().addAllSegmentIdxs(segmentIdxs).build());
        }
        return Structs.Mesh.newBuilder().addAllVertices(IOVertices).addAllSegments(IOSegments)
                .addAllPolygons(IOPolygons).build();
    }
}
