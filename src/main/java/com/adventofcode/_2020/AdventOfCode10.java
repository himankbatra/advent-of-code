package com.adventofcode._2020;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class AdventOfCode10 {


    public static void main(String[] args) {
        String inputFile = "2020/input10.txt";
        List<Integer> input;
        try {
            input = Files.lines(Paths.get("src/main/resources", inputFile))
                    .map(Integer::parseInt).collect(Collectors.toList());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        int max = Collections.max(input) + 3;

        input.add(max);

        int index = 0;
        int countOne = 0;
        int countThree = 0;
        while (index <= max - 3) {
            if (input.contains(index + 1)) {
                index = index + 1;
                countOne++;
            } else if (input.contains(index + 3)) {
                index = index + 3;
                countThree++;
            }
        }

        System.out.println("result 1 => " + (countThree * countOne));

        input.add(0);
        input.sort(Comparator.comparingInt(a -> a));
        List<Long> ways = new ArrayList<>(Collections.nCopies(input.size(), 0L));
        ways.set(0, 1L);
        for (int i = 0; i < input.size(); ++i) {
            for (int j = i + 1; j < input.size(); ++j) {
                if (input.get(j) - input.get(i) > 3) {
                    break;
                }
                ways.set(j, ways.get(j) + ways.get(i));
            }
        }

        System.out.println("result 2 => " + ways.get(ways.size() - 1));

    }


}
