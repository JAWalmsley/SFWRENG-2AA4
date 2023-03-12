package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class GenerateMeshTest {

    @Test
    /**
     * This test  creates a square mesh and checks that the mesh is not null.
     */
    public void squareMeshIsNotNull() {
        GenerateMesh generator = new GenerateMesh();
        Mesh mesh = generator.generatePolygonMesh("grid", 10);
        Structs.Mesh sMesh = mesh.getIOMesh();
        assertNotNull(sMesh);
        assertFalse(sMesh.getPolygonsCount() == 0);
    }


    @Test
    /**
     * This test checks that no two vertices are the same.
     */
    public void verticesDoNotOverlap() {
        GenerateMesh generator = new GenerateMesh();
        Mesh aMesh = generator.generatePolygonMesh("grid", 10);
        Structs.Mesh sMesh = aMesh.getIOMesh();
        List<Structs.Vertex> vertexArray = sMesh.getVerticesList();

        for (int i = 0; i < vertexArray.size(); i++) {
            for (int k = 0; k < vertexArray.size(); k++) {
                if (i != k) {
                    assertFalse(vertexArray.get(i) == vertexArray.get(k));
                }
            }
        }
    }

    @Test
    /**
     * This test checks that all segments belong to a polygon.
     */
    public void segmentsAreInPolygons() {
        GenerateMesh generator = new GenerateMesh();
        Mesh aMesh = generator.generatePolygonMesh("irregular", 10);
        Structs.Mesh sMesh = aMesh.getIOMesh();
        List<Structs.Segment> segmentArray = sMesh.getSegmentsList();
        List<Structs.Polygon> polygonArray = sMesh.getPolygonsList();

        for (Structs.Segment seg : segmentArray) {
            boolean found = false;
            for (Structs.Polygon polygon : polygonArray) {
                for (int i : polygon.getSegmentIdxsList()) {
                    if (segmentArray.get(i) == seg) {
                        found = true;
                    }
                }
            }
            assertTrue(found);
        }
    }

}