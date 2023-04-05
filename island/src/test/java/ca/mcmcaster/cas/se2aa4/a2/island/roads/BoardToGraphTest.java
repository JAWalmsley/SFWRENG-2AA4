package ca.mcmcaster.cas.se2aa4.a2.island.roads;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Node;
import ca.mcmcaster.cas.se2aa4.a2.island.BiMap;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Point;
import ca.mcmcaster.cas.se2aa4.a2.island.islandBuilder.Configuration;

public class BoardToGraphTest {

    BoardToGraph mtg;
    Board theBoard;
    Graph graph;
    BiMap<Point, Node> nodeMap;
    int numVertices;

    @BeforeEach
    void setUp() {
        String[] args = { "-h", "10", "-w", "10", "-k", "irregular", "-p", "10", "-r", "10", "-c", "5" };
        Configuration config = new Configuration(args);
        
        mtg = new BoardToGraph(theBoard);
        graph = mtg.getGraph();
        nodeMap = mtg.getNodeMap();

        // Search for number of unique vertices with a set
        HashSet<Vertex> vertices = new HashSet<>();
        for (Polygon p : theBoard) {
            for (PairOfVertex pv : p.hull()) {
                for (Vertex v : pv.contents()) {
                    vertices.add(v);
                }
            }
        }
        numVertices = vertices.size();
    }

    @Test
    void testGraphNum() {
        assertEquals(numVertices, graph.getNodes().size());
    }

    @Test
    void testGraphConnections() {
        for (Polygon p : theBoard) {
            for (PairOfVertex pv : p.hull()) {
                Node n1 = nodeMap.get(pv.contents()[0]);
                Node n2 = nodeMap.get(pv.contents()[1]);
                assertTrue(graph.getNeighbours(n1).contains(n2));
            }
        }
    }

    @Test
    void testNodeMapNum() {
        assertEquals(numVertices, nodeMap.size());
    }

}
