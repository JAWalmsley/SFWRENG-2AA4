import ca.mcmaster.cas.se2aa4.a2.generator.GenerateMesh;
import ca.mcmaster.cas.se2aa4.a2.generator.Mesh;
import ca.mcmaster.cas.se2aa4.a2.generator.SampleData;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.io.IOException;

import com.google.protobuf.DescriptorProtos.GeneratedCodeInfo;

public class Main {

    public static void main(String[] args) throws IOException {
        GenerateMesh generator = new GenerateMesh();
        Mesh m = generator.generatePolygonMesh(4);
        m.calculateNeighbours();
        Structs.Mesh myMesh = m.getIOMesh();
        MeshFactory factory = new MeshFactory();
        factory.write(myMesh, args[0]);
    }
}
