
import ca.mcmaster.cas.se2aa4.a2.generator.MeshType;
import ca.mcmaster.cas.se2aa4.a2.generator.GenerateGridMesh;
import ca.mcmaster.cas.se2aa4.a2.generator.GenerateIrregularMesh;
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
                .addOption("r", "relaxation", true, "Relaxation Level")
                .addOption("o", "fileName", true, "Output File Name");
        CommandLine cli = cliParser.parse(options, args);
        if (cli.getArgs().length != 1 || cli.hasOption("help")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("Generator [OPTIONS] grid/irregular", options);
            return;
        }

        String[] choice = cli.getArgs();
        int numPolygons = 100;
        if (cli.hasOption("n")) {
            numPolygons = Integer.valueOf(cli.getOptionValue("n"));
        }

        int relaxLevel = 10;
        if (cli.hasOption("r")) {
            relaxLevel = Integer.valueOf(cli.getOptionValue("r"));
        }

        String fileName = "Sample.mesh";
        if (cli.hasOption("o")) {
            fileName = cli.getOptionValue("o");
        }

        // Grid mesh by default
        MeshType generator = new GenerateGridMesh(5);
        if (choice[0].equals("irregular")) {
            generator = new GenerateIrregularMesh(relaxLevel);
        }
        Mesh m = generator.generateMesh(numPolygons);
        m.calculateNeighbours();
        Structs.Mesh myMesh = m.getIOMesh();
        MeshFactory factory = new MeshFactory();
        factory.write(myMesh, fileName);
    }
}
