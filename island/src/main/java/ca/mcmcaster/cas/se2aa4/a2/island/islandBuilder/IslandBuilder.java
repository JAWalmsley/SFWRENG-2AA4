package ca.mcmcaster.cas.se2aa4.a2.island.islandBuilder;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.aquifiers.Aquifiers;
import ca.mcmcaster.cas.se2aa4.a2.island.elevation.ElevationFactory;
import ca.mcmcaster.cas.se2aa4.a2.island.moisture.ExponentialMoisture;
import ca.mcmcaster.cas.se2aa4.a2.island.moisture.LinearMoisture;
import ca.mcmcaster.cas.se2aa4.a2.island.moisture.MoistureProfile;
import ca.mcmcaster.cas.se2aa4.a2.island.rivers.RiverGenerator;
import ca.mcmcaster.cas.se2aa4.a2.island.shape.Shape;
import ca.mcmcaster.cas.se2aa4.a2.island.shape.ShapeFactory;
import ca.mcmcaster.cas.se2aa4.a2.island.lakes.PlaceLakes;
import ca.mcmcaster.cas.se2aa4.a2.island.heatmaps.ElevationHeatmap;
import ca.mcmcaster.cas.se2aa4.a2.island.heatmaps.MoistureHeatmap;

import java.io.IOException;

public class IslandBuilder {
    Board board;

    public IslandBuilder(Board board) {
        this.board = board;
    }
    public void generateIsland(String output, String shapeInput,  String elevationString, int lakeInput, String heatmapType) throws IOException {
        Shape shape = ShapeFactory.getShape(shapeInput, 700);
        shape.draw(board);

        ElevationFactory.setElevation(elevationString, board);

        PlaceLakes lakes = new PlaceLakes();
        lakes.drawLakes(board, lakeInput);

        Aquifiers aq = new Aquifiers();
        aq.placeAquifers(board, 5);

        RiverGenerator rg = new RiverGenerator();
        rg.placeRivers(board, 10);

        MoistureProfile mp = new LinearMoisture();
        mp.drawMoisture(board);
        
        switch (heatmapType) {
            case "m":
                MoistureHeatmap MHeatmap = new MoistureHeatmap();
                MHeatmap.drawHeatMap(board);
                break;
            case "e":
                ElevationHeatmap EHeatmap = new ElevationHeatmap();
                EHeatmap.drawHeatMap(board);
        }
    }

}
