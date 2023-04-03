package ca.mcmcaster.cas.se2aa4.a4.pathfinder;

import java.util.List;

public interface Pathfinder {
    public List<Node> findShortestPath(Graph g, Node start, Node end);
}
