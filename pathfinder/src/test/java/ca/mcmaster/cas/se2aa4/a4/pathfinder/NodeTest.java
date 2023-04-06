package ca.mcmaster.cas.se2aa4.a4.pathfinder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NodeTest {
    @Test
    public void testName() {
        Node node = new Node("Toronto");
        assertEquals("Toronto", node.getName());
    }
}
