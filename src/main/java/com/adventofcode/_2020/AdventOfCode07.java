package com.adventofcode._2020;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AdventOfCode07 {


    private static final Map<String, List<Bag>> nestedBags = new HashMap<>();

    private static final Map<String, Integer> totalBags = new HashMap<>();

    private static class Bag {
        String color;
        int count;

        public Bag(String color, int count) {
            this.color = color;
            this.count = count;
        }
    }


    public static void main(String[] args) {
        String inputFile = "2020/input07.txt";
        List<String> input;
        try {
            input = Files.readAllLines(Paths.get("src/main/resources", inputFile));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        Pattern pattern = Pattern.compile("(.*) bag(s?)");
        Pattern pattern2 = Pattern.compile("(\\d+) (.*) bag(s?)(\\.?)");
        for (String str : input) {
            String[] split = str.split(" contain ");
            Matcher matcher = pattern.matcher(split[0]);
            if (!matcher.matches()) {
                continue;
            }
            String from = matcher.group(1);
            split = split[1].split(", ");
            List<Bag> bags = new ArrayList<>();
            for (String s : split) {
                matcher = pattern2.matcher(s);
                if (matcher.matches()) {
                    bags.add(new Bag(matcher.group(2), Integer.parseInt(matcher.group(1))));
                }
            }
            nestedBags.put(from, bags);
        }

        Set<String> colors = new HashSet<>();
        String color = "shiny gold";
        colors.add(color);
        boolean added;
        do {
            added = false;
            for (Map.Entry<String, List<Bag>> entry : nestedBags.entrySet()) {
                for (Bag bag : entry.getValue()) {
                    if (colors.contains(bag.color) && !colors.contains(entry.getKey())) {
                        added = true;
                        colors.add(entry.getKey());
                    }
                }
            }
        } while (added);

        System.out.println("result 1 => " + (colors.size() - 1));

        System.out.println("result 2 => " + (getSumOfBags(color) - 1));

    }


    private static int getSumOfBags(String color) {
        if (totalBags.containsKey(color)) {
            return totalBags.get(color);
        }
        int sum = 1;
        for (Bag bag : nestedBags.get(color)) {
            sum += bag.count * getSumOfBags(bag.color);
        }
        totalBags.put(color, sum);
        return sum;
    }


}
