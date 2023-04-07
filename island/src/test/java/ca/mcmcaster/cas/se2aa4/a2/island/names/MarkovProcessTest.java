package ca.mcmcaster.cas.se2aa4.a2.island.names;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MarkovProcessTest {
    private MarkovProcess mp;
    @BeforeEach
    public void setUp() {
        List<String> data = new ArrayList<String>(Arrays.asList("abc", "abd", "ahe", "bass", "no"));
        this.mp = new MarkovProcess(1, "abcdhesno", 0, data);
    }

    @Test
    public void testNext() {
        assertEquals("o", this.mp.generate("n", new Random()));
        assertTrue(this.mp.generate("a", new Random()).matches("[bs]"));
        assertTrue(this.mp.generate("b", new Random()).matches("[cd]"));
    }
}
