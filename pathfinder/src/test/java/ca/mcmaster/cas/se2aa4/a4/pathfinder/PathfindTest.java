package ca.mcmaster.cas.se2aa4.a4.pathfinder;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import ca.mcmcaster.cas.se2aa4.a4.pathfinder.DijkstraPathfinder;
import ca.mcmcaster.cas.se2aa4.a4.pathfinder.Edge;
import ca.mcmcaster.cas.se2aa4.a4.pathfinder.Graph;
import ca.mcmcaster.cas.se2aa4.a4.pathfinder.Node;

public class PathfindTest {
    @Test
    public void testDjikstraSanity() {
        Graph g = new Graph();

        Node n1 = new Node("City1");
        Node n2 = new Node("City2");
        Edge e = new Edge(n1, n2, 0);

        g.addEdge(e);

        DijkstraPathfinder dj = new DijkstraPathfinder();
        List<Node> path = dj.findShortestPath(g, n1, n2);

        assertTrue(path.size() == 2);
    }

    @Test
    public void testDjikstra() {
        Graph g = new Graph();

        Node n1 = new Node("City1");
        Node n2 = new Node("City2");
        Node n3 = new Node("City3");
        Edge e1 = new Edge(n1, n2, 1);
        Edge e2 = new Edge(n2, n3, 2);
        g.addEdge(e1);
        g.addEdge(e2);

        DijkstraPathfinder dj = new DijkstraPathfinder();
        List<Node> path = dj.findShortestPath(g, n1, n3);

        assertTrue(path.get(2) == n1);
        assertTrue(path.get(0) == n3);
    }
}
