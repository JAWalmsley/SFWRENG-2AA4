package ca.mcmcaster.cas.se2aa4.a2.island.elevation;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.*;

public class ElevationFactory {
    static Elevation chosenElevation;

    public static void setElevation(String elevation, Board board) {
        switch (elevation) {
        case "volcano":
            chosenElevation = new Volcano();
            break;
        case "mountain":
            chosenElevation = new MountainRidge();
            break;
        default:
            chosenElevation = new MountainRidge();
        }
        chosenElevation.addElevation(board);
        chosenElevation.addElevationToTiles(board);
    }
}