import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;
import ca.mcmcaster.cas.se2aa4.a2.island.shape.Circle;
import ca.mcmcaster.cas.se2aa4.a2.island.shape.Lagoon;
import ca.mcmcaster.cas.se2aa4.a2.island.shape.Shape;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        // TODO: command line parameters
        String input = "sample.mesh";
        String output = "island.mesh";

        // Getting width and height for the canvas
        Structs.Mesh aMesh = new MeshFactory().read(input);
        Board board = new Board(aMesh);
        Shape shape = new Lagoon(900, 600);
        shape.draw(board);
        board.export(output);
    }
}
