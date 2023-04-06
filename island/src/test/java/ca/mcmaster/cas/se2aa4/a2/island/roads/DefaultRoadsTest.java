package ca.mcmaster.cas.se2aa4.a2.island.roads;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;

import org.apache.commons.cli.ParseException;
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
import ca.mcmcaster.cas.se2aa4.a2.island.roads.CityType;
import ca.mcmcaster.cas.se2aa4.a2.island.roads.RoadGenerator;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class DefaultRoadsTest {
    RoadGenerator rg;
    Board board;
    int numCities = 3;

    @BeforeEach
    void setUp() throws ParseException, IOException{
        Path file = Path.of("src/test/resources").resolve("testdata.mesh");
        String filePath = file.toString();
        String[] args = { "-i", filePath, "-o", "none"};
        Configuration config = new Configuration(args);

        boolean parsedCorrectly = config.parse();
        if (!parsedCorrectly) {
            return;
        }

        String input = config.getOption("i", "");
        String output = config.getOption("o", "");
        String mode = "normal";
        Structs.Mesh aMesh = new MeshFactory().read(input);
        String shapeType = "circle";
        String elevationType = "volcano";
        int numLakes = 1;
        String heatmapInput = "i";
        String soilProfile = "linear";
        int numAquifers = 1;
        int numRivers = 0;
        String biome = "grassland";
        long randomSeed = 5;
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
            if(!(p.getCity() == CityType.NONE)) {
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
