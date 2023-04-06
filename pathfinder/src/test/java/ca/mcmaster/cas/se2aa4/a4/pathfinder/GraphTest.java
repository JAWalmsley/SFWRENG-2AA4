package ca.mcmaster.cas.se2aa4.a4.pathfinder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class GraphTest {
    @Test
    public void testNeighbours() {
        // Single neighbour
        Node n1 = new Node("City1");
        Node n2 = new Node("City2");
        Edge e = new Edge(n1, n2, 0);
        Graph g = new Graph();
        g.addEdge(e);
        assertTrue(g.getNeighbours(n1).size() == 1);
        assertTrue(g.getNeighbours(n2).size() == 1);

        Node neighbour = g.getNeighbours(n1).get(0);
        assertTrue(neighbour == n2);

        // Multiple neighbours
        Node n3 = new Node("City3");
        Edge e2 = new Edge(n2, n3, 0);
        g.addEdge(e2);
        assertTrue(g.getNeighbours(n2).size() == 2);
        List<Node> neighbours = g.getNeighbours(n2);
        assertTrue(neighbours.contains(n1));
        assertTrue(neighbours.contains(n3));
    }

    @Test
    public void testWeight() {
        Node n1 = new Node("City1");
        Node n2 = new Node("City2");
        Edge e = new Edge(n1, n2, 100);
        Graph g = new Graph();
        g.addEdge(e);
        assertTrue(g.getWeight(n1, n2) == 100);
        // Should throw if the nodes aren't connected
        assertThrows(IllegalArgumentException.class, () -> g.getWeight(n1, new Node("fakeNode")));
    }
}
