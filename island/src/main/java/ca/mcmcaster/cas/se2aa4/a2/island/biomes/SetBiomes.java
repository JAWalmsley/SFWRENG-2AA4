package ca.mcmcaster.cas.se2aa4.a2.island.biomes;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.*;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.*;

public class SetBiomes {
    public void setBiomes(Board board, String biome) {
        int numTiles = board.getNumTiles();
        for (int i = 0; i < numTiles; i++) {
            Tile t = board.getTile(i);
            if (t instanceof LandTile) {
                int moistureLevel = t.getMoistureLevel();
                float elevation = t.getElevation();
                if (moistureLevel <= 8){
                    if(elevation<=18) {
                        board.setTile(i, new LowMoistureLowElev(t,biome));
                    } else {
                        board.setTile(i, new LowMoistureHighElev(t,biome));
                    }
                } else {
                    if(elevation<=18) {
                        board.setTile(i, new HighMoistureLowElev(t,biome));
                    } else {
                        board.setTile(i, new HighMoistureHighElev(t,biome));
                    }
                }
            }
        }
    }
}
