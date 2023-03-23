package ca.mcmcaster.cas.se2aa4.a2.island.lakes;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.*;

public class placeLakes {

    public void drawLakes(Board board, int numberOfLakes) {
        if(numberOfLakes>board.getNumTiles()) {
            for(int i = 0; i < board.getNumTiles(); i++) {
                Tile t = board.getTile(i);
                if (t instanceof LandTile) {
                    board.setTile(i, new LakeTile(board.getTile(i)));
                }
            }
        } else {
            int i = 0;
            while (i < numberOfLakes) {
                int index = board.rand.nextInt(board.getNumTiles());
                Tile t = board.getTile(index);

                if (t instanceof LandTile) {
                    board.setTile(index, new LakeTile(board.getTile(index)));
                    i++;
                    for(int nID : board.getNeighboursID(t)) {
                        Tile n = board.getTile(nID);
                        if(n instanceof LandTile && board.rand.nextDouble() > 0.4) {
                            board.setTile(nID, new LakeTile(n));
                        }
                    }
                }
            }
        }
    }

}
