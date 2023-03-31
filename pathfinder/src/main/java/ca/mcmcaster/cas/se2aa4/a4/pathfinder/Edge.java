package ca.mcmcaster.cas.se2aa4.a4.pathfinder;

public class Edge {
    public Node node1;
    public Node node2;
    float weight;

    public Edge(Node node1, Node node2, float weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    public Node[] getNodes() {
        return new Node[] {this.node1, this.node2};
    }

    public float getWeight() {
        return this.weight;
    }
}
