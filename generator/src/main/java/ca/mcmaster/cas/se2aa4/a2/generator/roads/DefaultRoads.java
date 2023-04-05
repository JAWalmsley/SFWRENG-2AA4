package ca.mcmaster.cas.se2aa4.a2.generator.roads;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import ca.mcmaster.cas.se2aa4.a2.generator.BiMap;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.PairOfVertex;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Vertex;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.DijkstraPathfinder;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Edge;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Node;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Pathfinder;

public class DefaultRoads implements RoadGenerator {
    private HashSet<Vertex> cities = new HashSet<Vertex>();
    private BiMap<Vertex, Node> nodes;
    private Graph graph;
    private int numCities;

    public DefaultRoads(int numCities) {
        this.numCities = numCities;
    }

    private void placeCities(Mesh mesh) {
        Random rand = new Random();
        // Loop through all the polygons endlessly until we get enough cities
        outer: while (true) {
            for (Polygon p : mesh) {
                // Take the first vertex in the polygon. It's random distribution, doesn't rly
                // matter which one
                Vertex v = p.hull().get(0).contents()[0];
                if (cities.size() >= numCities) {
                    break outer;
                }
                if (rand.nextInt(0, 100) < 10 && !cities.contains(v)) {
                    this.cities.add(v);
                }
            }
        }

        for (Vertex v : cities) {
            v.setCity(true);
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
    public Mesh addRoads(Mesh mesh) {
        placeCities(mesh);

        MeshToGraph mtg = new MeshToGraph(mesh);
        this.graph = mtg.getGraph();
        this.nodes = mtg.getNodeMap();

        connectCities(mesh);
        
        return mesh;
    }

}
