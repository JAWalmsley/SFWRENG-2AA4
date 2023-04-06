package ca.mcmaster.cas.se2aa4.a2.visualizer.renderer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties.CityProperty;
import ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties.ColorProperty;
import ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties.NameProperty;
import ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties.RoadProperty;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.Optional;

public class GraphicRenderer implements Renderer {

    private static final int CITY_RADIUS = 40;
    private static final int CITY_INCREMENT = 10;
    private static final int ROAD_WIDTH = 10;

    private static final Color CITY_COLOUR;
    private static final Color CAPITAL_COLOUR;
    static {
        CITY_COLOUR = new Color(120, 0, 1);
        CAPITAL_COLOUR = new Color(201, 169, 22);
    }

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

        Optional<String> city = new CityProperty().extract(v.getPropertiesList());
        if (city.isPresent() && !city.get().equals("NONE")) {
            int radius = CITY_RADIUS;
            canvas.setColor(CITY_COLOUR);
            String c = city.get();
            if (c.equals("TOWN"))
                radius = CITY_RADIUS + CITY_INCREMENT;
            else if (c.equals("CITY"))
                radius = CITY_RADIUS + 2 * CITY_INCREMENT;
            else if (c.equals("CAPITAL")) {
                radius = CITY_RADIUS + 4 * CITY_INCREMENT;
                canvas.setColor(CAPITAL_COLOUR);
            }
            

            Ellipse2D circ = new Ellipse2D.Double(v.getX() - radius / 2, v.getY() - radius / 2, radius, radius);
            canvas.fill(circ);

            Optional<String> name = new NameProperty().extract(v.getPropertiesList());
            if(name.isPresent())
                drawBanner(name.get(), v.getX(), v.getY() + radius / 4, canvas);

        }
        canvas.setColor(old);
    }

    private void drawBanner(String text, double x, double y, Graphics2D canvas) {
        int FONT_SIZE = 20;
        int border = 10;
        Font f = new Font("Monospaced", Font.PLAIN, FONT_SIZE);
        canvas.setFont(f);
        int width = canvas.getFontMetrics().stringWidth(text);
        Rectangle2D rect = new Rectangle2D.Double(x - width/2, y + (FONT_SIZE - border) / 2, width, FONT_SIZE + border);
        Color old = canvas.getColor();
        canvas.setColor(CITY_COLOUR);
        canvas.fill(rect);
        canvas.setColor(CAPITAL_COLOUR);
        canvas.setStroke(new BasicStroke(2));
        canvas.draw(rect);
        canvas.setColor(new Color(0, 0, 0));
        canvas.drawString(text, (int) x - width/2 + border, (int) y + FONT_SIZE + border/2);

        canvas.setColor(old);
        
    }
}
