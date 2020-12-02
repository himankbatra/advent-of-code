package com.adventofcode._2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AdventOfCode2020_03 {

    public static void main(String[] args)  {
        String inputFile = "input_03.txt";
        List<String> input;
        try {
            input = Files.readAllLines(Paths.get("src/main/resources",inputFile));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }



    }
}
