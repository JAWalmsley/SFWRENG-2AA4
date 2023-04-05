package ca.mcmaster.cas.se2aa4.a2.island.roads;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Edge;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Point;
import ca.mcmcaster.cas.se2aa4.a2.island.islandBuilder.Configuration;
import ca.mcmcaster.cas.se2aa4.a2.island.islandBuilder.IslandBuilder;
import ca.mcmcaster.cas.se2aa4.a2.island.roads.DefaultRoads;
import ca.mcmcaster.cas.se2aa4.a2.island.roads.RoadGenerator;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class DefaultRoadsTest {
    RoadGenerator rg;
    Board board;
    int numCities = 5;

    @BeforeEach
    void setUp() throws ParseException, IOException{
        Path file = Path.of("src/test/resources").resolve("testdata.mesh");
        String filePath = file.toString();
        String[] args = { "-i", filePath, "-o", "none", "-d", "5", "-c", "5"};
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
        long randomSeed = Long.valueOf(config.getOption("d", String.valueOf(System.currentTimeMillis())));
        System.out.println("Using " + randomSeed + " as seed.");

        board = new Board(aMesh, randomSeed);
        IslandBuilder island = new IslandBuilder(board);
        island.generateIsland(output, shapeType, elevationType, numLakes, heatmapInput, soilProfile, numAquifers,
                numRivers, mode, biome, numCities);
    }

    @Test
    void testNumCities() {
        
        HashSet<Point> foundCities = new HashSet<>();
        for (Point p : board.getPoints()) {
            if(p.isCity()) {
                foundCities.add(p);
            }
        }
        assertEquals(numCities, foundCities.size());
    }

    @Test
    public void testRoadsSanity() {
        HashSet<Edge> foundRoads = new HashSet<>();
        for(Edge e : board.getEdges()) {
            if(e.isRoad()) {
                foundRoads.add(e);
            }
        }
        assertTrue(foundRoads.size() > 0);
    }
}
