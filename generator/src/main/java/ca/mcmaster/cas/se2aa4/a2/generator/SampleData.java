package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.ArrayList;
import java.util.List;

public class SampleData {
    public static Mesh createData() {
        Mesh m = new Mesh(100, 100, 1);
        Vertex[][] v = new Vertex[10][10];
        for(int x = 0; x < 10; x++) {
            v[x] = new Vertex[10];
            for(int y = 0; y < 10; y++) {
                v[x][y] = new Vertex(x*10, y*10);
            }
        }
        Polygon p = new Polygon(v[1][1]);
        p.addSegment(new Segment(v[0][0], v[0][2]));
        p.addSegment(new Segment(v[0][2], v[2][2]));
        p.addSegment(new Segment(v[2][2], v[2][0]));
        p.addSegment(new Segment(v[2][0], v[0][0]));
        p.setColour(new int[] {255, 0, 0, 130});
        m.polygons.add(p);

        p = new Polygon(v[1][3]);
        p.addSegment(new Segment(v[0][2], v[0][4]));
        p.addSegment(new Segment(v[0][4], v[2][4]));
        p.addSegment(new Segment(v[2][4], v[2][2]));
        p.addSegment(new Segment(v[2][2], v[0][2]));
        p.setColour(new int[] {0, 255, 0, 130});
        m.polygons.add(p);


        return m;
    }
}
