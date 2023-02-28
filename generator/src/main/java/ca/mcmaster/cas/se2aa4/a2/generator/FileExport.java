package ca.mcmaster.cas.se2aa4.a2.generator;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.StringBuilder;

public class FileExport {
    private Mesh mesh;

    public FileExport(Mesh m) {
        this.mesh = m;
    }

    public void export(String filename) throws IOException {
        int numVertices = 0;
        StringBuilder builder = new StringBuilder();

        for (Polygon p : this.mesh.polygons) {
            for (Segment s : p.getSegments()) {
                // OBJ doesn't inherently support colours, but some applications use it. Blender does with an extension :)
                builder.append(vertexToString(s.getV1(), p.getColour()));
                numVertices++;
            }
            builder.append("f");
            for (int i = 0; i < p.getSegments().size(); i++) {
                builder.append(" " + (numVertices - i));
            }
            builder.append("\n");

        }
        FileWriter myWriter = new FileWriter(filename);
        myWriter.write(builder.toString());
        myWriter.close();
    }

    static String vertexToString(Vertex v, int[] colour) {
        return "v " + v.getX() + " " + v.getY() + " " + "1 " + (colour[0] / 255) + " " + (colour[1] / 255) + " "
                + (colour[2] / 255) + "\n";
    }
}
