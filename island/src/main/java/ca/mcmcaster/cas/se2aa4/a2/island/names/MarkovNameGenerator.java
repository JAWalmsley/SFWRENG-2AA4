package ca.mcmcaster.cas.se2aa4.a2.island.names;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MarkovNameGenerator implements NameGenerator {
    private MarkovProcess mp;
    private Random rand;

    public MarkovNameGenerator(Random rand) throws MalformedURLException, IOException {
        this.rand = rand;
        URL nameurl = new URL("https://raw.githubusercontent.com/Tw1ddle/markov-namegen-lib/507e0b671fe1496c6a9046763c9cf2d4c9330589/word_lists/american_cities.txt");
        List<String> names = new ArrayList<String>();
        Scanner sc = new Scanner(nameurl.openStream());
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            names.add(line);
        }
        sc.close();

        this.mp = new MarkovProcess(names);
    }

    @Override
    public String generateName(int length) {
        StringBuilder sb = new StringBuilder();
        // Add first char to string, it comes after the previous line's \n
        sb.append(mp.getRandomNext('\n', this.rand));
        while (sb.charAt(sb.length() - 1) != '\n' && sb.length() < length) {
            sb.append(mp.getRandomNext(sb.charAt(sb.length() - 1), this.rand));
        }
        return sb.toString();
    }

}
