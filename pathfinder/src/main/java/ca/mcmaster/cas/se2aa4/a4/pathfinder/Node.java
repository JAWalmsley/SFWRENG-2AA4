package ca.mcmaster.cas.se2aa4.a4.pathfinder;

public class Node {
    String name;
    public Node(String cityName) {
        this.name = cityName;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        // Auto-generated hashCode
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}
