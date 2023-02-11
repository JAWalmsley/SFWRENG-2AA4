package ca.mcmaster.cas.se2aa4.a2.generator;

public class GenerateMesh {
    private int square_size = 5;
    public void makeVertices(Mesh mesh){
        // Create all the vertices
        for (int x = 0; x < mesh.width; x += square_size) {
            for (int y = 0; y < mesh.height; y += square_size) {
                Vertex v1 = new Vertex(x, y);
                mesh.vertices.add(v1);
            }
        }
    }    

    public void makePolygons(Mesh mesh, int sides){
        //TODO: Create a polygon with the given number of sides
        sides = 4;
        Polygon poly = new Polygon(new Vertex(0, 0)); //Centroid currently unsed so just set to 0,0
        //Make Square
        Vertex vert1 = mesh.vertices.get(0);
        Vertex vert2 = mesh.vertices.get((int) (Math.sqrt(mesh.vertices.size())));
        Vertex vert3 = mesh.vertices.get(1+(int)(Math.sqrt(mesh.vertices.size())));
        Vertex vert4 = mesh.vertices.get(1);

        poly.verticies.add(vert1);
        poly.verticies.add(vert2);
        poly.verticies.add(vert3);
        poly.verticies.add(vert4);

        Segment seg1 = new Segment(mesh.vertices.get(0), mesh.vertices.get(1));
        seg1.index.add(0);
        seg1.index.add(1);
        Segment seg2 = new Segment(mesh.vertices.get(0), mesh.vertices.get((int) (Math.sqrt(mesh.vertices.size()))));
        seg2.index.add(0);
        seg2.index.add(mesh.width);
        Segment seg3 = new Segment(mesh.vertices.get((int) (Math.sqrt(mesh.vertices.size()))), mesh.vertices.get(1+(int)(Math.sqrt(mesh.vertices.size()))));
        seg3.index.add(mesh.width);
        seg3.index.add(1+mesh.width);
        Segment seg4 = new Segment(mesh.vertices.get(1), mesh.vertices.get(1+(int)(Math.sqrt(mesh.vertices.size()))));
        seg4.index.add(1);
        seg4.index.add(1+mesh.width);
        
        poly.addSegment(seg1);
        poly.addSegment(seg2);
        poly.addSegment(seg3);
        poly.addSegment(seg4);        
        
    }

    public Mesh generatePolygonMesh(int sides) {
        //Create new mesh
        Mesh mesh = new Mesh(625, 625);
        makeVertices(mesh);
        makePolygons(mesh, sides);

        return mesh;
    }

    
}
