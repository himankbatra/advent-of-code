package com.adventofcode._2020;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class AdventOfCode15 {


    public static void main(String[] args) {
        String inputFile = "2020/input15.txt";
        List<String> input;
        try {
            input = Files.readAllLines(Paths.get("src/main/resources", inputFile));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }


        String a = input.get(0);
        List<Integer> g = Arrays.stream(a.split(",")).map(Integer::parseInt).collect(Collectors.toList());


        int[] t = new int[3000];
        for (int i = 0; i < g.size(); i++) {
            t[i] = g.get(i);
        }
        for (int i = g.size(); i < 2020; i++) {
            int last = t[i - 1];
            int j = i - 2;
            while (j >= 0 && t[j] != last) {
                j--;
            }
            if (j < 0) {
                t[i] = 0;
            } else {
                t[i] = i - 1 - j;
            }
        }
        System.out.println("result 1 => " + t[2019]);


        long calc = calc(g);
        System.out.println("result 2 => " + calc);

    }


    private static long calc(List<Integer> g) {
        int[] lastTime = new int[30000000];
        int[] lastTime2 = new int[30000000];
        int next = 0;
        for (int i = 1; i <= g.size(); i++) {
            next = g.get(i - 1);
            lastTime[next] = i;
        }
        for (int i = g.size() + 1; i <= 30000000; i++) {
            int last = lastTime[next];
            int last2 = lastTime2[next];
            if (last2 == 0) {
                next = 0;
            } else {
                next = last - last2;
            }
            int apa = lastTime[next];
            if (apa != 0) {
                lastTime2[next] = apa;
            }

            lastTime[next] = i;
        }
        return next;
    }

}
