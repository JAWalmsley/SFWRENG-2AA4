package ca.mcmaster.cas.se2aa4.a2.island;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmcaster.cas.se2aa4.a2.island.adt.Colour;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ColourTest {
    @Test
    public void testColour() {
        Colour c = new Colour(255, 0, 255);
        Structs.Property p = c.toProperty();
        assertEquals(p.getKey(), "rgb_color");
        assertEquals(p.getValue(), "255,0,255,255");
    }
}
