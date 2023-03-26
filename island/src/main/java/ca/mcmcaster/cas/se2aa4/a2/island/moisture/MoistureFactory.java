package ca.mcmcaster.cas.se2aa4.a2.island.moisture;

public class MoistureFactory {
    public static MoistureProfile getMoistureProfile(String profile) {
        switch(profile) {
            case "linear":
                return new LinearMoisture();
            case "exponential":
                return new ExponentialMoisture();
            default:
                return new LinearMoisture();
        }
    }
}
