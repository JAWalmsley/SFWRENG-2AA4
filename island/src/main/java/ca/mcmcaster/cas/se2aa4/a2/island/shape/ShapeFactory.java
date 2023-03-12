package ca.mcmcaster.cas.se2aa4.a2.island.shape;

public class ShapeFactory {
    public static Shape getShape(String shape, int border) {
        switch (shape) {
        case "square":
            return new Square(border);
        case "circle":
            System.out.println("Circle");
            return new Circle(border);
        case "triangle":
            return new Triangle(border);
        default:
            return new Lagoon(border, border-3);
        }
    }
}