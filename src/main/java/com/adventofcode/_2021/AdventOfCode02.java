package com.adventofcode._2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class AdventOfCode02 {

    public static void main(String[] args) {
        String inputFile = "2021/input02.txt";
        List<String> strings;
        try {
            strings = Files.lines(Paths.get("src/main/resources", inputFile))
                    .collect(Collectors.toList());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        int h = 0;
        int d = 0;

        for (String s : strings) {
            if (s.startsWith("f")) {
                h = h + Integer.parseInt(s.split(" ")[1]);
            } else if (s.startsWith("d")) {
                d = d + Integer.parseInt(s.split(" ")[1]);
            } else if (s.startsWith("u")) {
                d = d - Integer.parseInt(s.split(" ")[1]);
            }
        }

        System.out.println("ans 1 = " + h * d);

        h = 0;
        d = 0;
        int a = 0;

        for (String s : strings) {
            if (s.startsWith("f")) {
                h = h + Integer.parseInt(s.split(" ")[1]);
                d = d + (a * Integer.parseInt(s.split(" ")[1]));
            } else if (s.startsWith("d")) {
                a = a + Integer.parseInt(s.split(" ")[1]);
            } else if (s.startsWith("u")) {

                a = a - Integer.parseInt(s.split(" ")[1]);
            }
        }

        System.out.println("ans 2 = " + h * d);

    }

}
