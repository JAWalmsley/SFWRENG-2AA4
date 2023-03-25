package ca.mcmcaster.cas.se2aa4.a2.island.elevation;

import java.util.List;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Point;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.OceanTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.Tile;

public class MountainRidge extends Elevation {
    /**
     * Add Elevation to vertecies
     * @param board
     */
    public void addElevation(Board board) {
        for(Tile t : board.getTiles()) {
            if(t instanceof OceanTile) {
                continue;
            }
            List<Point> points =  board.getNeighbourPoints(t);
            
            float x;
            float distanceToRidge;
            for(Point v : points) {
                x = v.getX();

                distanceToRidge = (float) Math.abs((x - board.getWidth()/2));
                v.setElevation((int)(25 * (1 - distanceToRidge/(board.getWidth()/2))));
            }
        }
    }
}

