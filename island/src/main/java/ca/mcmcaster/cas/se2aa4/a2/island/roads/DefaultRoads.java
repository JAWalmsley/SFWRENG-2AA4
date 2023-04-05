package ca.mcmcaster.cas.se2aa4.a2.island.roads;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.DijkstraPathfinder;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Node;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Pathfinder;
import ca.mcmcaster.cas.se2aa4.a2.island.BiMap;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Point;

public class DefaultRoads implements RoadGenerator {
    private HashSet<Point> cities = new HashSet<Point>();
    private BiMap<Point, Node> nodes;
    private Graph graph;
    private int numCities;

    public DefaultRoads(int numCities) {
        this.numCities = numCities;
    }

    private void placeCities(Board board) {
        Random rand = new Random();
        List<Point> points = board.getPoints();
        // Loop through all the polygons endlessly until we get enough cities
        while(this.cities.size() < this.numCities) {
            cities.add(points.get(rand.nextInt(0, points.size())));
        }

        for (Point p : cities) {
            p.setCity(true);
        }
    }

    private void connectCities(Board board) {
        Pathfinder pf = new DijkstraPathfinder();
        // TODO: Decide on a center node in a smart way
        Node center = this.nodes.get(this.cities.iterator().next());
        for (Point p : cities) {
            Node n = this.nodes.get(p);
            if (n != center) {
                List<Node> path = pf.findShortestPath(this.graph, center, n);
                for(int i = 0; i < path.size() - 1; i++) {
                    // Get the vertices that map to the nodes in the path
                    Point p1 = this.nodes.invGet(path.get(i));
                    Point p2 = this.nodes.invGet(path.get(i+1));
                    ca.mcmcaster.cas.se2aa4.a2.island.adt.Edge pathEdge = board.getEdge(p1, p2);
                    pathEdge.setRoad(true);
                }
            }
        }
    }

    @Override
    public void drawRoads(Board board) {
        placeCities(board);

        BoardToGraph mtg = new BoardToGraph(board);
        this.graph = mtg.getGraph();
        this.nodes = mtg.getNodeMap();

        connectCities(board);
    }

}
