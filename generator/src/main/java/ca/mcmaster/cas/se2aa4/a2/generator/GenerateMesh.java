package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.Random;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;

public class GenerateMesh {
    private int numVertices = 100;

    public void makeVertices(Mesh mesh) {
        Random bag = new Random();
        for (int i = 0; i < numVertices; i++) {
            float x = bag.nextFloat() * mesh.width;
            float y = bag.nextFloat() * mesh.height;
            Vertex v = new Vertex(x, y);
            mesh.addVertex(v);
        }
    }

    public void makePolygons(Mesh mesh, int sides) {
        // TODO: Create a polygon with the given number of sides
        sides = 4;
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
            mesh.polygons.add(poly);
        }
    }

    public void loidRelaxation(Mesh mesh) {
        int LOOP = 10;

        for (int i = 0; i <= LOOP; i++) {
            calculateVoronoi(mesh);
            for (int j = 0; j < mesh.polygons.size(); j++) {
                mesh.polygons.get(j).setCentroid(mesh.polygons.get(j).centerOfMass());
            }
        }
    }

    public Mesh generatePolygonMesh(int sides) {
        // Create new mesh
        Mesh mesh = new Mesh(100, 100, 1);
        makeVertices(mesh);

        loidRelaxation(mesh);

        // Remove the original points after they have been relaxed
        mesh.getVertices().clear();

        return mesh;
    }
}
