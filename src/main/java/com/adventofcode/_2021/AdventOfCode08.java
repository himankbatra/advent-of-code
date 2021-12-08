package com.adventofcode._2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;


public class AdventOfCode08 {

    public static void main(String[] args) {

        long ans1 = getInput().filter(e -> !e.isBlank()).map(e -> e.split("\\|")[1])
                .flatMap(e -> Stream.of(e.split(" ")))
                .filter(e -> e.length() == 2 || e.length() == 3 || e.length() == 4 || e.length() == 7).count();

        System.out.println("ans1 = " + ans1);

        int sum = 0;
        for (String s : getInput().toList()) {
            String[] parts = s.split("\\|");
            String[] firstHalf = parts[0].strip().split(" ");
            String[] secondHalf = parts[1].strip().split(" ");
            String[] digits = new String[10];
            for (String sPart : firstHalf) {
                switch (sPart.length()) {
                    case 2 -> digits[1] = sPart;
                    case 3 -> digits[7] = sPart;
                    case 4 -> digits[4] = sPart;
                    case 7 -> digits[8] = sPart;
                }
            }
            for (String sPart : firstHalf) {
                if (sPart.length() == 6) {
                    if (isNine(digits, sPart))
                        digits[9] = sPart;
                    else if (isZero(digits, sPart))
                        digits[0] = sPart;
                    else
                        digits[6] = sPart;
                }
            }

            for (String sPart : firstHalf) {
                if (sPart.length() == 5) {
                    if (isThree(digits, sPart))
                        digits[3] = sPart;
                    else if (isFive(digits, sPart))
                        digits[5] = sPart;
                    else
                        digits[2] = sPart;
                }
            }

            for (int i = 0; i < 10; i++)
                digits[i] = orderString(digits[i]);

            int number = 0;
            for (String toFind : secondHalf) {
                String sorted = orderString(toFind);
                for (int i = 0; i < 10; i++)
                    if (digits[i].equals(sorted))
                        number = (number * 10) + i;
            }
            sum += number;
        }

        System.out.println("ans2 = " + sum);

    }

    private static Stream<String> getInput() {
        String inputFile = "2021/input08.txt";
        Stream<String> strings;
        try {
            strings = Files.lines(Paths.get("src/main/resources", inputFile));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return strings;
    }


    private static String orderString(String toOrder) {
        char[] toSort = toOrder.toCharArray();
        Arrays.sort(toSort);
        return new String(toSort);
    }

    private static boolean isNine(String[] digits, String sPart) {
        boolean nine = true;
        for (char c : digits[4].toCharArray()) {
            if (!sPart.contains(String.valueOf(c))) {
                nine = false;
                break;
            }
        }
        return nine;
    }

    private static boolean isZero(String[] digits, String sPart) {
        boolean one = true;
        for (char c : digits[1].toCharArray()) {
            if (!sPart.contains(String.valueOf(c))) {
                one = false;
                break;
            }
        }
        return one;
    }

    private static boolean isThree(String[] digits, String sPart) {
        boolean three = true;
        for (char c : digits[1].toCharArray()) {
            if (!sPart.contains(String.valueOf(c))) {
                three = false;
                break;
            }
        }
        return three;
    }

    private static boolean isFive(String[] digits, String sPart) {
        int missing = 0;
        for (char c : digits[6].toCharArray())
            if (!sPart.contains(String.valueOf(c)))
                missing++;
        return missing == 1;
    }


}

