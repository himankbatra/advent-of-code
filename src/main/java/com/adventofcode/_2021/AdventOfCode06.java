package com.adventofcode._2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AdventOfCode06 {

    public static void main(String[] args) {
        String inputFile = "2021/input06.txt";
        List<Integer> integers;
        try {
            integers = Arrays.stream(Files.lines(Paths.get("src/main/resources", inputFile))
                            .findFirst()
                            .orElse("")
                            .split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        Map<Integer, Long> fish = new HashMap<>();

        for(Integer life : integers)
        {
            fish.put(life, fish.getOrDefault(life, 0L) + 1);
        }

        Map<Integer, Long> copy = new HashMap<>(fish);
        for(int day = 0; day < 80; day++)
            reduce(copy);

        long sum = 0;
        for(long fishCount : copy.values())
            sum += fishCount;
        System.out.println("ans 1 = " + sum);

        copy = new HashMap<>(fish);
        for(int day = 0; day < 256; day++)
            reduce(copy);

        sum = 0;
        for(long fishCount : copy.values())
            sum += fishCount;
        System.out.println("ans 2 = " + sum);

    }


    public static void reduce(Map<Integer, Long> fish)
    {
        for(int fishLife = 0; fishLife <= 8; fishLife++)
            fish.put(fishLife - 1, fish.getOrDefault(fishLife, 0L));
        fish.put(6, fish.getOrDefault(6, 0L) + fish.get(-1));
        fish.put(8, fish.remove(-1));
    }
}
