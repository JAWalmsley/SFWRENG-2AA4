
import ca.mcmaster.cas.se2aa4.a2.generator.GenerateMesh;
import ca.mcmaster.cas.se2aa4.a2.generator.Mesh;
import ca.mcmaster.cas.se2aa4.a2.generator.SampleData;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.text.ParseException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.HelpFormatter;


import java.io.IOException;

import com.google.protobuf.DescriptorProtos.GeneratedCodeInfo;

public class Main {

    public static void main(String[] args) throws IOException, org.apache.commons.cli.ParseException {

        DefaultParser cliParser = new DefaultParser();
        Options options = new Options();

        options.addOption("h", "help", false, "Display help")
                .addOption("n", "polygons", true, "Number of Polygons")
                .addOption("r", "relaxation", true, "Relaxation Level");
        CommandLine cli = cliParser.parse(options, args);
        if (cli.getArgs.length !=1 || cli.hasOption("help")
        || !cli.hasOption("n") || !cli.hasOption("r")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("Generator [OPTIONS] grid/irregular", options);
            return;
        }
        String[] choice = cli.getArgs();

        int numPolygons = Integer.valueOf(cli.getOptionValue("n"));
        int relaxLevel = Integer.valueOf(cli.getOptionValue("r"));

        GenerateMesh generator = new GenerateMesh();
        generator.setNumPolygons(numPolygons);
        Mesh m = generator.generatePolygonMesh("square", relaxLevel);
        m.calculateNeighbours();
        Structs.Mesh myMesh = m.getIOMesh();
        MeshFactory factory = new MeshFactory();
        factory.write(myMesh, args[0]);
    }
}
