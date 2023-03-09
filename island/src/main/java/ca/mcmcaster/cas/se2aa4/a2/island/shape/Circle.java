package ca.mcmcaster.cas.se2aa4.a2.island.shape;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.BeachTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.LandTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.OceanTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tile;

public class Circle implements Shape {
    int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw(Board board) {
        float centerX = board.getWidth() / 2;
        float centery = board.getHeight() / 2;
        for(int i = 0; i < board.getTiles().size(); i++) {
            Tile t = board.getTiles().get(i);
            float x2 = (t.getX() - centerX) * (t.getX() - centerX);
            float y2 = (t.getY() - centery) * (t.getY() - centery);
            float r2 = this.radius * this.radius;
            if((x2 + y2) < r2)
                board.getTiles().set(i, new LandTile(t));
            else
                board.getTiles().set(i, new OceanTile(t));
        }
        for(int i = 0; i < board.getTiles().size(); i++) {
            Tile t = board.getTiles().get(i);
            if(t instanceof LandTile) {
                for(Tile n : t.getNeighbours()) {
                    if(n instanceof OceanTile) {
                        board.getTiles().set(i, new BeachTile(t));
                    }
                }
            }
        }
    }
}
