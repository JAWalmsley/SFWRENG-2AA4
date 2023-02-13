package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

public class GenerateMesh {
    private int square_size = 5;
    public void makeVertices(Mesh mesh){
        // Create all the vertices
        int bag = 0;
        for (int x = 0; x < mesh.width; x += square_size) {
            mesh.columns++;
            for (int y = 0; y < mesh.height; y += square_size) {
                Vertex v1 = new Vertex(x, y);
                mesh.vertices.add(v1);
                if (x == 0)
                    mesh.rows++;
            }
        }
    }    

    public void makePolygons(Mesh mesh, int sides){
        //TODO: Create a polygon with the given number of sides
        sides = 4;
        for (int i = 0; i < mesh.rows-1; i++) {
            for (int j = 0; j < mesh.columns-1; j++) {    
                Polygon poly = new Polygon(new Vertex(0, 0)); //Centroid currently unsed so just set to 0,0
                //Make Square
                Vertex vert1 = mesh.vertices.get(i*mesh.rows+j);
                Vertex vert2 = mesh.vertices.get(i*mesh.rows+j+mesh.rows);
                Vertex vert3 = mesh.vertices.get(i*mesh.rows+j+1+mesh.rows);
                Vertex vert4 = mesh.vertices.get(i*mesh.rows+j+1);

                poly.verticies.add(vert1);
                poly.verticies.add(vert2);
                poly.verticies.add(vert3);
                poly.verticies.add(vert4);

                Segment seg1 = new Segment(vert1, vert2);
                Segment seg2 = new Segment(vert2, vert3);
                Segment seg3 = new Segment(vert3, vert4);
                Segment seg4 = new Segment(vert4, vert1);
                
                poly.addSegment(seg1);
                poly.addSegment(seg2);
                poly.addSegment(seg3);
                poly.addSegment(seg4);        
                
                mesh.polygons.add(poly);
            }
        }
    }

    public Mesh generatePolygonMesh(int sides) {
        //Create new mesh
        Mesh mesh = new Mesh(40, 40);
        makeVertices(mesh);
        makePolygons(mesh, sides);

        return mesh;
    }
}
