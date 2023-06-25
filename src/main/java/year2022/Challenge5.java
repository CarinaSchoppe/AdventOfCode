package year2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Challenge5 {

    public static boolean part1 = true;

    public static void main(String[] args) throws IOException {
        //read in the file
        var reader = new BufferedReader(new InputStreamReader(Challenge5.class.getResourceAsStream("challenge5.txt")));
        String pile1 = "";
        var length = 0;

        String lines = "";
        String line;
        while (!Objects.equals(line = reader.readLine(), "")) {
            lines += line + "\n";
            pile1 += line.substring(0, 3);
            if (length < line.length()) {
                length = line.length();
            }
        }

        var amount = (length + 1) / 4;
        var pileArray = new String[amount];

        for (int i = 0; i < amount; i++) {
            pileArray[i] = "";
        }
        reader = new BufferedReader(new InputStreamReader(Challenge5.class.getResourceAsStream("challenge5.txt")));

        while (!Objects.equals(line = reader.readLine(), "")) {
            for (var i = 0; i < line.toCharArray().length; i += 4) {
                var character = line.toCharArray()[i + 1];
                if (character != ' ') {
                    pileArray[i / 4] += character;
                }
            }
        }
        for (var i = 0; i < pileArray.length; i++) {
            pileArray[i] = pileArray[i].substring(0, pileArray[i].length() - 1);
        }

        var jobs = new ArrayList<HashMap<String, Integer>>();
        reader = new BufferedReader(new InputStreamReader(Challenge5.class.getResourceAsStream("challenge5.txt")));

        while ((line = reader.readLine()) != null) {
            if (!line.startsWith("m")) continue;
            var points = line.split(" ");
            var job = new HashMap<String, Integer>();
            job.put(points[0], Integer.parseInt(points[1]));
            job.put(points[2], Integer.parseInt(points[3]) - 1);
            job.put(points[4], Integer.parseInt(points[5]) - 1);
            jobs.add(job);

        }

        System.out.println(jobs.get(1).get("move"));

        for (var job : jobs) {
            var amountMove = job.get("move");
            int from = job.get("from");
            var to = job.get("to");

            System.out.println("Moving from: " + (from + 1) + " to " + (to + 1) + " amount: " + amountMove);

            //For part 1
            if (part1) {
                for (var i = 0; i < amountMove; i++) {
                    var letter = pileArray[from].substring(0, 1);
                    System.out.println("letter: " + letter);
                    pileArray[from] = pileArray[from].substring(1);
                    System.out.println("pileArray: " + pileArray[from]);
                    pileArray[to] = letter + pileArray[to];
                    System.out.println("pileArrayNew: " + pileArray[to]);
                }
            } else {
                //for part 2
                var letter = pileArray[from].substring(0, amountMove);
                System.out.println("letter: " + letter);
                pileArray[from] = pileArray[from].substring(amountMove);
                System.out.println("pileArray: " + pileArray[from]);
                pileArray[to] = letter + pileArray[to];
                System.out.println("pileArrayNew: " + pileArray[to]);
            }
        }

        for (var string : pileArray) {
            System.out.println(string);
        }

        System.out.println("Loesung");
        for (var string : pileArray) {
            System.out.print(string.toCharArray()[0]);
        }
    }
}
