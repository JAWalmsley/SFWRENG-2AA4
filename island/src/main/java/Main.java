import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.islandBuilder.Configuration;
import ca.mcmcaster.cas.se2aa4.a2.island.islandBuilder.IslandBuilder;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, org.apache.commons.cli.ParseException {

        Configuration config = new Configuration(args);
        boolean parsedCorrectly = config.parse();
        if (!parsedCorrectly) {
            return;
        }

        String input = config.getOption("i", "");
        String output = config.getOption("o", "");

        String mode = config.getOption("mode", "normal");

        Structs.Mesh aMesh = new MeshFactory().read(input);

        String shapeType = config.getOption("s", "circle");

        String elevationType = config.getOption("e", "volcano");

        int numLakes = Integer.valueOf(config.getOption("l", "2"));

        String heatmapInput = config.getOption("h", "i");

        String soilProfile = config.getOption("a", "linear");

        int numAquifers = Integer.valueOf(config.getOption("q", "3"));

        int numRivers = Integer.valueOf(config.getOption("r", "5"));

        String biome = config.getOption("b", "grassland");

        int numCities = Integer.valueOf(config.getOption("c", "3"));

        long randomSeed = Long.valueOf(config.getOption("d", String.valueOf(System.currentTimeMillis())));
        System.out.println("Using " + randomSeed + " as seed.");

        Board board = new Board(aMesh, randomSeed);
        IslandBuilder island = new IslandBuilder(board);
        island.generateIsland(output, shapeType, elevationType, numLakes, heatmapInput, soilProfile, numAquifers,
                numRivers, mode, biome, numCities);
        board.export(output);
    }
}
