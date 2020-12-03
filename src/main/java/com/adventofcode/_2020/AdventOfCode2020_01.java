package com.adventofcode._2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class AdventOfCode2020_01 {

    public static void main(String[] args) {
        String inputFile = "2020/input_01.txt";
        List<Integer> numbers;
        try {
            numbers = Files.lines(Paths.get("src/main/resources", inputFile))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        numbers.stream()
                .map(i -> 2020 - i)
                .filter(numbers::contains)
                .peek(System.out::println)
                .reduce((integer, integer2) -> integer * integer2)
                .ifPresent(a -> System.out.println("answer 1st part :: " + a));

        numbers.stream()
                .map(i -> 2020 - i)
                .filter(i ->
                        numbers.stream().map(i2 -> i - i2)
                                .anyMatch(o -> /*{
                                    boolean contains = */
                                        numbers.contains(o)/*;
                                    if (contains) {
                                        int i1 = 2020 - i;
                                        int i2 = i - o;
                                        System.out.println("i1 => " + i1 + " i2 => " + i2 + " i3 => " + o);
                                    }
                                    return contains;
                                }*/)
                )
                .map(i -> 2020 - i)
                .peek(System.out::println)
                .reduce((integer, integer2) -> integer * integer2)
                .ifPresent(a -> System.out.println("answer second part :: " + a));
    }

}
