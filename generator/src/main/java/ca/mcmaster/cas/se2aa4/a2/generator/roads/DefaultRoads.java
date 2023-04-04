package ca.mcmaster.cas.se2aa4.a2.generator.roads;

import java.util.ArrayList;
import java.util.Random;

import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Vertex;

public class DefaultRoads implements RoadGenerator {
    ArrayList<Vertex> cities = new ArrayList<Vertex>();
    private int numCities;

    public DefaultRoads(int numCities) {
        this.numCities = numCities;
    }

    private void placeCities(Mesh mesh) {
        Random rand = new Random();
        outer: while(true) {
            for(Polygon p : mesh) {
                if(cities.size() >= numCities) {
                    break outer;
                }
                if(rand.nextInt(0, 100) < 10) {
                    this.cities.add(p.hull().get(0).contents()[0]);
                }
            }
        }

        for(Vertex v : cities) {
            v.isCity = true;
        }
    }

    @Override
    public Mesh addRoads(Mesh mesh) {
        return mesh;
    }
    
}
