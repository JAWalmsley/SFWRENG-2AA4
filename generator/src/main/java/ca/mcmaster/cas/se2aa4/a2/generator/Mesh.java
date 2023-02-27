package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.ArrayList;

import org.locationtech.jts.geom.Coordinate;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

public class Mesh {

    private ArrayList<Vertex> vertices = new ArrayList<>();
    private ArrayList<Coordinate> coordinates = new ArrayList<>();
    public ArrayList<Polygon> polygons = new ArrayList<>();

    public int width;
    public int height;
    public int rows;
    public int columns;
    
    private int precision;

    /**
     * Creates a new Mesh object
     * @param width The width of the canvas
     * @param height The height of the canvas
     * @param precision Number of decimal places to use when comparing vertices' positions
     */
    public Mesh(int width, int height, int precision) {
        this.width = width;
        this.height = height;
        this.precision = precision;
    }

    
    /** 
     * Adds a vertex to the mesh if it is not already present
     * @param v The vertex to add
     */
    public void addVertex(Vertex v) {
        for(Vertex vert : this.vertices) {
            if(vert.isEqual(v, this.precision)) {
                return;
            }
        }
        this.vertices.add(v);
    }

    public Vertex getVertex(int index) {
        return this.vertices.get(index);
    }
    
    public ArrayList<Vertex> getVertices() {
        return this.vertices;
    }

    public void createCords(){ 
        for(Vertex v : this.vertices){
            Coordinate c = new Coordinate(v.getX(), v.getY());
            this.coordinates.add(c);
        }
    }

    public ArrayList<Coordinate> getCoordinates(){
        return this.coordinates;
    }

    /**
     * Converts this Mesh object to the Structs.Mesh object used for IO
     * @return Structs.Mesh object
     */
    public Structs.Mesh getIOMesh() {
        ArrayList<Structs.Vertex> IOVertices = new ArrayList<>();
        ArrayList<Structs.Segment> IOSegments = new ArrayList<>();
        ArrayList<Structs.Polygon> IOPolygons = new ArrayList<>();
        for(Vertex v : vertices) {
            Property vColour = colourToProperty(v.getColour());
            IOVertices.add(Structs.Vertex.newBuilder()
                    .setX(v.getX())
                    .setY(v.getY())
                    .addProperties(vColour)
                    .build());
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
            IOVertices.add(Structs.Vertex.newBuilder()
                    .setX(p.getCentroid().getX())
                    .setY(p.getCentroid().getY())
                    .addProperties(colourToProperty(p.getCentroid().getColour()))
                    .build());
            Property pColour = colourToProperty(p.getColour());
            IOPolygons.add(Structs.Polygon.newBuilder()
                    .setCentroidIdx(IOVertices.size() - 1)
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

    
    /** 
     * Converts a colour array to a Property object
     * @param colour array in order [red, green, blue]
     * @return Property The colour property
     */
    private static Property colourToProperty(int[] colour) {
        String colorCode = colour[0] + "," + colour[1] + "," + colour[2] + ',' + colour[3];
        Property colourProp = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        return colourProp;
    }
}
