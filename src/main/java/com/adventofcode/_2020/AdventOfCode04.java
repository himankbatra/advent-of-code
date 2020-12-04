package com.adventofcode._2020;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AdventOfCode04 {

    public static void main(String[] args) {
        String inputFile = "2020/input04.txt";
        List<String> input;
        try {
            input = Files.readAllLines(Paths.get("src/main/resources", inputFile));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        String pattern1 = "byr";
        String pattern2 = "iyr";
        String pattern3 = "eyr";
        String pattern4 = "hgt";
        String pattern5 = "hcl";
        String pattern6 = "ecl";
        String pattern7 = "pid";

        input.add("");
        int result = 0;
        int result2 = 0;
        List<String> details = new ArrayList<>();
        Map<String, String> allDetails = new HashMap<>();
        for (String i : input) {
            if (i.length() == 0) {
                if (details
                        .containsAll(Arrays.asList(pattern1, pattern2, pattern3, pattern4, pattern5, pattern6, pattern7))) {
                    result++;
                    String s1 = allDetails.get(pattern1);
                    String s2 = allDetails.get(pattern2);
                    String s3 = allDetails.get(pattern3);
                    String s4 = allDetails.get(pattern4);
                    String s5 = allDetails.get(pattern5);
                    String s6 = allDetails.get(pattern6);
                    String s7 = allDetails.get(pattern7);
                    if (s1.length() == 4 && Integer.parseInt(s1) >= 1920 && Integer.parseInt(s1) <= 2002) {
                        if (s2.length() == 4 && Integer.parseInt(s2) >= 2010 && Integer.parseInt(s2) <= 2020) {
                            if (s3.length() == 4 && Integer.parseInt(s3) >= 2020 && Integer.parseInt(s3) <= 2030) {
                                if (s4.endsWith("in") || s4.endsWith("cm")) {
                                    int i1 = Integer.parseInt(s4.substring(0, s4.length() - 2));
                                   if((s4.endsWith("in") && i1>=59 && i1 <=96) || (s4.endsWith("cm") && i1>=150 && i1 <=193))
                                     {
                                        if (s5.startsWith("#") && s5.length() == 7
                                                && s5.substring(1).matches("[a-f0-9]+")) {
                                            List<String> strings = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
                                            if (strings.contains(s6)) {
                                                if (s7.length() == 9 && s7.matches("\\d+")) {
                                                    result2++;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                details.clear();
                allDetails.clear();
            } else {
                Arrays.stream(i.split(" "))
                        .forEach(s -> allDetails.put(s.split(":")[0], s.split(":")[1]));
                details.addAll(allDetails.keySet());
            }
        }

        System.out.println("result--> " + result);
        System.out.println("result2--> " + result2);

    }
}
