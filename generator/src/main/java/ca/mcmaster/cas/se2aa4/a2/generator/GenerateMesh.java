package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.Random;

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
                Polygon poly = new Polygon(new Vertex(0, 0)); // Centroid currently unsed so just set to 0,0
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
                int[] colour = { bag.nextInt(255), bag.nextInt(255), bag.nextInt(255) };
                poly.setColour(colour);

                mesh.polygons.add(poly);
            }
        }
    }

    public Mesh generatePolygonMesh(int sides) {
        // Create new mesh
        Mesh mesh = new Mesh(100, 100, 1);
        makeVertices(mesh);
        // makePolygons(mesh, sides);

        return mesh;
    }
}
