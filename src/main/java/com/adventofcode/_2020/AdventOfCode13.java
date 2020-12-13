package com.adventofcode._2020;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class AdventOfCode13 {


    public static void main(String[] args) {
        String inputFile = "2020/input13.txt";
        List<String> input;
        try {
            input = Files.readAllLines(Paths.get("src/main/resources", inputFile));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        int timestamp = Integer.parseInt(input.get(0));
        List<Integer> busIds = Arrays.stream(input.get(1).split(","))
                .filter(b -> !b.equals("x"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int min = Integer.MAX_VALUE;
        int minBusId = 0;
        for (int busId : busIds) {
            int time = timestamp;
            while (time % busId != 0) {
                time++;
            }
            if (time < min) {
                min = time;
                minBusId = busId;
            }

        }
        System.out.println("result 1 => " + ((min - timestamp) * minBusId));


        long time = 0;
        long factor = 1;
        String[] split = input.get(1).split(",");
        for (int i = 0; i < split.length; ) {
            String b = split[i];
            if (b.equals("x")) {
                i++;
            } else {
                int busId = Integer.parseInt(b);
                if ((time + i) % busId == 0) {
                    factor *= busId;
                    i++;
                } else {
                    time += factor;
                }
            }
        }

        System.out.println("result 2 => " + time);

    }
}
