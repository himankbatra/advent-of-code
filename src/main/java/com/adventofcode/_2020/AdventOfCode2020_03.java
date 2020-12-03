package com.adventofcode._2020;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AdventOfCode2020_03 {

    public static void main(String[] args) {
        String inputFile = "2020/input_03.txt";
        List<String> input;
        try {
            input = Files.readAllLines(Paths.get("src/main/resources", inputFile));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        int noOfTrees = 0;
        int index = 0;
        int loop = 0;
        for (String i : input) {
            char[] chars = i.toCharArray();
           while(chars.length <= index) {
                chars = new String(chars).concat(i).toCharArray();
            }
            loop++;
           // for part 2
           /* if(loop%2==0)
            {
                continue;
            }*/
            if (chars[index] == '#') {
                System.out.println("loop -> " + loop + " index -> " + index + " string -> " + new String(chars));
                noOfTrees++;
            }
            index = index + 7;
        }
        System.out.println("noOfTrees = " + noOfTrees);

    }
}
