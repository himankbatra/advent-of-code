package com.adventofcode._2020;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


public class AdventOfCode17 {


    public static void main(String[] args) {
        String inputFile = "2020/input17.txt";
        List<String> input;
        try {
            input = Files.readAllLines(Paths.get("src/main/resources", inputFile));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        long calc = calc(input);
        System.out.println("result 1 => " + calc);

        long calc2 = calc2(input);
        System.out.println("result 2 => " + calc2);


    }


    private static long calc(List<String> strs) {
        long sum = 0;
        char[][][][] g = new char[2][30][30][30];
        {
            int y = 0;
            for (String str : strs) {
                for (int x = 0; x < str.length(); x++) {
                    char c = str.charAt(x);
                    g[0][10][y + 10][x + 10] = c;
                }
                y++;
            }
        }


        char[][][] next = null;
        char[][][] now;
        for (int cycle = 0; cycle < 6; cycle++) {
            now = g[cycle % 2];
            next = g[(cycle + 1) % 2];

            for (int z = 0; z < 30; z++) {
                for (int y = 0; y < 30; y++) {
                    for (int x = 0; x < 30; x++) {
                        int count = grannCount(now, x, y, z);
                        char val = '.';
                        if (now[z][y][x] == '#' && (count == 2 || count == 3)) {
                            val = '#';
                        } else if (now[z][y][x] != '#' && count == 3) {
                            val = '#';
                        }
                        next[z][y][x] = val;
                    }
                }
            }
        }

        for (int z = 0; z < 30; z++) {
            for (int y = 0; y < 30; y++) {
                for (int x = 0; x < 30; x++) {
                    if (next[z][y][x] == '#') {
                        sum++;
                    }
                }
            }
        }

        return sum;
    }

    private static int grannCount(char[][][] now, int x, int y, int z) {
        int count = 0;
        for (int dz = -1; dz <= 1; dz++) {
            int nyz = z + dz;
            if (nyz < 0 || nyz >= 30) {
                continue;
            }
            for (int dy = -1; dy <= 1; dy++) {
                int nyy = y + dy;
                if (nyy < 0 || nyy >= 30) {
                    continue;
                }
                for (int dx = -1; dx <= 1; dx++) {
                    int nyx = x + dx;
                    if (nyx < 0 || nyx >= 30) {
                        continue;
                    }
                    if (dz == 0 && dy == 0 && dx == 0) {
                        continue;
                    }
                    if (now[nyz][nyy][nyx] == '#') {
                        count++;
                    }
                }
            }
        }
        return count;
    }


    private static long calc2(List<String> strs) {
        long sum = 0;
        char[][][][][] g = new char[2][30][30][30][30];
        {
            int y = 0;
            for (String str : strs) {
                for (int x = 0; x < str.length(); x++) {
                    char c = str.charAt(x);
                    g[0][10][10][y + 10][x + 10] = c;
                }
                y++;
            }
        }

        char[][][][] next = null;
        char[][][][] now;
        for (int cycle = 0; cycle < 6; cycle++) {
            now = g[cycle % 2];
            next = g[(cycle + 1) % 2];
            for (int w = 0; w < 30; w++) {
                for (int z = 0; z < 30; z++) {
                    for (int y = 0; y < 30; y++) {
                        for (int x = 0; x < 30; x++) {
                            int count = grannCount2(now, x, y, z, w);
                            char val = '.';
                            if (now[w][z][y][x] == '#' && (count == 2 || count == 3)) {
                                val = '#';
                            } else if (now[w][z][y][x] != '#' && count == 3) {
                                val = '#';
                            }
                            next[w][z][y][x] = val;
                        }
                    }
                }
            }
        }
        for (int w = 0; w < 30; w++) {
            for (int z = 0; z < 30; z++) {
                for (int y = 0; y < 30; y++) {
                    for (int x = 0; x < 30; x++) {
                        if (next[w][z][y][x] == '#') {
                            sum++;
                        }
                    }
                }
            }
        }
        return sum;
    }

    private static int grannCount2(char[][][][] now, int x, int y, int z, int w) {
        int count = 0;
        for (int dw = -1; dw <= 1; dw++) {
            int nyw = w + dw;
            if (nyw < 0 || nyw >= 30) {
                continue;
            }
            for (int dz = -1; dz <= 1; dz++) {
                int nyz = z + dz;
                if (nyz < 0 || nyz >= 30) {
                    continue;
                }
                for (int dy = -1; dy <= 1; dy++) {
                    int nyy = y + dy;
                    if (nyy < 0 || nyy >= 30) {
                        continue;
                    }
                    for (int dx = -1; dx <= 1; dx++) {
                        int nyx = x + dx;
                        if (nyx < 0 || nyx >= 30) {
                            continue;
                        }
                        if (dw == 0 && dz == 0 && dy == 0 && dx == 0) {
                            continue;
                        }
                        if (now[nyw][nyz][nyy][nyx] == '#') {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

}
