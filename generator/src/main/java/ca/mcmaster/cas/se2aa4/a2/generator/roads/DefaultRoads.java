package ca.mcmaster.cas.se2aa4.a2.generator.roads;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.PairOfVertex;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Vertex;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Edge;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Node;

public class DefaultRoads implements RoadGenerator {
    private HashSet<Vertex> cities = new HashSet<Vertex>();
    private HashMap<Vertex, Node> nodes;
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

    

    @Override
    public Mesh addRoads(Mesh mesh) {
        placeCities(mesh);

        MeshToGraph mtg = new MeshToGraph(mesh);
        this.graph = mtg.getGraph();
        this.nodes = mtg.getNodeMap();
        
        return mesh;
    }

}
