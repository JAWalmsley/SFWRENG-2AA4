package ca.mcmcaster.cas.se2aa4.a2.island.aquifiers;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.LandTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tile;

public class Aquifiers {
    public void placeAquifers(Board board, int numberOfAquifers) {
        if(numberOfAquifers>board.getNumTiles()) {
            for(int i = 0; i < board.getNumTiles(); i++) {
                Tile t = board.getTile(i);
                if (t instanceof LandTile) {
                    t.setIsAquifier(true);
                }
            }
        } else {
            int i = 0;
            while (i < numberOfAquifers) {
                int index = board.rand.nextInt(board.getNumTiles());
                Tile t = board.getTile(index);

                if (t instanceof LandTile) {
                    t.setIsAquifier(true);
                    i++;
                }
            }
        }
    }
}
