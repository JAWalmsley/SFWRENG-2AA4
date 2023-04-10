package ca.mcmaster.cas.se2aa4.a4.pathfinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraPathfinder implements Pathfinder {

    /**
     * Finds the shortest path between start and end using Dijkstra's algorithm
     * 
     * @param g     The graph containing the nodes
     * @param start The starting node
     * @param end   The ending node
     * @return A list of nodes representing the shortest path from start(max index)
     *         to end(index 0)
     */
    @Override
    public List<Node> findShortestPath(Graph g, Node start, Node end) throws IllegalArgumentException {
        HashMap<Node, Float> dist = new HashMap<>();
        HashMap<Node, Node> prev = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(g.getNodes().size(), new MapKeyComparator(dist));

        // Initialize all distances to infinity and add all nodes to the queue
        for (Node n : g.getNodes()) {
            dist.put(n, Float.MAX_VALUE);
            prev.put(n, null);
            queue.add(n);
        }

        // Start has a distance of 0 to itself
        dist.put(start, 0f);
        queue.remove(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            // Get current lowest distance node
            Node currNode = queue.poll();
            if (currNode == end) {
                break;
            }
            // Add all neighbours to the queue and see if we have found a faster path to them
            for (Node neigh : g.getNeighbours(currNode)) {
                float alt = dist.get(currNode) + g.getWeight(currNode, neigh);
                if (alt < dist.get(neigh)) {
                    dist.put(neigh, alt);

                    // Remove and re-add to update the priority queue with node's new distance
                    // (weird but Java's PQ only updates priority when you add)
                    queue.remove(neigh);
                    queue.add(neigh);

                    prev.put(neigh, currNode);
                }
            }
        }

        if (prev.get(end) == null) {
            throw new IllegalArgumentException("No path found");
        }

        // We found the trail of prevs, trace them to get the shortest path
        List<Node> finalPath = new ArrayList<>();
        Node traceNode = end;
        while (traceNode != null) {
            finalPath.add(traceNode);
            traceNode = prev.get(traceNode);
        }
        return finalPath;
    }

}
