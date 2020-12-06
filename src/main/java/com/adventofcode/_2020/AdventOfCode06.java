package com.adventofcode._2020;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class AdventOfCode06 {

    public static void main(String[] args) {
        String inputFile = "2020/input06.txt";
        List<String> input;
        try {
            input = Files.readAllLines(Paths.get("src/main/resources", inputFile));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        input.add("");
        int totalAnswers = 0;
        int totalCommonAnswers = 0;
        List<Character> allAnswers = new ArrayList<>();
        int groupSize = 0;
        for (String i : input) {
            groupSize++;
            if (i.length() == 0) {
                groupSize--;
                Map<Character, Long> distinctAnswersWithCount = allAnswers.stream()
                        .collect(Collectors.groupingBy(k -> k, Collectors.counting()));
                totalAnswers = totalAnswers + distinctAnswersWithCount.size();
                if (groupSize == 1) {
                    totalCommonAnswers = totalCommonAnswers + distinctAnswersWithCount.size();
                } else {
                    Long constantGroupSize = (long) groupSize;
                    totalCommonAnswers = totalCommonAnswers + (int) distinctAnswersWithCount.values()
                            .stream().filter(x -> x.equals(constantGroupSize)).count();
                }
                allAnswers.clear();
                groupSize = 0;
            }
            allAnswers.addAll(i.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        }
        System.out.println("result 1 = " + totalAnswers);
        System.out.println("result 2 = " + totalCommonAnswers);
    }

}
