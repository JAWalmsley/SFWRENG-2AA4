package ca.mcmcaster.cas.se2aa4.a2.island.adt;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

public class Colour {
    int red;
    int green;
    int blue;
    int alpha;

    public Colour(int red, int green, int blue, int alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public Colour(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = 255;
    }

    public Structs.Property toProperty() {
        String colorCode = this.red + "," + this.green + "," + this.blue + ',' + this.alpha;
        Structs.Property colourProp = Structs.Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        return colourProp;
    }
}
