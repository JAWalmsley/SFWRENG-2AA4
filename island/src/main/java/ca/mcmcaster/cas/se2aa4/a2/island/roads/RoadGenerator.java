package ca.mcmcaster.cas.se2aa4.a2.island.roads;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;

public interface RoadGenerator {
    /**
     * Adds cities and roads to a board
     * @param board the board to add to
     */
    public void drawRoads(Board board);
}
