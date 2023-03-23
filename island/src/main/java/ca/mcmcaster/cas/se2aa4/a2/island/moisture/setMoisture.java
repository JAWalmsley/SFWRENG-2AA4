package ca.mcmcaster.cas.se2aa4.a2.island.moisture;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.*;

public class setMoisture {




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
        for (Tile n : board.getNeighbours(t)) {
            if(n instanceof LakeTile || n instanceof OceanTile) {
                moistureLevel++;
            }
        }
        return moistureLevel;
    }

}
