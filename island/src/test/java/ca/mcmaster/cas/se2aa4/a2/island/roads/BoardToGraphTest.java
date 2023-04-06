package ca.mcmaster.cas.se2aa4.a2.island.roads;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;

import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Node;
import ca.mcmcaster.cas.se2aa4.a2.island.BiMap;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Edge;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Point;
import ca.mcmcaster.cas.se2aa4.a2.island.islandBuilder.Configuration;
import ca.mcmcaster.cas.se2aa4.a2.island.islandBuilder.IslandBuilder;
import ca.mcmcaster.cas.se2aa4.a2.island.roads.BoardToGraph;

@TestInstance(Lifecycle.PER_CLASS)
public class BoardToGraphTest {
    BoardToGraph mtg;
    Board board;
    Graph graph;
    BiMap<Point, Node> nodeMap;
    int numPoints;

    @BeforeAll
    void setUp() throws IOException, ParseException {
        Path file = Path.of("src/test/resources").resolve("testdata.mesh");
        String filePath = file.toString();
        String[] args = { "-i", filePath, "-o", "none", "-d", "5" };
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

        board = new Board(aMesh, randomSeed);
        IslandBuilder island = new IslandBuilder(board);
        island.generateIsland(output, shapeType, elevationType, numLakes, heatmapInput, soilProfile, numAquifers,
                numRivers, mode, biome, numCities);

        mtg = new BoardToGraph(board);
        graph = mtg.getGraph();
        nodeMap = mtg.getNodeMap();

        HashSet<Point> points = new HashSet<>();
        points.addAll(board.getPoints());
        // Remove one point for the centroid of every tile
        numPoints = points.size() - board.getTiles().size();
    }

    @Test
    void testGraphNum() {
        assertEquals(numPoints, graph.getNodes().size());
    }

    @Test
    void testGraphConnections() {
        for (Edge e : board.getEdges()) {
            Point[] pv = board.getPoints(e);
            Node n1 = nodeMap.get(pv[0]);
            Node n2 = nodeMap.get(pv[1]);
            assertTrue(graph.getNeighbours(n1).contains(n2));
            assertTrue(graph.getNeighbours(n2).contains(n1));
        }
    }

    @Test
    void testNodeMapNum() {
        assertEquals(numPoints, nodeMap.size());
    }
}
