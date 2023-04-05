package ca.mcmaster.cas.se2aa4.a2.generator.adt;

import ca.mcmaster.cas.se2aa4.a2.generator.neighborhoud.Neighborhood;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Mesh implements Iterable<Polygon> {

    private Set<Polygon> polygons;
    private int width, height;

    public Mesh(int width, int height) {
        this.width = width;
        this.height = height;
        this.polygons = new HashSet<>();
    }

    public void register(Polygon p) {
        this.polygons.add(p.crop(width, height));
    }

    @Override
    public Iterator<Polygon> iterator() {
        return this.polygons.iterator();
    }

    public int numPolygons() {
        return this.polygons.size();
    }

    public PairOfVertex getPairFromVertices(Vertex v1, Vertex v2) {
        for(Polygon p: this) {
            for(PairOfVertex pv : p.hull()) {
                Vertex[] vertices = pv.contents();
                if((vertices[0] == v1 && vertices[0] == v2) || (vertices[1] == v1 && vertices[0] == v2)) {
                    return pv;
                }
            }
        }
        return null;
    }

    public void populateNeighbours(Neighborhood neighbourhood) {
        neighbourhood.build(this.polygons);
        for(Polygon p: this) {
            for(Polygon n: neighbourhood.neighbors(p)) {
                p.registerAsNeighbour(n);
            }
        }
    }

    @Override
    public String toString() {
        return "Mesh(" +width+"x"+height+","+polygons+")";
    }
}
