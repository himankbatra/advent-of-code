package com.adventofcode._2020;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


public class AdventOfCode25 {


    public static void main(String[] args) {
        String inputFile = "2020/input25.txt";
        List<Integer> input;
        try {
            input = Files.lines(Paths.get("src/main/resources", inputFile))
                    .map(Integer::parseInt).collect(Collectors.toList());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        long val0 = 1;
        long val1 = 1;
        while (val0 != input.get(0)) {
            val0 = (val0 * 7) % 20201227;
            val1 = (val1 * input.get(1)) % 20201227;
        }

        System.out.println("val1 = " + val1);


    }


}
