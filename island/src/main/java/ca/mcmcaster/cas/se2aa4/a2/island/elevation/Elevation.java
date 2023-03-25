package ca.mcmcaster.cas.se2aa4.a2.island.elevation;
import java.util.List;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Point;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.Tile;

public abstract class Elevation {
    /**
     * Add Elevation to vertecies
     * @param board
     */
    public abstract void addElevation(Board board);

    /**
     * Adds the elavation to the tiles, from vertex avg
     * @param board
     */
    public void addElevationToTiles(Board board) {
        for(Tile t : board.getTiles()) {
            List<Point> points = board.getNeighbourPoints(t);
            int sum = 0;
            for(Point p : points) {
                sum += p.getElevation();
            }
            int avg = sum / points.size();
            t.setElevation(avg);
        }
    }
}
