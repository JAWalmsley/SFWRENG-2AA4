import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.islandBuilder.IslandBuilder;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.HelpFormatter;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, org.apache.commons.cli.ParseException {
        // TODO: command line parameters
        String input = "sample.mesh";
        String output = "island.mesh";

        // Getting width and height for the canvas
        Structs.Mesh aMesh = new MeshFactory().read(input);

        DefaultParser cliParser = new DefaultParser();
        Options options = new Options();

        options.addOption("h", "help", false, "Display help")
                .addOption("s", "shape", true, "Island Shape")
                .addOption("l", "lakes", true, "Number of Lakes")
                .addOption("d", "seed", true, "Generation Seed");
        CommandLine cli = cliParser.parse(options, args);
        if (cli.hasOption("help")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("Island [OPTIONS]", options);
            return;
        }

        String shapeInput = "triangle";
        if (cli.hasOption("s")) {
            shapeInput = String.valueOf(cli.getOptionValue("s"));
        }
        int lakeInput = 5;
        if(cli.hasOption("l")) {
            lakeInput = Integer.valueOf(cli.getOptionValue("l"));
        }
        long randomSeed = 0;
        if(cli.hasOption("d")) {
            randomSeed = Long.parseLong(cli.getOptionValue("d"));
        } else {
            long seed = System.currentTimeMillis();
            randomSeed = seed;
            System.out.println("No seed provided, using " + seed + " as seed.");
        }

        Board board = new Board(aMesh, randomSeed);

        IslandBuilder island = new IslandBuilder(board);
        island.generateIsland(output, shapeInput, lakeInput);
        board.export(output);
    }
}
