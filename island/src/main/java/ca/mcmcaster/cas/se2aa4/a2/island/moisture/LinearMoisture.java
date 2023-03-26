package ca.mcmcaster.cas.se2aa4.a2.island.moisture;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Point;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.RiverPoint;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.LakeTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.LandTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.OceanTile;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.Tile;

public class LinearMoisture extends MoistureProfile {

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
