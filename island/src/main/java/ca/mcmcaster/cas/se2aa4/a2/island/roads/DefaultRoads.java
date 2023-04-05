package ca.mcmcaster.cas.se2aa4.a2.island.roads;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Node;
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
        // Loop through all the polygons endlessly until we get enough cities
        while(this.cities.size() < this.numCities) {
            
        }

        for (Point p : cities) {
            p.setCity(true);
        }
    }

    private void connectCities(Mesh mesh) {
        Pathfinder pf = new DijkstraPathfinder();
        // TODO: Decide on a center node in a smart way
        Node center = this.nodes.get(this.cities.iterator().next());
        for (Vertex v : cities) {
            Node n = this.nodes.get(v);
            if (n != center) {
                List<Node> path = pf.findShortestPath(this.graph, center, n);
                for(int i = 0; i < path.size() - 1; i++) {
                    // Get the vertices that map to the nodes in the path
                    Vertex v1 = this.nodes.invGet(path.get(i));
                    Vertex v2 = this.nodes.invGet(path.get(i+1));
                    PairOfVertex pathPair = mesh.getPairFromVertices(v1, v2);
                    pathPair.setRoad(true);
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

        connectCities(mesh);
        
        return mesh;
    }

}
