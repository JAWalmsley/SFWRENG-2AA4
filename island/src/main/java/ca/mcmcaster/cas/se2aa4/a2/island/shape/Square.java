package ca.mcmcaster.cas.se2aa4.a2.island.shape;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.LandTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.OceanTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.Tile;

public class Square implements Shape {
    private int border;

    /**
     * Creates a square shape with the given border width
     * @param border The width of ocean on all sides of the island
     */
    public Square(int border) {
        this.border = border;
    }

    @Override
    public void draw(Board board) {
        for (int i = 0; i < board.getNumTiles(); i++) {
            Tile t = board.getTile(i);
            float x = t.getX();
            float y = t.getY();
            if (x > this.border && x < board.getWidth() - this.border && y > this.border
                    && y < board.getHeight() - this.border)
                board.setTile(i, new LandTile(board.getTile(i)));
            else
                board.setTile(i, new OceanTile(board.getTile(i)));
        }
    }

}
