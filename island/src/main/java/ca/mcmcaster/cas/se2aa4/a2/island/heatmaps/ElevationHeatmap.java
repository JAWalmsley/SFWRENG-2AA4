package ca.mcmcaster.cas.se2aa4.a2.island.heatmaps;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Colour;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tile;

public class ElevationHeatmap {

    public void drawHeatMap(Board board) {
        int numTiles = board.getNumTiles();
        for (int i = 0; i < numTiles; i++) {
            Tile t = board.getTile(i);
//            int elevationLevel = t.getElevationLevel();
//            int colourGreyscale = (25 - elevationLevel)*10;
//            Colour colour = new Colour(colourGreyscale,colourGreyscale,colourGreyscale);
//            t.setColour(colour);
        }
    }
}
