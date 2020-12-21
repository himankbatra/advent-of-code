package com.adventofcode._2020;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class AdventOfCode21 {


    public static void main(String[] args) {
        String inputFile = "2020/input21.txt";
        List<String> input;
        try {
            input = Files.readAllLines(Paths.get("src/main/resources", inputFile));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        Set<String> allergies = new HashSet<>();
        Set<String> ingredients = new HashSet<>();
        Map<String, Integer> counts = new HashMap<>();
        for (String line : input) {
            String[] parts = line.split(" \\(contains ");
            for (String ingr : parts[0].split(" ")) {
                ingredients.add(ingr);
                counts.merge(ingr, 1, (o, n) -> o + n);
            }
            for (String aller : parts[1].replace(')', ' ').trim().split(", ")) {
                allergies.add(aller.trim());
            }
        }

        System.out.println(ingredients);
        System.out.println(allergies);

        Map<String, Set<String>> possible = new HashMap<>();
        for (String aller : allergies) {
            possible.put(aller, new HashSet<>(ingredients));
        }
        for (String line : input) {
            String[] parts = line.split(" \\(contains ");
            List<String> ingrs = Arrays.asList(parts[0].split(" "));
            for (String aller : parts[1].replace(')', ' ').trim().split(", ")) {
                for (String ingr : ingredients) {
                    if (!ingrs.contains(ingr)) { possible.get(aller).remove(ingr); }
                }
            }
        }

        System.out.println(possible);
        int cnt = 0;
        outer: for (String ingr : ingredients) {
            for (Set<String> poss : possible.values()) {
                if (poss.contains(ingr)) { continue outer; }
            }
            cnt += counts.get(ingr);
        }
        System.out.println(cnt);


        part2(input);

    }


    private static void part2(List<String> lines)
    {
        Set<String> allergies = new HashSet<>();
        Set<String> ingredients = new HashSet<>();
        for (String line : lines) {
            String[] parts = line.split(" \\(contains ");
            for (String ingr : parts[0].split(" ")) {
                ingredients.add(ingr);
            }
            for (String aller : parts[1].replace(')', ' ').trim().split(", ")) {
                allergies.add(aller.trim());
            }
        }

        System.out.println(ingredients);
        System.out.println(allergies);

        Map<String, Set<String>> possible = new HashMap<>();
        for (String aller : allergies) {
            possible.put(aller, new HashSet<>(ingredients));
        }

        for (String line : lines) {
            String[] parts = line.split(" \\(contains ");
            List<String> ingrs = Arrays.asList(parts[0].split(" "));
            for (String aller : parts[1].replace(')', ' ').trim().split(", ")) {
                for (String ingr : ingredients) {
                    if (!ingrs.contains(ingr)) { possible.get(aller).remove(ingr); }
                }
            }
        }

        Set<String> complete = new HashSet<>();
        while (complete.size() < allergies.size()) {
            for (String aller : allergies) {
                if (possible.get(aller).size() == 1 && !complete.contains(aller)) {
                    complete.add(aller);
                    String v = possible.get(aller).iterator().next();
                    for (String b : allergies) {
                        if (!aller.equals(b)) { possible.get(b).remove(v); }
                    }
                }
            }
        }

        System.out.println(possible);
        String ans = "";
        for (String a : allergies.stream().sorted().collect(Collectors.toList())) {
            ans += "," + possible.get(a).iterator().next();
        }
        System.out.println(ans.substring(1));
    }




}
