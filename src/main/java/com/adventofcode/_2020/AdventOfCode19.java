package com.adventofcode._2020;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdventOfCode19 {




     public static void main(String[] args) {

        Scanner reader ;
        try {
            reader = new Scanner(new File("src/main/resources/2020/input19.txt"));
        } catch (Exception e) {
            throw new RuntimeException("File not found");
        }

          String[] rules= new String[150];
        List<String> messages = new ArrayList<>();

        while (reader.hasNext()) {
            String line = reader.nextLine();
            if (line.contains(":")) {
                int i = Integer.parseInt(line.substring(0, line.indexOf(":")));
                // replace the quotes around "a" and "b" when we find them
                rules[i] = line.substring(line.indexOf(":") + 2).replace("\"", "");
            } else if (!line.equals(""))
                messages.add(line);
        }

        new AdventOfCode19().part1(rules,messages);
        new AdventOfCode19().part2(rules,messages);
    }

    public void part1(String[] rules,List<String> messages) {
        // expected regex for sample input: "^(a((aa|bb)(ab|ba)|(ab|ba)(aa|bb))b)$"
        // create a regex for rule 0
        String re = "^" + makeRegex(rules, 0) + "$";

        int count = 0;
        for (String m : messages)
            if (m.matches(re))
                count++;

        System.out.println("Valid messages: " + count);

    }

    public void part2(String[] rules,List<String> messages) {
        // we are now making changes to rules 8 and 11
        // rule 0: 8 11
        // new rule 8: 42 | 42 8
        // new rule 11: 42 31 | 42 11 31
        // therefore, rule 0: (42 | 42 8) (42 31 | 42 11 31)
        // as far as I can tell, the pattern should begin with one or more copies of
        // rule42, followed X number of rule42 + X number of rule31

        // I'm hoping the largest X might be is 10?
        // we will create a regex for this pattern
        // ^((42+) ((42 31) | (42 42 31 31) | (42 42 42 31 31 31) | (42 42 42 42 31 31
        // 31 31) | (42 42 42 42 42 31 31 31 31 31) | (42 42 42 42 42 42 31 31 31 31 31
        // 31) | (42 42 42 42 42 42 42 31 31 31 31 31 31 31) | (42 42 42 42 42 42 42 42
        // 31 31 31 31 31 31 31 31) | (42 42 42 42 42 42 42 42 42 31 31 31 31 31 31 31
        // 31 31) | (42 42 42 42 42 42 42 42 42 42 31 31 31 31 31
        // 31 31 31 31 31)))$
        // I know, I want to throw up too.

        // create a regex for rule 42 and rule 31
        String r42 = makeRegex(rules, 42);
        String r31 = makeRegex(rules, 31);

        // originally created this using a loop, but this is more readable... i think
        String masterRegex = "^((42+) ((42 31) | (42{2} 31{2}) | (42{3} 31{3}) | (42{4} 31{4}) | (42{5} 31{5}) | (42{6} 31{6}) | (42{7} 31{7}) | (42{8} 31{8}) | (42{9} 31{9}) | (42{10} 31{10})))$";
        masterRegex = masterRegex.replace("42", r42).replace("31", r31).replace(" ", "");


        int count = 0;
        for (String m : messages) {
            if (m.matches(masterRegex)) {
                count++;
            }
        }

        System.out.println("Valid messages: " + count);
    }

    private static String makeRegex(String[] rules, int i) {

        // as long as there is still a number somewhere in rules[i], find a number and
        // replace it with it's rule
        while (rules[i].matches(".*\\d.*")) {
            String[] parts = rules[i].split(" ");
            String rep = ""; // string that will replace rules[i] at each iteration
            for (String part : parts) {
                if (part.matches("\\d+")) {
                    // if part is numerical, add the rule from that index
                    // if the rule is 'a' or 'b', just add that letter
                    // otherwise add '( ' + rule + ' )' so that .split separates that number in next
                    // iteration
                    if (rules[Integer.parseInt(part)].matches("[ab]")) {
                        rep += rules[Integer.parseInt(part)];
                    } else {
                        rep += "( " + rules[Integer.parseInt(part)] + " )";
                    }
                } else {
                    // part is not numerical so just add whatever it is
                    rep += part;
                }
            }
            rules[i] = rep;
        }
        return "(" + rules[i] + ")";
    }

}
