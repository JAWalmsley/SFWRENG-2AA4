package ca.mcmaster.cas.se2aa4.a4.pathfinder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

public class PathfindTest {
    @Test
    public void testDjikstraSanity() {
        Graph g = new Graph();

        Node n1 = new Node();
        n1.setProp("name", "c1");
        Node n2 = new Node();
        n2.setProp("name", "c2");
        Edge e = new Edge(n1, n2, 1);

        g.addEdge(e);

        DijkstraPathfinder dj = new DijkstraPathfinder();
        List<Node> path = dj.findShortestPath(g, n1, n2);

        assertEquals(path.size(), 2);
    }

    @Test
    public void testDjikstra() {
        Graph g = new Graph();

        Node n1 = new Node();
        n1.setProp("name", "c1");
        Node n2 = new Node();
        n2.setProp("name", "c2");
        Node n3 = new Node();
        n3.setProp("name", "c3");
        Edge e1 = new Edge(n1, n2, 1);
        Edge e2 = new Edge(n2, n3, 2);
        g.addEdge(e1);
        g.addEdge(e2);

        DijkstraPathfinder dj = new DijkstraPathfinder();
        List<Node> path = dj.findShortestPath(g, n1, n3);

        assertEquals(path.get(2), n1);
        assertEquals(path.get(0), n3);
    }

    @Test
    public void testErrors() {
        Graph g = new Graph();

        Node n1 = new Node();
        Node n2 = new Node();
        Node n3 = new Node();
        Edge e1 = new Edge(n1, n2, 1);
        g.addEdge(e1);

        DijkstraPathfinder dj = new DijkstraPathfinder();

        assertThrows(Exception.class, () -> dj.findShortestPath(g, n1, n3));
        assertThrows(Exception.class, () -> dj.findShortestPath(g, n3, n1));

        g.addNode(n3);
        assertThrows(Exception.class, () -> dj.findShortestPath(g, n3, n1));

    }
}
