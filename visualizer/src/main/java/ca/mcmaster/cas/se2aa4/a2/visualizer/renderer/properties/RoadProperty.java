package ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties;

import java.util.List;
import java.util.Optional;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

public class RoadProperty implements PropertyAccess<Boolean> {

    @Override
    public Optional<Boolean> extract(List<Property> props) {
        String value = new Reader(props).get("road");
        if (value == null)
            return Optional.empty();
        return Optional.of(value.equals("true"));
    }
    
}
