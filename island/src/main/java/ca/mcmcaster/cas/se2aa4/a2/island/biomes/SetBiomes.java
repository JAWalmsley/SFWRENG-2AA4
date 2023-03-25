package ca.mcmcaster.cas.se2aa4.a2.island.biomes;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.*;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Tiles.*;

public class SetBiomes {
    public void setBiomes(Board board) {
        int numTiles = board.getNumTiles();
        String biome = "test";
        for (int i = 0; i < numTiles; i++) {
            Tile t = board.getTile(i);
            if (t instanceof LandTile) {
                int moistureLevel = t.getMoistureLevel();
                if (moistureLevel <= 8){
                    board.setTile(i, new LowMoistureTile(t,biome));
                } else if (moistureLevel <= 17) {
                    float elevation = t.getElevation();
                    if(elevation<=12.5) {
                        board.setTile(i, new MidMoistureLowElev(t,biome));
                    } else {
                        board.setTile(i, new MidMoistureHighElev(t,biome));
                    }
                } else {
                    board.setTile(i, new HighMoisture(t,biome));
                }
            }
        }
    }
}
