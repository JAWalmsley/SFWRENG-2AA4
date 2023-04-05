package ca.mcmaster.cas.se2aa4.a2.generator.roads;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.cas.se2aa4.a2.generator.BiMap;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.PairOfVertex;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Vertex;
import ca.mcmaster.cas.se2aa4.a2.generator.configuration.Configuration;
import ca.mcmaster.cas.se2aa4.a2.generator.specification.Buildable;
import ca.mcmaster.cas.se2aa4.a2.generator.specification.SpecificationFactory;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Node;

public class MeshToGraphTest {
    MeshToGraph mtg;
    Mesh theMesh;
    Graph graph;
    BiMap<Vertex, Node> nodeMap;
    int numVertices;
    @BeforeEach
    void setUp() {
        String[] args = {"-h", "10" ,"-w", "10", "-k", "irregular", "-p", "10", "-r", "10", "-c", "5"};
        Configuration config = new Configuration(args);
        Buildable specification = SpecificationFactory.create(config);
        theMesh = specification.build();
        mtg = new MeshToGraph(theMesh);
        graph = mtg.getGraph();
        nodeMap = mtg.getNodeMap();


        // Search for number of unique vertices with a set
        HashSet<Vertex> vertices = new HashSet<>();
        for(Polygon p : theMesh) {
            for(PairOfVertex pv : p.hull()) {
                for(Vertex v : pv.contents()) {
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
        for(Polygon p: theMesh) {
            for(PairOfVertex pv : p.hull()) {
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
