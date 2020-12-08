package com.adventofcode._2020;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class AdventOfCode08 {


    public static void main(String[] args) {
        String inputFile = "2020/input08.txt";
        List<String> input;
        try {
            input = Files.readAllLines(Paths.get("src/main/resources", inputFile));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        int acc = 0;
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < input.size(); ) {
            String str = input.get(i);
            String[] s = str.split(" ");
            if (indexes.contains(i)) {
                break;
            } else {
                indexes.add(i);
            }
            switch (s[0]) {
                case "nop":
                    i++;
                    break;
                case "acc":
                    if (s[1].startsWith("-")) {
                        acc = acc - Integer.parseInt(s[1].substring(1));
                    } else if (s[1].startsWith("+")) {
                        acc = acc + Integer.parseInt(s[1].substring(1));
                    }
                    i++;
                    break;
                case "jmp":
                    if (s[1].startsWith("-")) {
                        i = i - Integer.parseInt(s[1].substring(1));
                    } else if (s[1].startsWith("+")) {
                        i = i + Integer.parseInt(s[1].substring(1));
                    }
                    break;
            }
        }

        System.out.println("acc = " + acc);

        int acc1 = 0;
        outer:
        for (int j = 0; j < input.size(); j++) {
            List<Integer> indexes1 = new ArrayList<>();
            acc1 = 0;
            for (int i = 0; i < input.size(); ) {
                String str = input.get(i);
                String[] s = str.split(" ");
                if (indexes1.contains(i)) {
                    break;
                } else {
                    indexes1.add(i);
                }

                if (i == j) {
                    if (s[0].equals("jmp")) {
                        s[0] = "nop";
                    } else if (s[0].equals("nop")) {
                        s[0] = "jmp";
                    }
                }

                switch (s[0]) {
                    case "nop":
                        i++;
                        break;
                    case "acc":
                        if (s[1].startsWith("-")) {
                            acc1 = acc1 - Integer.parseInt(s[1].substring(1));
                        } else if (s[1].startsWith("+")) {
                            acc1 = acc1 + Integer.parseInt(s[1].substring(1));
                        }
                        i++;
                        break;
                    case "jmp":
                        if (s[1].startsWith("-")) {
                            i = i - Integer.parseInt(s[1].substring(1));
                        } else if (s[1].startsWith("+")) {
                            i = i + Integer.parseInt(s[1].substring(1));
                        }
                        break;
                }
                if (i == input.size()) {
                    break outer;
                }
            }
        }

        System.out.println("acc1 = " + acc1);

    }


}
