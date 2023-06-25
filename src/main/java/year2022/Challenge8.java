package year2022;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

public class Challenge8 {

    public static void main(String[] args) {
        var reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Challenge8.class.getResourceAsStream("challenge8.txt"))));


        var lines = reader.lines().toList();
        System.out.println();
        var ints = new int[lines.get(0).length()][lines.size()];

        for (var i = 0; i < lines.size(); i++) {
            var line = lines.get(i);
            for (var j = 0; j < line.length(); j++) {
                var character = Integer.parseInt(String.valueOf(line.toCharArray()[j]));
                ints[j][i] = character;
            }
        }

        var booleans = new boolean[lines.get(0).length()][lines.size()];
        for (var j = 1; j < booleans.length - 1; j++) {
            for (var i = 1; i < booleans.length - 1; i++) {
                var zahl = ints[i][j];
                var upperOkay = true;
                var leftOkay = true;
                var rightOkay = true;
                var lowerOkay = true;

                System.out.println("aktuelle Zahl:" + zahl + " i,j " + i + "," + j);

                for (var upper = 0; upper <= j - 1; upper++) {
                    if (zahl > ints[i][upper]) {
                        upperOkay = false;
                        break;
                    }
                }
                for (var left = 0; left <= i - 1; left++) {
                    if (zahl > ints[left][j]) {
                        leftOkay = false;
                        break;
                    }
                }
                for (var right = booleans.length - 1; right > i; right--) {
                    if (zahl > ints[right][j]) {
                        rightOkay = false;
                        break;
                    }
                }
                for (var lower = booleans.length - 1; lower > j; lower--) {
                    if (zahl > ints[i][lower]) {
                        lowerOkay = false;
                        break;
                    }
                }

                if (leftOkay && rightOkay && upperOkay && lowerOkay)
                    booleans[i][j] = true;

            }
        }

        for (var i = 0; i < booleans.length; i++) {
            for (var j = 0; j < booleans.length; j++) {
                System.out.print(booleans[j][i] + " ");
            }
            System.out.println();
        }


    }

}
