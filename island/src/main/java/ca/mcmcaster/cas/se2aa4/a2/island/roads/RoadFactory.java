package ca.mcmcaster.cas.se2aa4.a2.island.roads;

public class RoadFactory {
    public static RoadGenerator create(int numCities) {
        return new DefaultRoads(numCities);
    }
}
