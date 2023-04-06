package ca.mcmaster.cas.se2aa4.a4.pathfinder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EdgeTest {
    Node n1;
    Node n2;

    @BeforeEach
    public void setUp() {
        n1 = new Node("City1");
        n2 = new Node("City2");
    }

    @Test
    public void testGetNodes() {
        Edge e = new Edge(n1, n2, 100);
        Node[] nodes = e.getNodes();
        assertEquals(n1, nodes[0]);
        assertEquals(n2, nodes[1]);
    }

    @Test
    public void testGetWeight() {
        Edge e = new Edge(n1, n2, 50);
        assertEquals(50, e.getWeight());
    }
}
