package ca.mcmcaster.cas.se2aa4.a2.island.roads;

public class RoadFactory {
    public static RoadGenerator create(int numCities) {
        // Currently only one road type, but could be extended in future to have other types such as realistic roads
        return new DefaultRoads(numCities);
    }
}
