package com.adventofcode._2020;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class AdventOfCode19 {


    public static void main(String[] args) {
        String inputFile = "2020/input19.txt";
        List<String> input;
        try {
            input = Files.readAllLines(Paths.get("src/main/resources", inputFile));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }


    }

    private static long calc(List<String> strs) {
        return 0L;
    }

    private static long calc2(List<String> strs) {
        return 0L;
    }

}
