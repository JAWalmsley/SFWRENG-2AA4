package ca.mcmaster.cas.se2aa4.a4.pathfinder;

public class Edge {
    Node node1;
    Node node2;
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

    /**
     * Get the node that isn't the one passed in, useful for finding neighbours and such
     * @param n the node to exclude
     * @return the other node
     */
    public Node getOtherNode(Node n) {
        if(n.equals(this.node1)) {
            return this.node2;
        } else if(n.equals(this.node2)) {
            return this.node1;
        } else {
            throw new IllegalArgumentException("Node is not in this edge");
        }
    }

    @Override
    public int hashCode() {
        // Auto-generated hashCode
        final int prime = 31;
        int result = 1;
        result = prime * result + ((node1 == null) ? 0 : node1.hashCode());
        result = prime * result + ((node2 == null) ? 0 : node2.hashCode());
        result = prime * result + Float.floatToIntBits(weight);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        // Auto-generated equals
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Edge other = (Edge) obj;
        if (node1 == null) {
            if (other.node1 != null)
                return false;
        } else if (!node1.equals(other.node1))
            return false;
        if (node2 == null) {
            if (other.node2 != null)
                return false;
        } else if (!node2.equals(other.node2))
            return false;
        if (Float.floatToIntBits(weight) != Float.floatToIntBits(other.weight))
            return false;
        return true;
    }
}
