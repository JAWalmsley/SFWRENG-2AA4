package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.ArrayList;
import java.util.List;

public class SampleData {
    static Vertex[][] v = new Vertex[10][10];

    public static void setup() {
        for (int x = 0; x < 10; x++) {
            v[x] = new Vertex[10];
            for (int y = 0; y < 10; y++) {
                v[x][y] = new Vertex(x * 10, y * 10);
            }
        }
    }

    public static Mesh createData() {
        setup();
        Mesh m = new Mesh(100, 100, 1);

        // Polygon p = new Polygon(v[1][1]);
        // p.addSegment(new Segment(v[0][0], v[0][2]));
        // p.addSegment(new Segment(v[0][2], v[2][2]));
        // p.addSegment(new Segment(v[2][2], v[2][0]));
        // p.addSegment(new Segment(v[2][0], v[0][0]));
        // p.setColour(new int[] { 255, 0, 0, 130 });
        // m.polygons.add(p);

        // p = new Polygon(v[1][3]);
        // p.addSegment(new Segment(v[0][2], v[0][4]));
        // p.addSegment(new Segment(v[0][4], v[2][4]));
        // p.addSegment(new Segment(v[2][4], v[2][2]));
        // p.addSegment(new Segment(v[2][2], v[0][2]));
        // p.setColour(new int[] { 0, 255, 0, 130 });
        // m.polygons.add(p);

        // p = new Polygon(v[3][1]);
        // p.addSegment(new Segment(v[2][0], v[2][2]));
        // p.addSegment(new Segment(v[2][2], v[4][2]));
        // p.addSegment(new Segment(v[4][2], v[4][0]));
        // p.addSegment(new Segment(v[4][0], v[2][0]));
        // p.setColour(new int[] { 0, 0, 255, 130 });
        // m.polygons.add(p);

        // p = new Polygon(v[5][1]);
        // p.addSegment(new Segment(v[4][0], v[4][2]));
        // p.addSegment(new Segment(v[4][2], v[6][2]));
        // p.addSegment(new Segment(v[6][2], v[6][0]));
        // p.addSegment(new Segment(v[6][0], v[4][0]));
        // p.setColour(new int[] { 0, 0, 255, 130 });
        // m.polygons.add(p);

        m.polygons.add(fromCent(1, 1));
        m.polygons.add(fromCent(1, 3));
        m.polygons.add(fromCent(3, 3));
        m.polygons.add(fromCent(5, 3));
        m.polygons.add(fromCent(5, 5));
        m.polygons.add(fromCent(1, 5));
        m.polygons.add(fromCent(1, 7));
        return m;
    }

    public static Polygon fromCent(int x, int y) {

        Polygon p = new Polygon(v[x][y]);
        p.addSegment(new Segment(v[x - 1][y - 1], v[x - 1][y + 1]));
        p.addSegment(new Segment(v[x - 1][y + 1], v[x + 1][y + 1]));
        p.addSegment(new Segment(v[x + 1][y + 1], v[x + 1][y - 1]));
        p.addSegment(new Segment(v[x + 1][y - 1], v[x - 1][y - 1]));
        return p;
    }
}
