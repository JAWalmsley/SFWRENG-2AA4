package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class GraphicRenderer {

    private static final int SEGMENT_THICKNESS = 1;
    private static final int VERTEX_THICKNESS = 2;

    public void render(Mesh aMesh, Graphics2D canvas) {
        List<Vertex> vertices = aMesh.getVerticesList();
        List<Segment> segments = aMesh.getSegmentsList();
        for (Polygon p : aMesh.getPolygonsList()) {
            ArrayList<Integer> xpoints = new ArrayList<>();
            ArrayList<Integer> ypoints = new ArrayList<>();
            for (int i : p.getSegmentIdxsList()) {
                Vertex v1 = vertices.get(segments.get(i).getV1Idx());
                Vertex v2 = vertices.get(segments.get(i).getV2Idx());
                xpoints.add((int) v1.getX());
                ypoints.add((int) v1.getY());
                xpoints.add((int) v2.getX());
                ypoints.add((int) v2.getY());
            }
            int[] xpointsarr = xpoints.stream().mapToInt(Integer::intValue).toArray();
            int[] ypointsarr = ypoints.stream().mapToInt(Integer::intValue).toArray();
            Color old = canvas.getColor();
            canvas.setColor(extractColor(p.getPropertiesList()));
            java.awt.Polygon poly = new java.awt.Polygon(xpointsarr, ypointsarr, xpoints.size());
            // canvas.draw(poly);
            canvas.fill(poly);
            canvas.setColor(old);
        }

        for (Segment s : segments) {
            canvas.setStroke(new BasicStroke(SEGMENT_THICKNESS));

            Vertex v1 = vertices.get(s.getV1Idx());
            Vertex v2 = vertices.get(s.getV2Idx());
            Color old = canvas.getColor();
            canvas.setColor(extractColor(s.getPropertiesList()));
            
            Line2D line = new Line2D.Double(v1.getX(), v1.getY(), v2.getX(), v2.getY());
            canvas.draw(line);
            canvas.setColor(old);
        }

        for (Vertex v : vertices) {

            double centre_x = v.getX() - (VERTEX_THICKNESS / 2.0d);
            double centre_y = v.getY() - (VERTEX_THICKNESS / 2.0d);
            Color old = canvas.getColor();
            canvas.setColor(extractColor(v.getPropertiesList()));
            Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, VERTEX_THICKNESS, VERTEX_THICKNESS);
            canvas.fill(point);
            canvas.setColor(old);
        }
    }

    private Color extractColor(List<Property> properties) {
        String val = null;
        for (Property p : properties) {
            if (p.getKey().equals("rgb_color")) {
                // System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        if (val == null)
            return Color.BLACK;
        String[] raw = val.split(",");
        int red = Integer.parseInt(raw[0]);
        int green = Integer.parseInt(raw[1]);
        int blue = Integer.parseInt(raw[2]);
        return new Color(red, green, blue);
    }
}
