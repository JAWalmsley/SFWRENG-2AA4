package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.ArrayList;
import java.util.Random;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;

public class GenerateIrregularMesh implements MeshType {
    private int relaxLevel;

    public GenerateIrregularMesh(int relaxLevel) {
        this.relaxLevel = relaxLevel;
    }

    public void makeRandomVertices(Mesh mesh, int numCentroids) {
        Random bag = new Random();
        for (int i = 0; i < numCentroids; i++) {
            float x = bag.nextFloat() * mesh.width;
            float y = bag.nextFloat() * mesh.height;
            Vertex v = new Vertex(x, y);
            mesh.addVertex(v);
        }
    }

    public void calculateVoronoi(Mesh mesh) {
        mesh.polygons.clear();
        mesh.createCords();

        GeometryFactory fact = new GeometryFactory();

        for (int i = 0; i < mesh.getCoordinates().size(); i++) {
            fact.createPoint(mesh.getCoordinates().get(i));
        }

        VoronoiDiagramBuilder voronoiBuilder = new VoronoiDiagramBuilder();
        voronoiBuilder.setSites(mesh.getCoordinates());
        Geometry voronoiDiagram = voronoiBuilder.getDiagram(fact);

        for (int i = 0; i < voronoiDiagram.getNumGeometries(); i++) {
            Polygon poly = new Polygon(mesh.getVertices().get(i));
            poly.convertGeometry(voronoiDiagram.getGeometryN(i));

            Random bag = new Random();
            int[] colour = { bag.nextInt(255), bag.nextInt(255), bag.nextInt(255), 130 };
            poly.setColour(colour);
            mesh.polygons.add(poly);
        }
    }

    public void lloydRelaxation(Mesh mesh, int relaxLevel) {
        for (int i = 0; i <= relaxLevel; i++) {
            calculateVoronoi(mesh);
            for (int j = 0; j < mesh.polygons.size(); j++) {
                mesh.polygons.get(j).setCentroid(mesh.polygons.get(j).centerOfMass());
            }
        }
    }

    public void cropMesh(Mesh mesh) {
        ArrayList<Polygon> toRemove = new ArrayList<Polygon>();
        for (Polygon p : mesh.polygons) {
            for (Segment s : p.getSegments()) {
                Vertex v1 = s.getV1();
                Vertex v2 = s.getV2();
                boolean v1out = v1.getX() > mesh.width ||
                        v1.getY() > mesh.height || v1.getX() < 0 || v1.getY() < 0;
                boolean v2out = v2.getX() > mesh.width ||
                        v2.getY() > mesh.height || v2.getX() < 0 || v2.getY() < 0;
                if (v1out || v2out) {
                    toRemove.add(p);
                    break;
                }
            }
        }
        mesh.polygons.removeAll(toRemove);
    }

    public Mesh generateMesh(int numPolygons) {
        Mesh mesh = new Mesh(100, 100, 1);

        makeRandomVertices(mesh, numPolygons);
        lloydRelaxation(mesh, relaxLevel);
        cropMesh(mesh);
        return mesh;
    }

}