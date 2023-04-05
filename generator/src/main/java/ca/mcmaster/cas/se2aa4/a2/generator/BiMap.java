package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.HashMap;
import java.util.Map;

/**
 * This is a map that allows you to get by key and by value.
 * Useful for a map from Vertex to graph Nodes, these are mutually unique.
 */
public class BiMap<K, V> {
    private Map<K, V> forward;
    private Map<V, K> backward;

    public BiMap() {
        this.forward = new HashMap<K, V>();
        this.backward = new HashMap<V, K>();
    }

    public void put(K key, V value) {
        this.forward.put(key, value);
        this.backward.put(value, key);
    }

    public V get(K key) {
        return this.forward.get(key);
    }

    public K invGet(V value) {
        return this.backward.get(value);
    }

    public int size() {
        return this.forward.size();
    }
}
