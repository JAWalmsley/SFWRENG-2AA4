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
        List<String> data = new ArrayList<String>(Arrays.asList("abc", "abd", "ahe", "bass"));
        this.mp = new MarkovProcess(data);
    }

    @Test
    public void testProbabilities() {
        assertEquals((double) 2/4, this.mp.getProbability('a', 'b'));
        assertEquals((double) 1/3, this.mp.getProbability('b', 'c'));
        assertEquals((double) 1, this.mp.getProbability('h', 'e'));
        assertEquals((double) 1/4, this.mp.getProbability('a', 'h'));
        assertEquals((double) 1/2, this.mp.getProbability('s', 's'));
    }

    @Test
    public void testNext() {
        assertEquals('b', this.mp.getExpectedNext('a'));
        assertEquals('e', this.mp.getExpectedNext('h'));
        assertEquals('a', this.mp.getExpectedNext('\n'));
    }

    @Test
    public void testRandom() {
        Random r = new Random();
        char n = this.mp.getRandomNext('a', r);
        assertTrue(n == 'b' || n == 'd' || n == 'h' || n == 's');
    }
}
