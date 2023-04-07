package ca.mcmcaster.cas.se2aa4.a2.island.names;

import java.util.List;

public class BMarkovProcess {
    private int order;
    private double smoothing;
    private List<String> trainingData;
    public BMarkovProcess(int order, double smoothing, List<String> trainingData) {
        this.order = order;
        this.smoothing = smoothing;
        this.trainingData = trainingData;
    }
}
