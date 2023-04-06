package ca.mcmaster.cas.se2aa4.a4.pathfinder;

import java.util.HashMap;

public class Node {
    HashMap<String, String> properties;
    public Node() {
        this.properties = new HashMap<String, String>();
    }

    public void setProp(String key, String value) {
        this.properties.put(key, value);
    }

    public String getProp(String key) {
        return this.properties.get(key);
    }

    public String toString() {
        return this.properties.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((properties == null) ? 0 : properties.hashCode());
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
        if (properties == null) {
            if (other.properties != null)
                return false;
        } else if (!properties.equals(other.properties))
            return false;
        return true;
    }
}
