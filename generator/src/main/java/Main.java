import ca.mcmaster.cas.se2aa4.a2.generator.GenerateMesh;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        GenerateMesh generator = new GenerateMesh();
        Structs.Mesh myMesh = generator.generatePolygonMesh(4).getIOMesh();
        MeshFactory factory = new MeshFactory();
        factory.write(myMesh, args[0]);
    }
}
