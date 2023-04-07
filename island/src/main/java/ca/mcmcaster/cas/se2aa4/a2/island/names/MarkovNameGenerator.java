package ca.mcmcaster.cas.se2aa4.a2.island.names;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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

        this.mp = new MarkovProcess(3, "abcdefghijklmnopqrstuvwxyz", 0, names);
    }

    @Override
    public String generateName(int length) {
        StringBuilder sb = new StringBuilder();
        // Add first char to string, it comes after the start char "^"
        sb.append(mp.generateFirst(this.rand));

        // Generate new chars until we get the suffix char or go over length
        while (sb.length() < length) {
            String newChar = mp.generate(sb.toString(), this.rand);
            if(newChar.equals("^"))
                break;
            sb.append(newChar);
        }
        return sb.toString();
    }

}
