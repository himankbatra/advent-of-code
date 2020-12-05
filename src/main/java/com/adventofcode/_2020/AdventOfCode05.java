package com.adventofcode._2020;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;


public class AdventOfCode05 {

    public static void main(String[] args) {
        String inputFile = "2020/input05.txt";
        List<String> input;
        try {
            input = Files.readAllLines(Paths.get("src/main/resources", inputFile));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        int rows = 128;
        int cols = 8;

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int sumOfSeatIds = 0;
        for (String i : input) {
            char[] chars = i.toCharArray();
            int r1 = 0;
            int r2 = rows - 1;
            int rc1 = 0;
            int rc2 = cols - 1;
            for (char c : chars) {
                if (r1 != r2) {
                    if (c == 'F') {
                        float res = (float) (r2 - r1) / 2;
                        r2 = r1 + (int) Math.floor(res);
                    } else if (c == 'B') {
                        float res = (float) (r2 - r1) / 2;
                        r1 = r1 + (int) Math.ceil(res);
                    }
                }
                if (rc1 != rc2) {
                    if (c == 'L') {
                        float res = (float) (rc2 - rc1) / 2;
                        rc2 = rc1 + (int) Math.floor(res);
                    } else if (c == 'R') {
                        float res = (float) (rc2 - rc1) / 2;
                        rc1 = rc1 + (int) Math.ceil(res);
                    }
                }
            }
            int seatId = (r1 * 8) + rc1;
            if (max < seatId) {
                max = seatId;
            }
            if (seatId < min) {
                min = seatId;
            }
            sumOfSeatIds = sumOfSeatIds + seatId;
        }
        System.out.println("result1 = " + max);
        int missingSeatId = IntStream.rangeClosed(min, max).sum() - sumOfSeatIds;
        System.out.println("result2 = " + missingSeatId);
    }
}
