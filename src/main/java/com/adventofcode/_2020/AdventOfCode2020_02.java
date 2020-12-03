package com.adventofcode._2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AdventOfCode2020_02 {
    public static void main(String[] args) {
        String inputFile = "2020/input_02.txt";
        List<String> passwords;
        try {
            passwords = Files.readAllLines(Paths.get("src/main/resources", inputFile));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        List<String> result = new ArrayList<>();
        for (String password : passwords) {
            String[] s = password.split(" ");
            String[] s1 = s[0].split("-");
            int min = Integer.parseInt(s1[0]);
            int max = Integer.parseInt(s1[1]);
            long count = s[2].chars().mapToObj(c -> (char) c)
                    .filter(c -> c.equals(s[1].toCharArray()[0])).count();
            if (count >= min && count <= max) {
                result.add(password);
            }
        }
        System.out.println(result);
        System.out.println(" result => " + result.size());

        List<String> result2 = new ArrayList<>();
        for (String password : passwords) {
            String[] s = password.split(" ");
            String[] s1 = s[0].split("-");
            int pos1 = Integer.parseInt(s1[0]) - 1;
            int pos2 = Integer.parseInt(s1[1]) - 1;
            char[] chars = s[2].toCharArray();
            if (((chars.length > pos1 && chars[pos1] == s[1].toCharArray()[0])
                    && (chars.length > pos2 && chars[pos2] != s[1].toCharArray()[0]))
                    ||
                    ((chars.length > pos1 && chars[pos1] != s[1].toCharArray()[0])
                            && (chars.length > pos2 && chars[pos2] == s[1].toCharArray()[0]))) {
                result2.add(password);
            }
        }
        System.out.println(result2);
        System.out.println("result2 => " + result2.size());
    }
}
