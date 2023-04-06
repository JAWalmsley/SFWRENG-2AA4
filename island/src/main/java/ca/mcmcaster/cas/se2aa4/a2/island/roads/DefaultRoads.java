package ca.mcmcaster.cas.se2aa4.a2.island.roads;

import java.util.HashSet;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.DijkstraPathfinder;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Node;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Pathfinder;
import ca.mcmcaster.cas.se2aa4.a2.island.BiMap;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Point;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.RiverPoint;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.LandTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.Tile;

public class DefaultRoads implements RoadGenerator {
    private HashSet<Point> cities = new HashSet<Point>();
    private BiMap<Point, Node> nodes;
    private Graph graph;
    private int numCities;

    public DefaultRoads(int numCities) {
        this.numCities = numCities;
    }

    private void placeCities(Board board) {
        List<Point> points = board.getPoints();
        // Loop through all the polygons endlessly until we get enough cities
        outer: while (this.cities.size() < this.numCities) {
            Point potential = points.get(board.rand.nextInt(0, points.size()));
            // If it has no edges, it's a centroid and we don't use it
            if (board.getNeighbourEdges(potential).size() == 0)
                continue outer;

            // No cities in the middle of rivers
            if(potential instanceof RiverPoint)
                continue outer;

            // Only put cities on land tiles
            for (Tile t : board.getNeighbourTiles(potential)) {
                if (!(t instanceof LandTile)) {
                    continue outer;
                }
            }

            // No cities right next to each other
            for (Point p : board.getNeighbourPoints(potential)) {
                if (this.cities.contains(p)) {
                    continue outer;
                }
            }

            this.cities.add(potential);
        }

        for (Point p : cities) {
            CityType ct = CityType.NONE;
            // Choose city type based on random population
            int pop = board.rand.nextInt(1, 200);
            if (pop > 150) {
                ct = CityType.CITY;
            } else if (pop > 100) {
                ct = CityType.TOWN;
            } else {
                ct = CityType.HAMLET;
            }

            p.setCity(ct);
        }
    }

    private void connectCities(Board board) {
        Pathfinder pf = new DijkstraPathfinder();
        // TODO: Decide on a center node in a smart way
        Point capital = this.cities.iterator().next();
        capital.setCity(CityType.CAPITAL);
        Node center = this.nodes.get(capital);

        for (Point p : cities) {
            Node n = this.nodes.get(p);
            if (n != center) {
                List<Node> path = pf.findShortestPath(this.graph, center, n);
                for (int i = 0; i < path.size() - 1; i++) {
                    // Get the vertices that map to the nodes in the path
                    Point p1 = this.nodes.invGet(path.get(i));
                    Point p2 = this.nodes.invGet(path.get(i + 1));
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
