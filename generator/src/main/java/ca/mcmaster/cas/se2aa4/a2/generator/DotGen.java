package ca.mcmaster.cas.se2aa4.a2.generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

public class DotGen {

    private final int width = 500;
    private final int height = 500;
    private final int square_size = 20;

    public Mesh generate() {
        ArrayList<Vertex> vertices = new ArrayList<>();
        ArrayList<Segment> segments = new ArrayList<>();
        // Create all the vertices
        int highest_vertex_index = -1;
        for (int x = 0; x < width; x += square_size) {
            for (int y = 0; y < height; y += square_size) {
                Vertex v1 = Vertex.newBuilder().setX((double) x).setY((double) y).build();
                Vertex v2 = Vertex.newBuilder().setX((double) x + square_size).setY((double) y).build();
                Vertex v3 = Vertex.newBuilder().setX((double) x).setY((double) y + square_size).build();
                Vertex v4 = Vertex.newBuilder().setX((double) x + square_size)
                        .setY((double) y + square_size).build();
                highest_vertex_index += 4;

                vertices.add(v1);
                vertices.add(v2);
                vertices.add(v3);
                vertices.add(v4);
                segments.add(Segment.newBuilder().setV1Idx(highest_vertex_index)
                        .setV2Idx(highest_vertex_index - 2).build());
                segments.add(Segment.newBuilder().setV1Idx(highest_vertex_index - 1)
                        .setV2Idx(highest_vertex_index - 3).build());
                segments.add(Segment.newBuilder().setV1Idx(highest_vertex_index - 2)
                        .setV2Idx(highest_vertex_index - 3).build());
                segments.add(Segment.newBuilder().setV1Idx(highest_vertex_index)
                        .setV2Idx(highest_vertex_index - 1).build());
            }
        }
        // Distribute colors randomly. Vertices are immutable, need to enrich them
        ArrayList<Vertex> verticesWithColors = new ArrayList<>();
        ArrayList<Segment> segmentsWithColors = new ArrayList<>();
        Random bag = new Random();
        for (Vertex v : vertices) {
            int red = bag.nextInt(255);
            int green = bag.nextInt(255);
            int blue = bag.nextInt(255);
            String colorCode = red + "," + green + "," + blue;
            Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
            Vertex colored = Vertex.newBuilder(v).addProperties(color).build();
            // System.out.println(colored.getPropertiesList());
            verticesWithColors.add(colored);
        }
        for (Segment s : segments) {
            Vertex v1 = verticesWithColors.get(s.getV1Idx());
            Vertex v2 = verticesWithColors.get(s.getV2Idx());
            int color1[] = Arrays.stream(v1.getProperties(0).getValue().split(","))
                    .mapToInt(Integer::parseInt).toArray();
            int color2[] = Arrays.stream(v2.getProperties(0).getValue().split(","))
                    .mapToInt(Integer::parseInt).toArray();

            int red = (color1[0] + color2[0]) / 2;
            int green = (color1[1] + color2[1]) / 2;
            int blue = (color1[2] + color2[2]) / 2;
            String colorCode = red + "," + green + "," + blue;
            Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
            Segment colored = Segment.newBuilder(s).addProperties(color).build();
            segmentsWithColors.add(colored);
        }

        return Mesh.newBuilder().addAllVertices(verticesWithColors).addAllSegments(segmentsWithColors).build();
    }

}
