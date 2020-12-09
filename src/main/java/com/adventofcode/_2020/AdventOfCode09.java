package com.adventofcode._2020;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class AdventOfCode09 {


    public static void main(String[] args) {
        String inputFile = "2020/input09.txt";
        List<Long> input;
        try {
            input = Files.lines(Paths.get("src/main/resources", inputFile))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        int preamble = 25;
        int i;
        for (i = 0; i < input.size() - preamble; i++) {
            Set<Long> sums = new HashSet<>();
            for (int j = i; j < i + preamble; j++) {
                for (int k = j + 1; k < i + preamble; k++) {
                    sums.add(input.get(j) + input.get(k));
                }
            }
            if (!sums.contains(input.get(i + preamble))) {
                break;
            }
        }

        System.out.println("result 1 => " + input.get(i + preamble));

        List<Long> window = new ArrayList<>();
        outer:
        for (int p = 2; i < input.size(); p++) {
            for (int q = 0; q <= input.size() - p; q++) {
                if (input.subList(q, q + p).stream().mapToLong(l -> l).sum() == input.get(i + preamble)) {
                    window = input.subList(q, q + p + 1);
                    break outer;
                }
            }
        }

        long result2 = Collections.max(window) + Collections.min(window);
        System.out.println("result 2 => " + result2);
    }


}
