package ca.mcmaster.cas.se2aa4.a4.pathfinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    List<Edge> edges;
    List<Node> nodes;
    // Map from each node to all the edges that contain that node
    Map<Node, List<Edge>> adjacency;

    public Graph() {
        this.edges = new ArrayList<>();
        this.nodes = new ArrayList<>();
        this.adjacency = new HashMap<>();
    }

    public void addNode(Node n) {
        this.nodes.add(n);
        this.adjacency.put(n, new ArrayList<>());
    }

    public void addEdge(Edge e) {
        this.edges.add(e);
        for(Node n : e.getNodes()) {
            // Add nodes if they aren't already in this graph
            if(!this.nodes.contains(n)){
                this.addNode(n);
            }
            this.adjacency.get(n).add(e);
        }
    }

    public List<Edge> getEdges() {
        // Defensive copy
        return new ArrayList<>(this.edges);
    }

    public List<Node> getNodes() {
        // Defensive copy
        return new ArrayList<>(this.nodes);
    }

    public List<Node> getNeighbours(Node s) {
        List<Node> neighbours = new ArrayList<>();
        for(Edge e : this.adjacency.get(s)) {
            neighbours.add(e.getOtherNode(s));
        }
        return neighbours;
    }

    public float getWeight(Node n1, Node n2) {
        for(Edge e : this.adjacency.get(n1)) {
            if(e.getOtherNode(n1) == n2){
                return e.getWeight();
            }
        }
        throw new IllegalArgumentException("Nodes are not connected");
    }
}
