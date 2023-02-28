
import ca.mcmaster.cas.se2aa4.a2.generator.GenerateMesh;
import ca.mcmaster.cas.se2aa4.a2.generator.Mesh;
import ca.mcmaster.cas.se2aa4.a2.generator.SampleData;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;


import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.HelpFormatter;


import java.io.IOException;

import com.google.protobuf.DescriptorProtos.GeneratedCodeInfo;

public class Main {

    public static void main(String[] args) throws IOException {

        DefaultParser cliParser = new DefaultParser();
        Options options = new Options();

        options.addOption("t","trace", false, "Trace mode ON")
                .addOption("d", "debug", false, "debug mode ON");

        GenerateMesh generator = new GenerateMesh();
        Mesh m = generator.generatePolygonMesh("square");
        m.calculateNeighbours();
        Structs.Mesh myMesh = m.getIOMesh();
        MeshFactory factory = new MeshFactory();
        factory.write(myMesh, args[0]);
    }
}
