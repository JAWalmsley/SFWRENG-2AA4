package ca.mcmcaster.cas.se2aa4.a2.island.moisture;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.Tile;

public abstract class MoistureProfile {
    int MAX_MOISTURE = 25;

    /**
     * Adds moisture values to the provided Board
     * @param board the board to draw moisture onto
     */
    public void drawMoisture(Board board) {
        /*
         * We keep running iterations of the calculation until we have an iteraiton
         * where nothing changes. This guarantees all tiles get set to a correct moisture level,
         * even if their neighbours didnt have a moisture last time.
         */
        boolean changed = true;
        while (changed) {
            changed = false;
            for (Tile t : board.getTiles()) {
                // Changed is true if anything changed
                changed = changed | setMoistureLevel(board, t);
            }
        }
    }

    /**
     * Sets the moisture level of a given Tile
     * @param board the board containing the Tile
     * @param t the Tile to set moisture level of
     * @return true if the moisture level changed, false otherwise
     */
    public boolean setMoistureLevel(Board board, Tile t) {
        if (t.getMoistureLevel() != 0) {
            // Don't change tiles that already have their moisture
            return false;
        }
        int moistureLevel = calculateMoisture(board, t);

        // Make sure moisture level is within bounds
        moistureLevel = Math.min(moistureLevel, MAX_MOISTURE);
        if (moistureLevel == t.getMoistureLevel()) {
            return false;
        }
        t.setMoistureLevel(moistureLevel);
        return true;
    }

    /**
     * Calculates the moisture at the given Tile
     * @param board the board containing the Tile
     * @param t the Tile to assign moisture to
     * @return the amount of moisture at that Tile
     */
    public abstract int calculateMoisture(Board board, Tile t);
}
