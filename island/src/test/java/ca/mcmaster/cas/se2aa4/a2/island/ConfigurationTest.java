package ca.mcmaster.cas.se2aa4.a2.island;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ca.mcmcaster.cas.se2aa4.a2.island.islandBuilder.Configuration;

public class ConfigurationTest {
    @Test
    public void testConfigGoodParse() throws Exception {
        Configuration config = new Configuration(new String[] {"-e", "volcano", "-i", "inputfile", "-o", "outputfile"});
        assertTrue(config.parse());
        assertEquals("volcano", config.getOption("e", ""));
    }

    @Test
    public void testConfigBadParse() throws Exception {
        Configuration config = new Configuration(new String[] {"-e", "volcano"});
        assertFalse(config.parse());
    }

    @Test
    public void testConfigStopOnHelp() throws Exception {
        Configuration config = new Configuration(new String[] {"--help"});
        assertFalse(config.parse());
    }
}
