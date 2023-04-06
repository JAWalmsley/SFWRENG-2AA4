package ca.mcmcaster.cas.se2aa4.a2.island.islandBuilder;

import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.aquifiers.Aquifiers;
import ca.mcmcaster.cas.se2aa4.a2.island.elevation.ElevationFactory;
import ca.mcmcaster.cas.se2aa4.a2.island.moisture.MoistureFactory;
import ca.mcmcaster.cas.se2aa4.a2.island.moisture.MoistureProfile;
import ca.mcmcaster.cas.se2aa4.a2.island.rivers.RiverGenerator;
import ca.mcmcaster.cas.se2aa4.a2.island.roads.RoadFactory;
import ca.mcmcaster.cas.se2aa4.a2.island.roads.RoadGenerator;
import ca.mcmcaster.cas.se2aa4.a2.island.shape.Lagoon;
import ca.mcmcaster.cas.se2aa4.a2.island.shape.Shape;
import ca.mcmcaster.cas.se2aa4.a2.island.shape.ShapeFactory;
import ca.mcmcaster.cas.se2aa4.a2.island.lakes.PlaceLakes;
import ca.mcmcaster.cas.se2aa4.a2.island.heatmaps.ElevationHeatmap;
import ca.mcmcaster.cas.se2aa4.a2.island.heatmaps.MoistureHeatmap;
import ca.mcmcaster.cas.se2aa4.a2.island.biomes.SetBiomes;

public class IslandBuilder {
    Board board;

    public IslandBuilder(Board board) {
        this.board = board;
    }

    /**
     * Generate all features of the island
     * 
     * @param output          output filename
     * @param shapeInput      shape type, see --help for info
     * @param elevationString elevation profile, see --help for info
     * @param lakeInput       maximum number of lakes
     * @param heatmapType     what heatmap to draw, "m" for moisture, "e" for
     *                        elevation, "i" for none (island)
     * @param soilProfile     soil moisture absorption profile, see --help for info
     * @param mode            the mode of generation, lagoon or normal
     */
    public void generateIsland(String output, String shapeInput, String elevationString, int lakeInput,
            String heatmapType, String soilProfile, int numAquifers, int numRivers, String mode, String biome, int numCities) {
        if (mode.equals("lagoon")) {
            int dimension = Math.min(board.getWidth(), board.getHeight());
            int outerRadius = dimension/2 - (int)(dimension*0.1); 
            int innerRadius = dimension/2 - (int)(dimension*0.25); 
            Shape shape = new Lagoon(outerRadius, innerRadius);
            shape.draw(board);
        } else {
            Shape shape = ShapeFactory.getShape(shapeInput, 700);
            shape.draw(board);

            ElevationFactory.setElevation(elevationString, board);

            PlaceLakes lakes = new PlaceLakes();
            lakes.drawLakes(board, lakeInput);

            Aquifiers aq = new Aquifiers();
            aq.placeAquifers(board, numAquifers);

            RiverGenerator rg = new RiverGenerator();
            rg.placeRivers(board, numRivers);

            MoistureProfile mp = MoistureFactory.getMoistureProfile(soilProfile);
            mp.drawMoisture(board);

            RoadGenerator rdg = RoadFactory.create(numCities);
            rdg.drawRoads(board);
        }
        switch (heatmapType) {
            case "m":
                MoistureHeatmap MHeatmap = new MoistureHeatmap();
                MHeatmap.drawHeatMap(board);
                break;
            case "e":
                ElevationHeatmap EHeatmap = new ElevationHeatmap();
                EHeatmap.drawHeatMap(board);
                break;
            case "i":
                SetBiomes biomes = new SetBiomes();
                biomes.setBiomes(board, biome);
        }
    }

}
