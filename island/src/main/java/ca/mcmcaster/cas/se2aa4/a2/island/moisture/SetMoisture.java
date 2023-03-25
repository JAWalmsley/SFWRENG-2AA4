package ca.mcmcaster.cas.se2aa4.a2.island.moisture;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.*;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.LakeTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.LandTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.OceanTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.Tile;

public class SetMoisture {




    public void setMoistureLevel(Board board) {
        int numTiles = board.getNumTiles();
        for (int i = 0; i < numTiles; i++) {
           Tile t = board.getTile(i);
           t.setMoistureLevel(calculateMoistureLevel(board, t));
        }
    }

    public int calculateMoistureLevel(Board board, Tile t) {
        int moistureLevel = 0;
        if (t instanceof LakeTile || t instanceof OceanTile) {
            return 25;
        }
        else if (t instanceof LandTile) {
            if (t.getIsAquifier()) {
                moistureLevel+=8;
            }
        }
        for (Tile n : board.getNeighbourTiles(t)) {
            if(n instanceof LakeTile || n instanceof OceanTile) {
                moistureLevel+=4;
            }
            else if(n instanceof LandTile) {
                if (n.getIsAquifier()) {
                    moistureLevel+=3;
                }
            }
        }
        for (Point p : board.getNeighbourPoints(t)) {
           if (p instanceof RiverPoint) {
               moistureLevel += ((RiverPoint) p).getThickness();
           }
        }
        return moistureLevel;
    }

}
