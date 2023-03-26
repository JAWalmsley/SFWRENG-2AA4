package ca.mcmaster.cas.se2aa4.a2.island;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ca.mcmcaster.cas.se2aa4.a2.island.moisture.ExponentialMoisture;
import ca.mcmcaster.cas.se2aa4.a2.island.moisture.LinearMoisture;
import ca.mcmcaster.cas.se2aa4.a2.island.moisture.MoistureFactory;
import ca.mcmcaster.cas.se2aa4.a2.island.moisture.MoistureProfile;
import ca.mcmcaster.cas.se2aa4.a2.island.shape.Circle;
import ca.mcmcaster.cas.se2aa4.a2.island.shape.Shape;
import ca.mcmcaster.cas.se2aa4.a2.island.shape.ShapeFactory;
import ca.mcmcaster.cas.se2aa4.a2.island.shape.Square;
import ca.mcmcaster.cas.se2aa4.a2.island.shape.Triangle;

public class FactoryTest {
    @Test
    public void testMoistureFactory() {
        MoistureProfile lmp = MoistureFactory.getMoistureProfile("linear");
        assert(lmp instanceof LinearMoisture);
        MoistureProfile emp = MoistureFactory.getMoistureProfile("exponential");
        assert(emp instanceof ExponentialMoisture);
    }

    @Test
    public void testShapeFactory() {
        Shape cs = ShapeFactory.getShape("circle", 100);
        assertTrue(cs instanceof Circle);
        Shape ss = ShapeFactory.getShape("square", 100);
        assertTrue(ss instanceof Square);
        Shape ts = ShapeFactory.getShape("triangle", 100);
        assertTrue(ts instanceof Triangle);
    }
}
