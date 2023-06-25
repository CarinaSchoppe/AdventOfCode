package year2022;

import java.io.BufferedReader;
import java.io.IOException;

public class Challenge4 {

    public static void main(String[] args) throws IOException {
        var sum = 0;
        var sum3 = 0;
        var sum2 = 0;
        //read in the file "challenge4.txt" from the resources folder
        var file = Challenge4.class.getResourceAsStream("challenge4.txt");
        assert file != null;
        var reader = new BufferedReader(new java.io.InputStreamReader(file));
        while (reader.ready()) {
            var jobPair = reader.readLine().split(",");
            var elf1 = new int[]{Integer.parseInt(jobPair[0].split("-")[0]), Integer.parseInt(jobPair[0].split("-")[1])};
            var elf2 = new int[]{Integer.parseInt(jobPair[1].split("-")[0]), Integer.parseInt(jobPair[1].split("-")[1])};
/*
            System.out.println("elf1[0] = " + elf1[0]);
            System.out.println("elf1[1] = " + elf1[1]);
            System.out.println("elf2[0] = " + elf2[0]);
            System.out.println("elf2[1] = " + elf2[1]);
            var string = "";
            var string2 = "";
            for (int i = elf1[0]; i < elf1[1]+1; i++){
                string+=i;
            }
            for (int i = elf2[0]; i < elf2[1]+1; i++){
                string2+=i;
            }

            if (string.contains(string2) || string2.contains(string)){
                System.out.println(string+" "+string2);
            sum+=1;
            }*/
            var num11 = elf1[0];
            var num12 = elf1[1];
            var num21 = elf2[0];
            var num22 = elf2[1];
            if (num11 <= num21 && num12 >= num22 || num11 >= num21 && num12 <= num22) {
                sum2 += 1;
            }
            System.out.println("num11 = " + num11);
            System.out.println("num12 = " + num12);
            System.out.println("num21 = " + num21);
            System.out.println("num22 = " + num22);
            if (num11 <= num21 && num12 >= num21 || num21 <= num11 && num22 >= num11 || num11 <= num21 && num12 >= num21 || num11 >= num21 && num21 >= num11) {
                System.out.println("nums: " + num11 + " " + num12 + " " + num21 + " " + num22);
                sum3 += 1;
            }
        }
        //System.out.println("sum = " + sum);
        System.out.println("sum = " + sum2);
        System.out.println("sum = " + sum3);
    }
}
