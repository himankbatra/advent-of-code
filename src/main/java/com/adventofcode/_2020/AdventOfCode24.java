package com.adventofcode._2020;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class AdventOfCode24 {


    public static void main(String[] args) {
        String inputFile = "2020/input24.txt";
        List<String> input;
        try {
            input = Files.readAllLines(Paths.get("src/main/resources", inputFile));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        long calc = new AdventOfCode24().calc(input);
        System.out.println("calc = " + calc);

        long calc2 = new AdventOfCode24().calc2(input);
        System.out.println("calc2 = " + calc2);

    }

    Map<Pair<Integer, Integer>, Boolean> g = new HashMap<>();

    private long calc(List<String> strs) {
        long sum = 0;
        for (String str : strs) {
            Pair<Integer, Integer> pos = go(str);
            g.put(pos, !g.getOrDefault(pos, false));
        }
        for (Boolean value : g.values()) {
            if (value) {
                sum++;
            }
        }
        return sum;
    }

    private Pair<Integer, Integer> go(String str) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == 'e') {
                x++;
            } else if (c == 'w') {
                x--;
            } else if (c == 'n') {
                char d = str.charAt(++i);
                if (d == 'w') {
                    x--;
                    y++;
                } else if (d == 'e') {
                    y++;
                }
            } else if (c == 's') {
                char d = str.charAt(++i);
                if (d == 'w') {
                    y--;
                } else if (d == 'e') {
                    x++;
                    y--;
                }
            }
        }
        return Pair.with(y, x);
    }



    private long calc2(List<String> strs) {
        Map<Pair<Integer, Integer>, Boolean> g = new HashMap<>();
        for (String str : strs) {
            Pair<Integer, Integer> pos = go2(str);
            g.put(pos, !g.getOrDefault(pos, false));
        }
        for (int i = 0; i < 100; i++) {
            System.out.println("i = " + i);
            g = flip(g);
        }
        long sum = 0;
        for (Boolean value : g.values()) {
            if (value) {
                sum++;
            }
        }
        return sum;
    }

    int dx[] = {1, -1, -1, 0, 0, 1};
    int dy[] = {0, 0, 1, 1, -1, -1};

    private Map<Pair<Integer, Integer>, Boolean> flip(Map<Pair<Integer, Integer>, Boolean> from) {
        Map<Pair<Integer, Integer>, Boolean> to = new HashMap<>();
        for (int y = -150; y <= 150; y++) {
            for (int x = -150; x <= 150; x++) {
                final Boolean now = from.getOrDefault(Pair.with(y, x), false);
                int adjacent = 0;
                for (int dir = 0; dir < dx.length; dir++) {
                    int xx = x + dx[dir];
                    int yy = y + dy[dir];
                    if (from.getOrDefault(Pair.with(yy, xx), false)) {
                        adjacent++;
                    }
                }
                if (now) {
                    if (adjacent == 0 || adjacent > 2) {
                        to.put(Pair.with(y, x), false);
                    } else {
                        to.put(Pair.with(y, x), true);
                    }
                } else {
                    if (adjacent == 2) {
                        to.put(Pair.with(y, x), true);
                    } else {
                        to.put(Pair.with(y, x), false);
                    }
                }
            }
        }
        return to;
    }

    private Pair<Integer, Integer> go2(String str) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == 'e') {
                x++;
            } else if (c == 'w') {
                x--;
            } else if (c == 'n') {
                char d = str.charAt(++i);
                if (d == 'w') {
                    x--;
                    y++;
                } else if (d == 'e') {
                    y++;
                }
            } else if (c == 's') {
                char d = str.charAt(++i);
                if (d == 'w') {
                    y--;
                } else if (d == 'e') {
                    x++;
                    y--;
                }
            }
        }
        return Pair.with(y, x);
    }

}
class Pair<T, R> {

    private T key;
    private R value;

    public Pair() {
    }


    public Pair(T key, R value) {
        this.key = key;
        this.value = value;
    }

    public static <T, R> Pair<T, R> with(T key, R value) {
        return new Pair<>(key, value);
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public R getValue() {
        return value;
    }

    public void setValue(R value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(key, pair.key) &&
                Objects.equals(value, pair.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
