package ca.mcmcaster.cas.se2aa4.a2.island.rivers;

import java.util.ArrayList;
import java.util.List;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Edge;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Point;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.RiverEdge;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.RiverPoint;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.LakeTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.LandTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.OceanTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.Tile;

public class RiverGenerator {
    int THICKNESS_INCREMENT = 6;
    int DEFAULT_THICKNESS = 6;

    public void placeRivers(Board board, int numRivers) {
        for (int i = 0; i < numRivers; i++) {
            Point start;
            while (true) {
                start = board.getPoints().get(board.rand.nextInt(board.getPoints().size()));
                if (start instanceof RiverPoint) {
                    continue;
                }
                boolean onLand = true;
                for (Tile t : board.getNeighbourTiles(start)) {
                    if (!(t instanceof LandTile)) {
                        onLand = false;
                    }
                }
                if (!onLand) {
                    // Can't break from inside the other loop, so we do this instead
                    continue;
                }

                if (board.getNeighbourPoints(start).size() == 0) {
                    continue;
                }
                break;

            }
            this.growRiver(board, start);
        }
    }

    public void growRiver(Board board, Point seed) {
        ArrayList<Point> visited = new ArrayList<Point>();
        int startingThickness = board.rand.nextInt(DEFAULT_THICKNESS);
        int thickness = startingThickness;
        outerloop: while (true) {
            List<Point> potentials = board.getNeighbourPoints(seed);
            Point nextPoint = seed;
            // If we are next to ocean/lake, stop here
            for (Tile t : board.getNeighbourTiles(seed)) {
                if (t instanceof OceanTile || t instanceof LakeTile) {
                    break outerloop;
                }
            }

            // Find attached point with lowest elevation not already visited
            for (Point p : potentials) {
                if (p.getElevation() <= nextPoint.getElevation() && !visited.contains(p)) {
                    nextPoint = p;
                }
            }

            // We couldn't find a lower point to go to, make a lake
            if (nextPoint == seed) {
                // TODO: Create lake here
                ;
                break outerloop;
            }

            

            int nextIdx = board.getPoints().indexOf(nextPoint);
            RiverPoint p = new RiverPoint(nextPoint);
            board.getPoints().set(nextIdx, p);
            p.setThickness(thickness);
            Edge e;
            try {
                e = board.getEdge(seed, nextPoint);
            } catch (IllegalArgumentException ex) {
                // Edge can't be found for some reason, so we stop here
                break outerloop;
            }
            
            board.getEdges().set(board.getEdges().indexOf(e), new RiverEdge(e, thickness));
            
            visited.add(nextPoint);
            // If the next point is already a river, increase thickness
            if (nextPoint instanceof RiverPoint) {
                thickness = startingThickness + THICKNESS_INCREMENT;
            }
            seed = nextPoint;
        }
    }
}
