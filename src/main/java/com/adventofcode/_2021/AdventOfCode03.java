package com.adventofcode._2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AdventOfCode03 {

    public static void main(String[] args) {
        String inputFile = "2021/input03.txt";
        List<String> strings;
        try {
            strings = Files.lines(Paths.get("src/main/resources", inputFile))
                    .collect(Collectors.toList());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }


        Map<Integer, List<String>> map = new LinkedHashMap<>();

        for (String s : strings) {
            String[] split = s.split("");
            int j = 0;
            while (split.length > j) {
                List<String> stringArrayList = new ArrayList<>();
                map.putIfAbsent(j, stringArrayList);
                map.get(j).add(split[j]);
                j++;
            }

        }

        String gamma = "";
        String epsilon = "";

        for (Integer integer : map.keySet()) {

            String s = map.get(integer)
                    .stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet()
                    .stream().max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey).orElseThrow(RuntimeException::new);


            gamma = gamma + s;
            String s1 = map.get(integer)
                    .stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet()
                    .stream().min(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey).orElseThrow(RuntimeException::new);
            epsilon = epsilon + s1;

        }

        int ans1 = Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2);
        System.out.println("ans 1 = " + ans1);

        int j = 0;

        List<String> co2 = new ArrayList<>(strings);
        while (strings.size() > 1) {
            List<String> stringArrayList = new ArrayList<>();
            for (String s : strings) {
                String[] split = s.split("");
                stringArrayList.add(split[j]);
            }
            Map<String, Long> collect = stringArrayList
                    .stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            String max = collect.entrySet()
                    .stream().max(Map.Entry.<String, Long>comparingByValue()
                            .thenComparing(a -> a.getKey().equals("0") ? -1 : 1))
                    .map(Map.Entry::getKey).orElseThrow(RuntimeException::new);
            List<String> calculate = calculate(strings, max, j);
            strings = new ArrayList<>(calculate);
            j++;
        }

        j = 0;


        while (co2.size() > 1) {
            List<String> stringArrayList = new ArrayList<>();
            for (String s : co2) {
                String[] split = s.split("");
                stringArrayList.add(split[j]);
            }
            Map<String, Long> collect = stringArrayList
                    .stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            String min = collect.entrySet()
                    .stream().min(Map.Entry.<String, Long>comparingByValue()
                            .thenComparing(a -> a.getKey().equals("0") ? -1 : 1))
                    .map(Map.Entry::getKey).orElseThrow(RuntimeException::new);
            List<String> calculate1 = calculate(co2, min, j);
            co2 = new ArrayList<>(calculate1);
            j++;
        }

        System.out.println("ans 2 = "
                + Integer.parseInt(strings.get(0), 2) * Integer.parseInt(co2.get(0), 2));


    }


    static List<String> calculate(List<String> strings, String s, Integer index) {
        List<String> result = new ArrayList<>();
        for (String string : strings) {
            if (string.charAt(index) == s.charAt(0)) {
                result.add(string);
            }
        }
        return result;
    }

}
