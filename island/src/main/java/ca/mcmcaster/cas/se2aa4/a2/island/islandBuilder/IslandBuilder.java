package ca.mcmcaster.cas.se2aa4.a2.island.islandBuilder;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.moisture.SetMoisture;
import ca.mcmcaster.cas.se2aa4.a2.island.shape.Shape;
import ca.mcmcaster.cas.se2aa4.a2.island.shape.ShapeFactory;
import ca.mcmcaster.cas.se2aa4.a2.island.lakes.PlaceLakes;
import ca.mcmcaster.cas.se2aa4.a2.island.heatmaps.MoistureHeatmap;

import java.io.IOException;

public class IslandBuilder {
    Board board;

    public IslandBuilder(Board board) {
        this.board = board;
    }
    public void generateIsland(String output, String shapeInput, int lakeInput) throws IOException {
        Shape shape = ShapeFactory.getShape(shapeInput, 700);
        shape.draw(board);
        PlaceLakes lakes = new PlaceLakes();
        lakes.drawLakes(board, lakeInput);
        SetMoisture moisture = new SetMoisture();
        moisture.setMoistureLevel(board);
        // MoistureHeatmap heatmap = new MoistureHeatmap();
        // heatmap.drawHeatMap(board);

        

    }

}
