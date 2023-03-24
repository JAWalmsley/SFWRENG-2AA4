package ca.mcmcaster.cas.se2aa4.a2.island.shape;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.BeachTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.LandTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.OceanTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.Tile;

public class Circle implements Shape {
    int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw(Board board) {
        float centerX = board.getWidth() / 2;
        float centery = board.getHeight() / 2;
        for(int i = 0; i < board.getNumTiles(); i++) {
            Tile t = board.getTile(i);
            float x2 = (t.getX() - centerX) * (t.getX() - centerX);
            float y2 = (t.getY() - centery) * (t.getY() - centery);
            float r2 = this.radius * this.radius;
            if((x2 + y2) < r2)
                board.setTile(i, new LandTile(t));
            else
                board.setTile(i, new OceanTile(t));
        }
        for(int i = 0; i < board.getNumTiles(); i++) {
            Tile t = board.getTile(i);
            if(t instanceof LandTile) {
                for(Tile n : board.getNeighbourTiles(t)) {
                    if(n instanceof OceanTile) {
                        board.setTile(i, new BeachTile(t));
                    }
                }
            }
        }
    }
}
