package com.adventofcode._2020;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AdventOfCode20_1 {


    public static void main(String[] args) {
        String inputFile = "2020/input20.txt";
        List<String> input;
        try {
            input = Files.readAllLines(Paths.get("src/main/resources", inputFile));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        long calc = new AdventOfCode20_1().calc(input);
        System.out.println("calc = " + calc);

    }


    List<Img> imgs = new ArrayList<>();

    private long calc(List<String> strs) {
        long sum = 0;
        System.out.println("Util.splitByDoubleNewline(strs).size() = " + splitByDoubleNewline(strs).size());
        for (List<String> strings : splitByDoubleNewline(strs)) {
            int nr = findAllInts(strings.get(0)).get(0);
            char[][] bild = new char[10][];
            for (int i = 1; i <= 10; i++) {
                bild[i - 1] = strings.get(i).toCharArray();
            }
            imgs.add(new Img(nr, bild));
        }
        Arrays.fill(taken, -1);
        calc(0, 1);
        return sum;
    }


    public static List<Integer> findAllInts(String haystack) {
        return findAll(haystack, "[-\\d]+", Integer::parseInt);
    }

    public static <T> List<T> findAll(String haystack, String regexp, Function<String, T> converter) {
        Matcher matcher = Pattern.compile(regexp).matcher(haystack);
        List<T> allMatches = new ArrayList<>();
        while (matcher.find()) {
            allMatches.add(converter.apply(matcher.group()));
        }
        return allMatches;
    }

    int[] taken = new int[144];
    char[][][][] g = new char[12][12][][];

    private void calc(int i, long prod) {
        if(i==144){
            System.out.println("prod = " + prod);
            return;
        }
        int y = i / 12;
        int x = i % 12;
        for (int j = 0; j < 144; j++) {
            if (taken[j] != -1) {
                continue;
            }
            taken[j] = i;
            long nextProd = prod;
            Img ourImg = imgs.get(j);
            if(i==0||i==11||i==143||i==143-11){
                nextProd *= ourImg.nr;
            }
            for (int variant = 0; variant < 8; variant++) {
                if (fits(x, y, ourImg.variants[variant])) {
                    g[y][x] = ourImg.variants[variant];
                    calc(i + 1, nextProd);
                }
            }
            taken[j] = -1;
        }
    }

    private boolean fits(int x, int y, char[][] variant) {
        if (x > 0) {
            char[][] left = g[y][x - 1];
            for (int yy = 0; yy < 10; yy++) {
                if (left[yy][9] != variant[yy][0]) {
                    return false;
                }
            }
        }
        if (y > 0) {
            char[][] top = g[y - 1][x];
            for (int xx = 0; xx < 10; xx++) {
                if (top[9][xx] != variant[0][xx]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static class Img {
        int nr;
        char[][] bild;

        char[][][] variants = new char[8][][];

        public Img(int nr, char[][] bild) {
            this.nr = nr;
            this.bild = bild;
            variants[0] = bild;
            for (int i = 1; i < 4; i++) {
                variants[i] = rotate(variants[i - 1]);
            }
            for (int i = 0; i < 4; i++) {
                variants[4 + i] = flip(variants[i]);
            }
        }

        private char[][] rotate(char[][] from) {
            char[][] to = new char[10][10];
            for (int y = 0; y < 10; y++) {
                for (int x = 0; x < 10; x++) {
                    to[y][x] = from[9 - x][y];
                }
            }
            return to;
        }

        private char[][] flip(char[][] from) {
            char[][] to = new char[10][10];
            for (int y = 0; y < 10; y++) {
                for (int x = 0; x < 10; x++) {
                    to[y][x] = from[y][9 - x];
                }
            }
            return to;
        }
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

    private static long calc2(List<String> strs) {
        return 0L;
    }

}
