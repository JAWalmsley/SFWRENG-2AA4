package ca.mcmcaster.cas.se2aa4.a2.island.elevation;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.*;

public class ElevationFactory {
    static Elevation chosenElevation;

    public static void setElevation(String elavation, Board board) {
        switch (elavation) {
        case "volcano":
            chosenElevation = new Volcano();
        case "mountain":
            chosenElevation = new MountainRidge();
        default:
            chosenElevation = new MountainRidge();
        }
        chosenElevation.addElevation(board);
        chosenElevation.addElevationToTiles(board);
    }
}