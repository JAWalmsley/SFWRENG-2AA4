package ca.mcmaster.cas.se2aa4.a2.generator.roads;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.PairOfVertex;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Vertex;
import ca.mcmaster.cas.se2aa4.a2.generator.configuration.Configuration;
import ca.mcmaster.cas.se2aa4.a2.generator.specification.Buildable;
import ca.mcmaster.cas.se2aa4.a2.generator.specification.SpecificationFactory;
import ca.mcmcaster.cas.se2aa4.a2.island.roads.RoadFactory;
import ca.mcmcaster.cas.se2aa4.a2.island.roads.RoadGenerator;

public class DefaultRoadsTest {
    RoadGenerator rg;
    Mesh theMesh;
    int numCities = 5;
    @BeforeEach
    void setUp() {
        String[] args = {"-h", "10" ,"-w", "10", "-k", "irregular", "-p", "10", "-r", "10", "-c", String.valueOf(numCities)};
        Configuration config = new Configuration(args);
        Buildable specification = SpecificationFactory.create(config);
        theMesh = specification.build();
        rg = RoadFactory.create(config);
    }

    @Test
    void testNumCities() {
        Mesh withRoads = rg.addRoads(theMesh);
        HashSet<Vertex> foundCities = new HashSet<>();
        for(Polygon p : withRoads) {
            for(PairOfVertex vp : p.hull()) {
                for(Vertex v : vp.contents()) {
                    if(v.isCity()) {
                        foundCities.add(v);
                    }
                }
            }
        }
        assertEquals(numCities, foundCities.size());
    }

    @Test
    public void testRoadsSanity() {
        Mesh withRoads = rg.addRoads(theMesh);
        HashSet<PairOfVertex> foundRoads = new HashSet<>();
        for(Polygon p : withRoads) {
            for(PairOfVertex vp : p.hull()) {
                if(vp.isRoad()) {
                    foundRoads.add(vp);
                }
            }
        }
        assertTrue(foundRoads.size() > 0);
    }
}
