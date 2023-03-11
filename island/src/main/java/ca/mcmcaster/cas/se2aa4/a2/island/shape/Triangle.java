package ca.mcmcaster.cas.se2aa4.a2.island.shape;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.LandTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.OceanTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tile;

public class Triangle implements Shape {
    private int size;

    public Triangle(int size) {
        this.size = size;
    }

    @Override
    public void draw(Board board) {
        for (int i = 0; i < board.getNumTiles(); i++) {
            Tile t = board.getTile(i);
            float x = t.getX() - board.getWidth() / 2;
            float y = board.getHeight() / 2 - t.getY() + this.size/2;
            if (y > 0 && x + this.size > y && x - this.size < -y)
                board.setTile(i, new LandTile(board.getTile(i)));
            else
                board.setTile(i, new OceanTile(board.getTile(i)));
        }
    }

}
