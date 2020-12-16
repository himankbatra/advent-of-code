package com.adventofcode._2020;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AdventOfCode16 {

    public static void main(String[] args) {
        String inputFile = "2020/input16.txt";
        List<String> input;
        try {
            input = Files.readAllLines(Paths.get("src/main/resources", inputFile));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        long calc = calc(input);
        System.out.println("result 1 => " + calc);

        long calc2 = calc2(input);
        System.out.println("result 2 => " + calc2);

    }


    private static long calc(List<String> strs) {
        long sum = 0;
        List<List<String>> lists = splitByDoubleNewline(strs);

        Set<Integer> valid = new HashSet<>();
        for (String str : lists.get(0)) {
            List<Integer> allInts = findAllPositiveInts(str);
            for (int i = allInts.get(0); i <= allInts.get(1); i++) {
                valid.add(i);
            }
            for (int i = allInts.get(2); i <= allInts.get(3); i++) {
                valid.add(i);
            }
        }

        for (String str : lists.get(2)) {
            for (Integer allInt : findAllPositiveInts(str)) {
                if (!valid.contains(allInt)) {
                    sum += allInt;
                }
            }
        }
        return sum;
    }


    public static List<List<String>> splitByDoubleNewline(List<String> strs) {
        List<List<String>> groups = new ArrayList<>();
        List<String> group = new ArrayList<>();
        for (String str : strs) {
            if (str.isEmpty()) {
                groups.add(group);
                group = new ArrayList<>();
            } else {
                group.add(str);
            }
        }
        if (!group.isEmpty()) {
            groups.add(group);
        }
        return groups;
    }

    public static List<Integer> findAllPositiveInts(String haystack) {
        return findAll(haystack, "[\\d]+", Integer::parseInt);
    }

    public static <T> List<T> findAll(String haystack, String regexp, Function<String, T> converter) {
        Matcher matcher = Pattern.compile(regexp).matcher(haystack);
        List<T> allMatches = new ArrayList<>();
        while (matcher.find()) {
            allMatches.add(converter.apply(matcher.group()));
        }
        return allMatches;
    }


    private static long calc2(List<String> strs) {
        List<List<String>> lists = splitByDoubleNewline(strs);

        List<Integer> myTicket = findAllPositiveInts(lists.get(1).get(1));

        Map<String, Set<Integer>> valids = new HashMap<>();
        for (String str : lists.get(0)) {
            Set<Integer> valid = new HashSet<>();
            List<Integer> allInts = findAllPositiveInts(str);
            for (int i = allInts.get(0); i <= allInts.get(1); i++) {
                valid.add(i);
            }
            for (int i = allInts.get(2); i <= allInts.get(3); i++) {
                valid.add(i);
            }
            valids.put(str.substring(0, str.indexOf(":")), valid);
        }

        List<List<Integer>> validTickets = new ArrayList<>();
        validTickets.add(myTicket);
        for (String str : lists.get(2)) {
            int minAntal = Integer.MAX_VALUE;
            List<Integer> allInts = findAllPositiveInts(str);
            if (allInts.isEmpty()) {
                continue;
            }
            for (Integer allInt : allInts) {
                int antal = 0;
                for (Set<Integer> valid : valids.values()) {
                    if (valid.contains(allInt)) {
                        antal++;
                    }
                }
                minAntal = Math.min(minAntal, antal);
            }
            if (minAntal != 0) {
                validTickets.add(allInts);
            }
        }
        Map<String, Set<Integer>> mapping = new HashMap<>();
        for (String field : valids.keySet()) {
            Set<Integer> apa = new HashSet<>();
            for (int j = 0; j < 20; j++) {
                apa.add(j);
            }
            mapping.put(field, apa);
        }
        for (List<Integer> validTicket : validTickets) {
            for (int pos = 0; pos < 20; pos++) {
                Integer integer = validTicket.get(pos);
                for (Map.Entry<String, Set<Integer>> stringSetEntry : valids.entrySet()) {
                    if (!stringSetEntry.getValue().contains(integer)) {
                        mapping.get(stringSetEntry.getKey()).remove(pos);
                    }
                }
            }
        }
        for (int i = 0; i < 20; i++) {
            Set<Integer> onlyPresentOnce = new HashSet<>();
            for (Set<Integer> value : mapping.values()) {
                if (value.size() == 1) {
                    onlyPresentOnce.add(value.iterator().next());
                }
            }
            for (Set<Integer> value : mapping.values()) {
                if (value.size() != 1) {
                    value.removeAll(onlyPresentOnce);
                }
            }
        }
        long prod = 1;
        for (Map.Entry<String, Set<Integer>> stringSetEntry : mapping.entrySet()) {
            if (stringSetEntry.getKey().startsWith("departure")) {
                prod *= myTicket.get(stringSetEntry.getValue().iterator().next());
            }
        }
        //System.out.println("mapping = " + mapping);
        return prod;
    }


}
