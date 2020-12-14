package com.adventofcode._2020;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class AdventOfCode14 {


    public static void main(String[] args) {
        String inputFile = "2020/input14.txt";
        List<String> input;
        try {
            input = Files.readAllLines(Paths.get("src/main/resources", inputFile));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        long[] mem = new long[100000];
        long mask = 0L;
        long maskx = 0L;

        for (String str : input) {
            if (str.startsWith("mask")) {
                String m = str.split(" = ")[1];
                mask = Long.parseLong(m.replace("X", "0"), 2);
                maskx = Long.parseLong(m.replace("1", "0").replace("X", "1"), 2);
            } else {
                String[] vals = str.split("] = ");
                int location = Integer.parseInt(vals[0].substring(4));
                long value = Long.parseLong(vals[1]);
                mem[location] = (maskx & value) + mask;
            }
        }

        System.out.println("result 1 => " + Arrays.stream(mem).sum());

        String mask1 = "";
        Map<Long, Long> mem1 = new HashMap<>();
        for (String it : input) {
            if (it.startsWith("mask")) {
                mask1 = it.split(" = ")[1];
            } else {
                String[] vals = it.split("] = ");
                String location = String.format("%1$" + 36 + "s"
                        , Integer.toBinaryString(Integer.parseInt(vals[0].substring(4))))
                        .replace(' ', '0');
                long value = Long.parseLong(vals[1]);

                for (String loc : floatingValues(mask1, location)) {
                    mem1.put(Long.parseLong(loc, 2), value);
                }
            }
        }
        System.out.println("result 2 => " + (mem1.values().stream().reduce(0L, Long::sum)));

    }

    static List<String> floatingValues(String mask, String address) {
        List<String> addresses = new ArrayList<>();
        if (mask.length() >= 1) {
            char mask_r = mask.charAt(mask.length() - 1);
            char address_r = address.charAt(address.length() - 1);
            List<String> recurse = floatingValues(mask.substring(0, mask.length() - 1)
                    , address.substring(0, address.length() - 1));
            if (mask_r == '0') {
                addresses.addAll(recurse.stream().map(c -> c + address_r).collect(Collectors.toList()));
            } else if (mask_r == '1') {
                addresses.addAll(recurse.stream().map(c -> c + mask_r).collect(Collectors.toList()));
            } else if (mask_r == 'X') {
                addresses.addAll(recurse.stream().map(c -> c + 0).collect(Collectors.toList()));
                addresses.addAll(recurse.stream().map(c -> c + 1).collect(Collectors.toList()));
            }
        } else {
            addresses.add("");
        }
        return addresses;
    }


}
