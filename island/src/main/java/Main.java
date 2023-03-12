import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.shape.Circle;
import ca.mcmcaster.cas.se2aa4.a2.island.shape.Lagoon;
import ca.mcmcaster.cas.se2aa4.a2.island.shape.Shape;
import ca.mcmcaster.cas.se2aa4.a2.island.shape.Square;
import ca.mcmcaster.cas.se2aa4.a2.island.shape.Triangle;

import ca.mcmcaster.cas.se2aa4.a2.island.shape.ShapeFactory;

import java.text.ParseException;
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
        Board board = new Board(aMesh);

        DefaultParser cliParser = new DefaultParser();
        Options options = new Options();

        options.addOption("h", "help", false, "Display help")
                .addOption("s", "shape", true, "Island Shape");
        CommandLine cli = cliParser.parse(options, args);
        if (cli.getArgs().length != 1 || cli.hasOption("help")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("Island [OPTIONS]", options);
        }

        String shapeInput = "triangle";
        if (cli.hasOption("s")) {
            shapeInput = String.valueOf(cli.getOptionValue("s"));
        }

        Shape shape = ShapeFactory.getShape(shapeInput, 100);
        shape.draw(board);
        board.export(output);
    }
}
