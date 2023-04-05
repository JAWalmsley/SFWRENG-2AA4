package ca.mcmaster.cas.se2aa4.a2.generator.roads;

import java.util.HashMap;

import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.PairOfVertex;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Vertex;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Edge;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Node;

public class MeshToGraph {
    HashMap<Vertex, Node> nodes = new HashMap<Vertex, Node>();
    Mesh mesh;
    public MeshToGraph(Mesh mesh) {
        this.mesh = mesh;
    }

    public Graph getGraph() {
        Graph graph = new Graph();
        for (Polygon p : mesh) {
            for (PairOfVertex pv : p.hull()) {
                Vertex[] vertices = pv.contents();
                for (Vertex v : vertices) {
                    this.nodes.put(v, new Node(v.toString()));
                }
                // Edge weights are all 1 to simulate unweighted, will be changed based on
                // elevation etc in bonus
                graph.addEdge(new Edge(this.nodes.get(vertices[0]), this.nodes.get(vertices[1]), 1));
            }
        }
        return graph;
    }

    public HashMap<Vertex, Node> getNodeMap() {
        return this.nodes;
    }
}
