package year2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;

public class Challenge6 {

    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Challenge6.class.getResourceAsStream("challenge6.txt"))));
        String line = reader.readLine();
        var charArray = line.toCharArray();
        var preAmount = 0;
        var checkAmount = 14;
        for (int i = 0; i < charArray.length; i++) {
            var set = new HashSet<Character>();
            for (int j = 0; j < checkAmount; j++) {
                set.add(charArray[i + j]);
            }
            if (set.size() == checkAmount) {
                preAmount += checkAmount;
                break;
            }
            preAmount++;
        }
        System.out.println(preAmount);

    }
}
