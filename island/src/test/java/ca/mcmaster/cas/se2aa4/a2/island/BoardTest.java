package ca.mcmaster.cas.se2aa4.a2.island;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.RiverEdge;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.RiverPoint;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.LakeTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.LandTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.OceanTile;
import ca.mcmcaster.cas.se2aa4.a2.island.aquifiers.Aquifiers;
import ca.mcmcaster.cas.se2aa4.a2.island.elevation.ElevationFactory;
import ca.mcmcaster.cas.se2aa4.a2.island.lakes.PlaceLakes;
import ca.mcmcaster.cas.se2aa4.a2.island.moisture.MoistureFactory;
import ca.mcmcaster.cas.se2aa4.a2.island.moisture.MoistureProfile;
import ca.mcmcaster.cas.se2aa4.a2.island.rivers.RiverGenerator;
import ca.mcmcaster.cas.se2aa4.a2.island.shape.Circle;
import ca.mcmcaster.cas.se2aa4.a2.island.shape.Shape;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoardTest {
    Board board;
    Mesh aMesh;

    @BeforeAll
    public void setUp() throws IOException {
        // Test relies on an existing file, this is tehcnical debt but creating sample
        // data is outside time constraints right now
        aMesh = new MeshFactory().read("../sample.mesh");
        board = new Board(aMesh, 0);
    }

    @Test
    @Order(1)
    public void testBoard() {
        assertFalse(board.getTiles().size() == 0);
        assertFalse(board.getEdges().size() == 0);
        assertFalse(board.getPoints().size() == 0);
        assertTrue(board.getTiles().size() == aMesh.getPolygonsCount());
    }

    @Test
    @Order(2)
    public void testShape() {
        Shape shape = new Circle(200);
        shape.draw(board);
        assertTrue(board.getTiles().stream().anyMatch(tile -> tile instanceof LandTile));
        assertTrue(board.getTiles().stream().anyMatch(tile -> tile instanceof OceanTile));
    }

    @Test
    @Order(3)
    public void testElevation() {
        ElevationFactory.setElevation("volcano", board);

        assertTrue(board.getTiles().stream().anyMatch(tile -> tile.getElevation() > 0));
        assertTrue(board.getPoints().stream().anyMatch(point -> point.getElevation() > 0));
    }

    @Test
    @Order(4)
    public void testLakes() {
        PlaceLakes pl = new PlaceLakes();
        pl.drawLakes(board, 1);

        assertTrue(board.getTiles().stream().anyMatch(tile -> tile instanceof LakeTile));
    }

    @Test
    @Order(5)
    public void testAquifers() {
        Aquifiers aq = new Aquifiers();
        aq.placeAquifers(board, 1);

        assertTrue(board.getTiles().stream().anyMatch(tile -> tile.getIsAquifier() == true));
    }

    @Test
    @Order(6)
    public void testRivers() {
        RiverGenerator rg = new RiverGenerator();
        rg.placeRivers(board, 1);

        assertTrue(board.getEdges().stream().anyMatch(edge -> edge instanceof RiverEdge));
        assertTrue(board.getPoints().stream().anyMatch(point -> point instanceof RiverPoint));
    }

    @Test
    @Order(7)
    public void testMoisture() {
        MoistureProfile mp = MoistureFactory.getMoistureProfile("linear");
        mp.drawMoisture(board);

        assertTrue(board.getTiles().stream().anyMatch(tile -> tile instanceof LandTile && tile.getMoistureLevel() > 0));
    }
}
