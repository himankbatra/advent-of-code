package com.adventofcode._2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;

public class AdventOfCode07 {

    public static void main(String[] args) {

        int ans1= IntStream.rangeClosed(1, getInput().max().getAsInt())
                .map(AdventOfCode07::calc).min().getAsInt();

        System.out.println("ans1 = " + ans1);

        int ans2= IntStream.rangeClosed(1, getInput().max().getAsInt())
                .map(AdventOfCode07::calc1).min().getAsInt();

        System.out.println("ans2 = " + ans2);


    }

    private static IntStream getInput() {
        String inputFile = "2021/input07.txt";
        IntStream integers;
        try {
            integers = Arrays.stream(Files.lines(Paths.get("src/main/resources", inputFile))
                            .findFirst()
                            .orElse("")
                            .split(","))
                    .mapToInt(Integer::parseInt);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return integers;
    }


    private static IntStream getSteps(int guess) {
        return getInput().map(n -> guess > n ? guess - n : n - guess);
    }

    private static int calc(int guess) {
        return getSteps(guess).sum();
    }


    private static int calc1(int guess) {
        return getSteps(guess).map(n->n*(n+1)/2).sum();
    }
}

