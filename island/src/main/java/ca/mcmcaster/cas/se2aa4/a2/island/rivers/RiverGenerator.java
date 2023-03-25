package ca.mcmcaster.cas.se2aa4.a2.island.rivers;

import java.util.ArrayList;
import java.util.List;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Colour;
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
    public void placeRivers(Board board, int numRivers) {
        for (int i = 0; i < numRivers; i++) {
            Point start;
            while (true) {
                start = board.getPoints().get(board.rand.nextInt(board.getPoints().size()));
                boolean onLand = true;
                for (Tile t : board.getNeighbourTiles(start)) {
                    if (!(t instanceof LandTile)) {
                        onLand = false;
                    }
                }
                if(!onLand) {
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
        int thickness = 6;
        outerloop:
        while (true) {
            List<Point> potentials = board.getNeighbourPoints(seed);
            Point nextPoint = seed;
            for (Tile t : board.getNeighbourTiles(seed)) {
                if (t instanceof OceanTile || t instanceof LakeTile) {
                    break outerloop;
                }
            }
            for (Point p : potentials) {
                if(p instanceof RiverPoint && !(visited.contains(p))){
                    thickness += THICKNESS_INCREMENT;
                }
                if (p.getElevation() <= nextPoint.getElevation() && !(p instanceof RiverPoint)) {
                    nextPoint = p;
                }
            }
            if (nextPoint == seed) {
                // TODO: Create lake here
                ;
                break outerloop;
            }
            int nextIdx = board.getPoints().indexOf(nextPoint);
            board.getPoints().set(nextIdx, new RiverPoint(nextPoint));
            Edge e = board.getEdge(seed, nextPoint);
            board.getEdges().set(board.getEdges().indexOf(e), new RiverEdge(e, thickness));
            visited.add(nextPoint);
            seed = nextPoint;
        }
    }
}
