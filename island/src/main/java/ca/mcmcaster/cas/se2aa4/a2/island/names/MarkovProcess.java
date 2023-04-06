package ca.mcmcaster.cas.se2aa4.a2.island.names;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MarkovProcess {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz\n";
    private static final Map<Character, Double> emptyProbabilityMap;
    private static final Map<Character, Integer> emptyOccurrencesMap;
    static {
        emptyProbabilityMap = new HashMap<>();
        emptyOccurrencesMap = new HashMap<>();
        for(char c : ALPHABET.toCharArray()) {
            emptyProbabilityMap.put(c, 0.0);
            emptyOccurrencesMap.put(c, 0);
        }
    }

    private List<String> trainingData;
    private Map<Character, Map<Character, Double>> probabilities;
    private Map<Character, Map<Character, Integer>> occurrences = new HashMap<>();
    

    public MarkovProcess(List<String> trainingData) {
        this.trainingData = trainingData;
        this.probabilities = new HashMap<>();
        for (char c : ALPHABET.toCharArray()) {
            probabilities.put(c, new HashMap<Character, Double>(emptyProbabilityMap));
            occurrences.put(c, new HashMap<Character, Integer>(emptyOccurrencesMap));
        }

        this.createProbabilities();
    }

    private void createProbabilities() {
        for(String str : this.trainingData) {
            char[] strArr = str.toCharArray();
            // Add the first char to the occurrences after a newline
            int currStart = this.occurrences.get('\n').get(strArr[0]);
            this.occurrences.get('\n').put(strArr[0], currStart + 1);

            // Add all other characters in string's occurrences
            for(int i = 0; i < strArr.length - 1; i++) {
                int currCount = this.occurrences.get(strArr[i]).get(strArr[i+1]);
                this.occurrences.get(strArr[i]).put(strArr[i+1], currCount + 1);
            }
        }

        for(Map.Entry<Character, Map<Character, Integer>> entry : this.occurrences.entrySet()) {
            int total = 0;
            for(Map.Entry<Character, Integer> innerEntry : entry.getValue().entrySet()) {
                total += innerEntry.getValue();
            }
            for(Map.Entry<Character, Integer> innerEntry : entry.getValue().entrySet()) {
                int occurrs = innerEntry.getValue();
                this.probabilities.get(entry.getKey()).put(innerEntry.getKey(), (double) occurrs / total);
            }
        }
    }

    public Double getProbability(char c1, char c2) {
        return this.probabilities.get(c1).get(c2);
    }

    public char getExpectedNext(char c1) {
        double max = 0;
        char maxChar = 'a';
        for(Map.Entry<Character, Double> entry : this.probabilities.get(c1).entrySet()) {
            if(entry.getValue() > max) {
                max = entry.getValue();
                maxChar = entry.getKey();
            }
        }
        return maxChar;
    }

    public char getRandomNext(char c1, Random r) {
        double rand = r.nextDouble(0, 1);
        double sum = 0;
        for(Map.Entry<Character, Double> entry : this.probabilities.get(c1).entrySet()) {
            sum += entry.getValue();
            if(sum >= rand) {
                return entry.getKey();
            }
        }
        return 'a';
    }
}
