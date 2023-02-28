package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.ArrayList;
import java.util.Random;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;

public class GenerateMesh {
    private int numCentroids = 100;

    public void makeRandomVertices(Mesh mesh) {
        Random bag = new Random();
        for (int i = 0; i < numCentroids; i++) {
            float x = bag.nextFloat() * mesh.width;
            float y = bag.nextFloat() * mesh.height;
            Vertex v = new Vertex(x, y);
            mesh.addVertex(v);
        }
    }
    public void setNumPolygons(int numPolygons) {
        this.numCentroids = numPolygons;
    }

    public void makeSquareVertices(Mesh mesh) {
        int square_size = 5;
        // Create all the vertices
        for (int x = 0; x < mesh.width; x += square_size) {
            mesh.columns++;
            for (int y = 0; y < mesh.height; y += square_size) {
                Vertex v1 = new Vertex(x, y);
                mesh.addVertex(v1);
                if (x == 0)
                    mesh.rows++;
            }
        }
    }

    public void makeSquarePolygons(Mesh mesh) {
        // TODO: Create a polygon with the given number of sides
        for (int i = 0; i < mesh.rows - 1; i++) {
            for (int j = 0; j < mesh.columns - 1; j++) {
                Polygon poly = new Polygon(new Vertex(0, 0)); // Centroid currently unused so just set to 0,0
                // Make Square
                Vertex vert1 = mesh.getVertex(i * mesh.rows + j);
                Vertex vert2 = mesh.getVertex(i * mesh.rows + j + mesh.rows);
                Vertex vert3 = mesh.getVertex(i * mesh.rows + j + 1 + mesh.rows);
                Vertex vert4 = mesh.getVertex(i * mesh.rows + j + 1);

                poly.verticies.add(vert1);
                poly.verticies.add(vert2);
                poly.verticies.add(vert3);
                poly.verticies.add(vert4);

                poly.setCentroid(poly.centerOfMass());

                Segment seg1 = new Segment(vert1, vert2);
                Segment seg2 = new Segment(vert2, vert3);
                Segment seg3 = new Segment(vert3, vert4);
                Segment seg4 = new Segment(vert4, vert1);

                poly.addSegment(seg1);
                poly.addSegment(seg2);
                poly.addSegment(seg3);
                poly.addSegment(seg4);

                Random bag = new Random();
                int[] colour = { bag.nextInt(255), bag.nextInt(255), bag.nextInt(255), 130 };
                poly.setColour(colour);

                mesh.polygons.add(poly);
            }
        }
    }

    public void calculateVoronoi(Mesh mesh) {
        mesh.polygons.clear();
        mesh.createCords();

        GeometryFactory fact = new GeometryFactory();

        for (int i = 0; i < mesh.getCoordinates().size(); i++) {
            fact.createPoint(mesh.getCoordinates().get(i));
        }

        VoronoiDiagramBuilder voronoiBuilder = new VoronoiDiagramBuilder();
        voronoiBuilder.setSites(mesh.getCoordinates());
        Geometry voronoiDiagram = voronoiBuilder.getDiagram(fact);

        for (int i = 0; i < voronoiDiagram.getNumGeometries(); i++) {
            Polygon poly = new Polygon(mesh.getVertices().get(i));
            poly.convertGeometry(voronoiDiagram.getGeometryN(i));

            Random bag = new Random();
            int[] colour = { bag.nextInt(255), bag.nextInt(255), bag.nextInt(255), 130 };
            poly.setColour(colour);
            mesh.polygons.add(poly);
        }
    }

    public void loidRelaxation(Mesh mesh, int relaxLevel) {

        for (int i = 0; i <= relaxLevel; i++) {
            calculateVoronoi(mesh);
            for (int j = 0; j < mesh.polygons.size(); j++) {
                mesh.polygons.get(j).setCentroid(mesh.polygons.get(j).centerOfMass());
            }
        }
    }

    public void cropMesh(Mesh mesh) {
        ArrayList<Polygon> toRemove = new ArrayList<Polygon>();
        for (Polygon p : mesh.polygons) {
            for (Segment s : p.getSegments()) {
                Vertex v1 = s.getV1();
                Vertex v2 = s.getV2();
                boolean v1out = v1.getX() > mesh.width ||
                        v1.getY() > mesh.height || v1.getX() < 0 || v1.getY() < 0;
                boolean v2out = v2.getX() > mesh.width ||
                        v2.getY() > mesh.height || v2.getX() < 0 || v2.getY() < 0;
                if (v1out || v2out) {
                    toRemove.add(p);
                    break;
                }
            }
        }
        mesh.polygons.removeAll(toRemove);
    }

    public Mesh generatePolygonMesh(String meshType, int relaxLevel) {
        // Create new mesh


        Mesh mesh = new Mesh(100, 100, 1);
        if (meshType == "grid"){
            makeSquareVertices(mesh);
            makeSquarePolygons(mesh);
        } else if(meshType == "irregular") {
            makeRandomVertices(mesh);
            loidRelaxation(mesh, relaxLevel);
            cropMesh(mesh);

            // Remove the original points after they have been relaxed
            mesh.getVertices().clear();
        }

        return mesh;
    }
}
