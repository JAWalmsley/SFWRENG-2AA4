package ca.mcmcaster.cas.se2aa4.a2.island.roads;

import java.util.HashMap;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Node;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Edge;
import ca.mcmcaster.cas.se2aa4.a2.island.BiMap;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Point;


public class BoardToGraph {
    BiMap<Point, Node> nodes = new BiMap<Point, Node>();
    Board board;
    public BoardToGraph(Board board) {
        this.board = board;
    }

    public Graph getGraph() {
        Graph graph = new Graph();
        for(Point p: board.getPoints()) {
            Node n = new Node(String.valueOf(p.getX()) + " " + String.valueOf(p.getY()));
            nodes.put(p, n);
        }
        for(ca.mcmcaster.cas.se2aa4.a2.island.adt.Edge e : board.getEdges()) {
            Point[] pts = board.getPoints(e);
            Node n1 = nodes.get(pts[0]);
            Node n2 = nodes.get(pts[1]);
            graph.addEdge(new Edge(n1, n2, 1));
        }
        return graph;
    }

    public BiMap<Point, Node> getNodeMap() {
        return this.nodes;
    }
}
