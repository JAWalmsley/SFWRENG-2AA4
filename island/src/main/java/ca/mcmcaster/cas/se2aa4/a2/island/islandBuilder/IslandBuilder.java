package ca.mcmcaster.cas.se2aa4.a2.island.islandBuilder;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.aquifiers.Aquifiers;
import ca.mcmcaster.cas.se2aa4.a2.island.moisture.SetMoisture;
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
    public void generateIsland(String output, String shapeInput, int lakeInput, String formatInput) throws IOException {
        Shape shape = ShapeFactory.getShape(shapeInput, 700);
        shape.draw(board);
        PlaceLakes lakes = new PlaceLakes();
        lakes.drawLakes(board, lakeInput);
        Aquifiers aq = new Aquifiers();
        aq.placeAquifers(board, 5);
        SetMoisture moisture = new SetMoisture();
        moisture.setMoistureLevel(board);
        switch (formatInput) {
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
