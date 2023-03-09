package ca.mcmcaster.cas.se2aa4.a2.island.shape;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;

public interface Shape {
    /**
     * Draws the shape onto the board
     * @param board
     */
    public void draw(Board board);
}
