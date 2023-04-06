package ca.mcmcaster.cas.se2aa4.a2.island.adt;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmcaster.cas.se2aa4.a2.island.roads.CityType;

public class City extends Point {
    CityType city;
    String name;

    public City(Point p) {
        super(p);
        this.city = CityType.NONE;
        this.name = "";
    }

    public CityType getCity() {
        return city;
    }

    public void setCity(CityType city) {
        this.city = city;
    }

    public Structs.Vertex getVertex() {
        Structs.Vertex.Builder builder = Structs.Vertex.newBuilder(this.vertex);
        while (builder.getPropertiesCount() > 0)
            builder.removeProperties(0);
        return builder.addProperties(this.colour.toProperty()).addProperties(this.cityProperty()).build();
    }

    public Property cityProperty() {
        return Property.newBuilder().setKey("city").setValue(this.city.name()).build();
    }
}
