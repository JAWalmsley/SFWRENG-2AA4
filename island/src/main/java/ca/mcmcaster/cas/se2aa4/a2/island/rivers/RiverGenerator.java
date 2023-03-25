package ca.mcmcaster.cas.se2aa4.a2.island.rivers;

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
    public void placeRivers(Board board, int numRivers){
        for(int i = 0; i < numRivers; i++){
            Point start;
            while(true) {
                start = board.getPoints().get(board.rand.nextInt(board.getPoints().size()));
                for(Tile t: board.getNeighbourTiles(start)){
                    if(!(t instanceof LandTile)) {
                        continue;
                    }
                }
                if(board.getNeighbourPoints(start).size() == 0) {
                    continue;
                }
                break;
                
            }
            this.growRiver(board, start);
        }
    }

    public void growRiver(Board board, Point seed) {
        while(true){
            List<Point> potentials = board.getNeighbourPoints(seed);
            // TODO: this is for testing, set to null
            Point nextPoint = potentials.get(0);
            for(Tile t : board.getNeighbourTiles(seed)) {
                if(t instanceof OceanTile || t instanceof LakeTile) {
                    break;
                }
            }
            for(Point p: potentials) {
                if(p.getElevation() < seed.getElevation() && !(p instanceof RiverPoint)) {
                    nextPoint = p;
                }
            }
            if(nextPoint == null){ 
                // TODO: Create lake here
                break;
            }
            int nextIdx = board.getPoints().indexOf(nextPoint);
            board.getPoints().set(nextIdx, new RiverPoint(nextPoint));
            Edge e = board.getEdge(seed, nextPoint);
            board.getEdges().set(board.getEdges().indexOf(e), new RiverEdge(e));
        }
        
    }
}
