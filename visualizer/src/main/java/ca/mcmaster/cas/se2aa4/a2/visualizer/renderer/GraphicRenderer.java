package ca.mcmaster.cas.se2aa4.a2.visualizer.renderer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties.CityProperty;
import ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties.ColorProperty;
import ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties.RoadProperty;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.util.Iterator;
import java.util.Optional;

public class GraphicRenderer implements Renderer {

    private static final int CITY_RADIUS = 40;
    private static final int ROAD_WIDTH = 6;

    public void render(Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.2f);
        canvas.setStroke(stroke);
        drawPolygons(aMesh, canvas);
        drawSegments(aMesh, canvas);
        drawVertices(aMesh, canvas);
    }

    private void drawPolygons(Mesh aMesh, Graphics2D canvas) {
        for (Structs.Polygon p : aMesh.getPolygonsList()) {
            drawAPolygon(p, aMesh, canvas);
        }
    }

    private void drawSegments(Mesh aMesh, Graphics2D canvas) {
        for (Structs.Segment s : aMesh.getSegmentsList()) {
            drawASegment(s, aMesh, canvas);
        }
    }

    private void drawVertices(Mesh aMesh, Graphics2D canvas) {
        for (Structs.Vertex v : aMesh.getVerticesList()) {
            drawAVertex(v, aMesh, canvas);
        }
    }

    private void drawAPolygon(Structs.Polygon p, Mesh aMesh, Graphics2D canvas) {
        Hull hull = new Hull();
        for (Integer segmentIdx : p.getSegmentIdxsList()) {
            hull.add(aMesh.getSegments(segmentIdx), aMesh);
        }
        Path2D path = new Path2D.Float();
        Iterator<Vertex> vertices = hull.iterator();
        Vertex current = vertices.next();
        path.moveTo(current.getX(), current.getY());
        while (vertices.hasNext()) {
            current = vertices.next();
            path.lineTo(current.getX(), current.getY());
        }
        path.closePath();
        canvas.draw(path);
        Optional<Color> fill = new ColorProperty().extract(p.getPropertiesList());
        if (fill.isPresent()) {
            Color old = canvas.getColor();
            canvas.setColor(fill.get());
            canvas.fill(path);
            canvas.setColor(old);
        }
    }

    private void drawASegment(Structs.Segment s, Mesh aMesh, Graphics2D canvas) {
        Vertex v1 = aMesh.getVertices(s.getV1Idx());
        Vertex v2 = aMesh.getVertices(s.getV2Idx());

        Color old = canvas.getColor();
        Optional<Color> fill = new ColorProperty().extract(s.getPropertiesList());
        Optional<Boolean> road = new RoadProperty().extract(s.getPropertiesList());
        if (road.isPresent() && road.get()) {
            canvas.setColor(Color.BLACK);
        } else if (fill.isPresent()) {
            canvas.setColor(fill.get());
        }
        for (Property p : s.getPropertiesList()) {
            if (p.getKey().equals("thickness")) {
                canvas.setStroke(new BasicStroke(Integer.valueOf(p.getValue())));
            }
        }
        if (road.isPresent() && road.get()) {
            canvas.setStroke(new BasicStroke(ROAD_WIDTH));
        }
        Line2D line = new Line2D.Double(v1.getX(), v1.getY(), v2.getX(), v2.getY());
        canvas.draw(line);
        canvas.setColor(old);
        canvas.setStroke(new BasicStroke(0));
    }

    private void drawAVertex(Structs.Vertex v, Mesh aMesh, Graphics2D canvas) {
        Color old = canvas.getColor();

        Optional<Boolean> city = new CityProperty().extract(v.getPropertiesList());
        if (city.isPresent() && city.get()) {
            canvas.setColor(Color.RED);
            Ellipse2D circ = new Ellipse2D.Double(v.getX() - CITY_RADIUS / 2, v.getY() - CITY_RADIUS / 2, CITY_RADIUS,
                    CITY_RADIUS);
            canvas.fill(circ);
            canvas.setColor(old);
        }

    }

}
