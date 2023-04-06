package ca.mcmaster.cas.se2aa4.a4.pathfinder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NodeTest {
    @Test
    public void testProps() {
        Node node = new Node();
        node.setProp("name", "Toronto");
        assertEquals("Toronto", node.getProp("name"));
    }
}
