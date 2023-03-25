package ca.mcmcaster.cas.se2aa4.a2.island.elevation;

import java.util.List;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Point;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.OceanTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.Tile;

public class Volcano extends Elevation {
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
            float y;
            float distanceToCenter;
            for(Point v : points) {
                x = v.getX();
                y = v.getY();
                
                distanceToCenter = (float) Math.sqrt(Math.pow(x - board.getWidth()/2, 2) + Math.pow(y - board.getHeight()/2, 2));
                v.setElevation((int)(25 * (1 - distanceToCenter/(board.getWidth()/2))));
            }
        }
    }
}
