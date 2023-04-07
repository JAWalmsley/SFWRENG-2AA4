package ca.mcmcaster.cas.se2aa4.a2.island.names;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BMarkovProcess {
    private int order;
    private double smoothing;
    private List<String> trainingData;
    private Map<String, Counter> counters;
    private String alphabet;
    private String start, end;

    public BMarkovProcess(int order, String alphabet, double smoothing, List<String> trainingData) {
        this.order = order;
        this.smoothing = smoothing;
        this.trainingData = trainingData;
        this.alphabet = alphabet;
        this.counters = new HashMap<>();
        this.start = "^".repeat(this.order);
        this.end = "^";
    }

    private Counter getCounter(String data) {
        if (!(counters.keySet().contains(data))) {
            counters.put(data, new Counter(this.alphabet, this.smoothing));
        }
        return counters.get(data);
    }

    /**
     * Katz backoff algorithm, used to tell which order chain to use in this context
     * 
     * @param data the string we are trying to predict the next character of
     */
    private String katzBackoff(String data) {
        String result = data;

        // Too long for this chain
        if (data.length() > this.order) {
            result = data.substring(data.length() - this.order);
        } else if (data.length() < this.order) {
            result = this.start.repeat(this.order - data.length()) + data;
        }

        // If we don't have any data for the last n characters, see if we have any for
        // the last n-1 characters
        while (!(counters.keySet().contains(result))) {
            result = result.substring(1);
        }
        return result;
    }

    public void addData(String data) {
        String padded = this.start + data + this.end;
        for (int i = this.order; i < padded.length(); i++) {
            // Add all order-sized chunks of the string to the counters
            String nextData = data.substring(i - this.order, i);
            String currData = String.valueOf(data.charAt(i));
            // Add the next j characters to the counter for the current character, up to
            // current order
            for (int j = 0; j < nextData.length(); j++) {
                this.getCounter(nextData.substring(j)).addData(currData);
            }
        }
    }

    public String generate(String data, Random r) {
        Counter c = this.getCounter(this.katzBackoff(data));
        return c.generate(r);
    }
}
