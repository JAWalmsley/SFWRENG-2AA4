package ca.mcmcaster.cas.se2aa4.a2.island.islandBuilder;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.moisture.setMoisture;
import ca.mcmcaster.cas.se2aa4.a2.island.shape.Shape;
import ca.mcmcaster.cas.se2aa4.a2.island.shape.ShapeFactory;
import ca.mcmcaster.cas.se2aa4.a2.island.lakes.placeLakes;
import ca.mcmcaster.cas.se2aa4.a2.island.heatmaps.moistureHeatmap;

import java.io.IOException;

public class islandBuilder {
    Board board;

    public islandBuilder(Board board) {
        this.board = board;
    }
    public void generateIsland(String output, String shapeInput, int lakeInput) throws IOException {
        Shape shape = ShapeFactory.getShape(shapeInput, 700);
        shape.draw(board);
        placeLakes lakes = new placeLakes();
        lakes.drawLakes(board, lakeInput);
        setMoisture moisture = new setMoisture();
        moisture.setMoistureLevel(board);
        moistureHeatmap heatmap = new moistureHeatmap();
        heatmap.drawHeatMap(board);

        board.export(output);

    }

}
