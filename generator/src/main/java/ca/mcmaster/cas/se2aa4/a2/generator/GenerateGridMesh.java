package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.Random;

public class GenerateGridMesh implements MeshType {
    int square_size;

    public GenerateGridMesh(int square_size) {
        this.square_size = square_size;
    }

    public void makeSquareVertices(Mesh mesh) {
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

    /*
 The makeSquarePolygons function takes a Mesh object as input,
 which is the mesh to which the polygons will be added.
 The function generates a set of square polygons that cover the entire mesh.

The function loops through each row and column of vertices in the mesh,
and creates a new polygon for each square in the mesh.
The polygon is initialized with an arbitrary vertex,
and then its four vertices are defined based on the current row and column index.

The function then creates four Segment objects that define the edges of the polygon,
and adds them to the polygon object. The polygon's centroid is calculated and set,
and a random color is generated for the polygon using the Random class.

Finally, the function adds the polygon to the mesh's list of polygons.
*/
    public void makeSquarePolygons(Mesh mesh) {
        for (int i = 0; i < mesh.rows - 1; i++) {
            for (int j = 0; j < mesh.columns - 1; j++) {
                Polygon poly = new Polygon(new Vertex(0, 0)); // Initialize Centroid (re-defined later)
                // Make Square
                Vertex vert1 = mesh.getVertex(i * mesh.rows + j);
                Vertex vert2 = mesh.getVertex(i * mesh.rows + j + mesh.rows);
                Vertex vert3 = mesh.getVertex(i * mesh.rows + j + 1 + mesh.rows);
                Vertex vert4 = mesh.getVertex(i * mesh.rows + j + 1);

                poly.verticies.add(vert1);
                poly.verticies.add(vert2);
                poly.verticies.add(vert3);
                poly.verticies.add(vert4);

                poly.setCentroid(poly.centerOfMass()); // Centroid is now defined

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

    public Mesh generateMesh(int numPolygons) {
        int side_length = (int) Math.sqrt(numPolygons) * square_size;
        Mesh mesh = new Mesh(side_length, side_length, 1);

        makeSquareVertices(mesh);
        makeSquarePolygons(mesh);
        return mesh;
    }
}