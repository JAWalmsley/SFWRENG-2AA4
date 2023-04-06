package ca.mcmaster.cas.se2aa4.a4.pathfinder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class GraphTest {
    @Test
    public void testNeighbours() {
        // Single neighbour
        Node n1 = new Node();
        n1.setProp("name", "c1");
        Node n2 = new Node();
        n2.setProp("name", "c2");
        Edge e = new Edge(n1, n2, 0);
        Graph g = new Graph();
        g.addEdge(e);
        assertEquals(1, g.getNeighbours(n1).size());
        assertEquals(1, g.getNeighbours(n2).size());

        Node neighbour = g.getNeighbours(n1).get(0);
        assertTrue(neighbour == n2);

        // Multiple neighbours
        Node n3 = new Node();
        Edge e2 = new Edge(n2, n3, 0);
        g.addEdge(e2);
        assertTrue(g.getNeighbours(n2).size() == 2);
        List<Node> neighbours = g.getNeighbours(n2);
        assertTrue(neighbours.contains(n1));
        assertTrue(neighbours.contains(n3));
    }

    @Test
    public void testWeight() {
        Node n1 = new Node();
        n1.setProp("name", "c1");
        Node n2 = new Node();
        n2.setProp("name", "c2");
        Edge e = new Edge(n1, n2, 100);
        Graph g = new Graph();
        g.addEdge(e);
        assertTrue(g.getWeight(n1, n2) == 100);
        // Should throw if the nodes aren't connected
        assertThrows(IllegalArgumentException.class, () -> g.getWeight(n1, new Node()));
    }
}
