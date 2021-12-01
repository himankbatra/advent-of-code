package com.adventofcode._2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class AdventOfCode01 {

    public static void main(String[] args) {
        String inputFile = "2021/input01.txt";
        List<Integer> numbers;
        try {
            numbers = Files.lines(Paths.get("src/main/resources", inputFile))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }


        int count = 0;

        for (int i = 1; i < numbers.size(); i++) {
            Integer number = numbers.get(i);
            Integer prevNumber = numbers.get(i - 1);
            if (number > prevNumber) {
                count++;
            }
        }

        System.out.println("count = " + count);

        int c = 0;
        Integer fNumber = numbers.get(0);
        int first = fNumber + numbers.get(1) + numbers.get(2);
        for (int i = 1; i < numbers.size() - 2; i++) {
            Integer number = numbers.get(i);
            int sum = number + numbers.get(i + 1) + numbers.get(i + 2);
            if (sum > first) {
                c++;
            }
            first = sum;
        }

        System.out.println("c = " + c);

    }

}
