package ca.mcmcaster.cas.se2aa4.a2.island.names;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Counter {
    private String alphabet;
    private double min;
    private Map<String, Double> occurrences = new HashMap<>();
    private double total;
    public Counter(String alphabet, double min) {
        this.alphabet = alphabet;
        this.min = min;
        this.total = 0;

        for(char c : this.alphabet.toCharArray()) {
            occurrences.put(String.valueOf(c), min);
            this.total += min;
        }
    }

    public void addData(String data) {
        double curr = this.occurrences.get(data);
        this.occurrences.put(data, curr + 1);
        this.total += 1;
    }

    public String generate(Random r) {
        double rand = r.nextDouble() * this.total;
        for(Map.Entry<String, Double> entry : this.occurrences.entrySet()) {
            if(rand <= entry.getValue()) {
                return entry.getKey();
            }
            rand -= entry.getValue();
        }
        return "";
    }

    public double getProbability(String data) {
        return this.occurrences.get(data) / this.total;
    }
}
