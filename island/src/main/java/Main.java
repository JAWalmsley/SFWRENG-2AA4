import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Board;

import ca.mcmaster.cas.se2aa4.a2.generator.Mesh;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        // TODO: command line parameters
        String input = "sample.mesh";
        String output = "island.mesh";

        // Getting width and height for the canvas
        Structs.Mesh aMesh = new MeshFactory().read(input);
        Mesh m = new Mesh(aMesh);
        Board board = new Board(m);
        
        board.export(output);
    }
}
