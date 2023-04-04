package ca.mcmaster.cas.se2aa4.a2.generator.roads;

import java.util.Map;

import ca.mcmaster.cas.se2aa4.a2.generator.configuration.Configuration;

public class RoadFactory {
    public static RoadGenerator create(Configuration config) {
        Map<String, String> options = config.export();
        return new DefaultRoads();
    }
}
