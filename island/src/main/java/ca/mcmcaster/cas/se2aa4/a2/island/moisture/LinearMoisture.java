package ca.mcmcaster.cas.se2aa4.a2.island.moisture;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Point;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.RiverPoint;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.LakeTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.LandTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.OceanTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.Tile;

public class LinearMoisture implements MoistureProfile {

    @Override
    public void drawMoisture(Board board) {
        /**
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
     * Set the moisture level of the given tile
     * 
     * @param board: board that contains tile
     * @param t:     tile to set moisture of
     * @return: true if anything changed, false if tile already had moisture
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

    public int calculateMoisture(Board board, Tile t) {
        int moistureLevel = 0;
        // If tile is water, set moisture level to max
        if (t instanceof LakeTile || t instanceof OceanTile) {
            return MAX_MOISTURE;
        }
        // If tile is an aquifer
        else if (t instanceof LandTile && t.getIsAquifier()) {
            moistureLevel += 8;
        }

        // Add moisture from rivers bordering this tile
        for (Point p : board.getNeighbourPoints(t)) {
            if (p instanceof RiverPoint) {
                moistureLevel += ((RiverPoint) p).getThickness();
            }
        }

        // Tile gets average moisture of their neighbours
        int avgMoisture = 0;
        for (Tile n : board.getNeighbourTiles(t)) {
            avgMoisture += n.getMoistureLevel();
        }
        moistureLevel += avgMoisture / board.getNeighbourTiles(t).size();
        return moistureLevel;
    }

}
