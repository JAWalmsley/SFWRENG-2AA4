package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.ArrayList;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.triangulate.DelaunayTriangulationBuilder;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

public class Mesh {

    private ArrayList<Vertex> vertices = new ArrayList<>();
    public ArrayList<Polygon> polygons = new ArrayList<>();

    public int width;
    public int height;
    public int rows;
    public int columns;

    private int precision;

    /**
     * Creates a new Mesh object
     * 
     * @param width     The width of the canvas
     * @param height    The height of the canvas
     * @param precision Number of decimal places to use when comparing vertices'
     *                  positions
     */
    public Mesh(int width, int height, int precision) {
        this.width = width;
        this.height = height;
        this.precision = precision;
    }

    /**
     * Adds a vertex to the mesh if it is not already present
     * 
     * @param v The vertex to add
     */
    public void addVertex(Vertex v) {
        for (Vertex vert : this.vertices) {
            if (vert.isEqual(v, this.precision)) {
                return;
            }
        }
        this.vertices.add(v);
    }

    public Vertex getVertex(int index) {
        return this.vertices.get(index);
    }

    /**
     * Converts this Mesh object to the Structs.Mesh object used for IO
     * 
     * @return Structs.Mesh object
     */
    public Structs.Mesh getIOMesh() {
        ArrayList<Structs.Vertex> IOVertices = new ArrayList<>();
        ArrayList<Structs.Segment> IOSegments = new ArrayList<>();
        ArrayList<Structs.Polygon> IOPolygons = new ArrayList<>();
        for (Vertex v : vertices) {
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
            
            ArrayList<Integer> neighbourIdxs = new ArrayList<>();
            for(Polygon n : p.getNeighbours()) {
                neighbourIdxs.add(polygons.indexOf(n));
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
                    .addAllNeighborIdxs(neighbourIdxs)
                    .build());
        }
        return Structs.Mesh.newBuilder()
                .addAllVertices(IOVertices)
                .addAllSegments(IOSegments)
                .addAllPolygons(IOPolygons)
                .build();
    }

    public void calculateNeighbours() {
        ArrayList<Coordinate> coords = new ArrayList<Coordinate>();
        for (Polygon p : polygons) {
            Vertex c = p.getCentroid();
            coords.add(new Coordinate((double) c.getX(), (double) c.getY()));
        }
        GeometryFactory geometryFactory = new GeometryFactory();

        DelaunayTriangulationBuilder builder = new DelaunayTriangulationBuilder();
        builder.setSites(coords);
        Geometry edges = builder.getEdges(geometryFactory);
        GeometryCollection edgeCollection = (GeometryCollection) edges;
        for (int i = 0; i < edgeCollection.getNumGeometries(); i++) {
            Coordinate[] edgeCoords = edgeCollection.getGeometryN(i).getCoordinates();
            Vertex c1 = new Vertex( (float)edgeCoords[0].x, (float) edgeCoords[0].y);
            Vertex c2 = new Vertex((float) edgeCoords[1].x, (float) edgeCoords[1].y);
            getPolyByCentroid(c1).addNeighbour(getPolyByCentroid(c2));
            getPolyByCentroid(c2).addNeighbour(getPolyByCentroid(c1));
        }
        return;
    }

    public Polygon getPolyByCentroid(Vertex v) {
        for (Polygon p : polygons) {
            if (p.getCentroid().isEqual(v, this.precision)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Converts a colour array to a Property object
     * 
     * @param colour array in order [red, green, blue]
     * @return Property The colour property
     */
    private static Property colourToProperty(int[] colour) {
        String colorCode = colour[0] + "," + colour[1] + "," + colour[2] + ',' + colour[3];
        Property colourProp = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        return colourProp;
    }
}
