package com.adventofcode._2020;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class AdventOfCode23 {


    public static void main(String[] args) {
        String inputFile = "2020/input23.txt";
        List<String> input;
        try {
            input = Files.readAllLines(Paths.get("src/main/resources", inputFile));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }


        CircleNode<Integer> prev = null;
        Map<Integer, CircleNode<Integer>> cups = new HashMap<>();

        int curr = -1;
        int maxVal = 9;

        for (char c : input.get(0).toCharArray()) {
            int v = Character.getNumericValue(c);
            CircleNode<Integer> n = new CircleNode<>(v);
            if (prev == null) {
                curr = v;
            } else {
                prev.insertAfter(n);
            }
            prev = n;
            cups.put(v, n);
        }

        for (int i = 0; i < 100; i++) {
            System.out.println("i " + i);

            CircleNode<Integer> ccup = cups.get(curr);
            CircleNode<Integer> a = ccup.next.remove();
            CircleNode<Integer> b = ccup.next.remove();
            CircleNode<Integer> c = ccup.next.remove();

            int dest = curr - 1;
            if (dest < 1) { dest = maxVal; }
            CircleNode<Integer> dcup = cups.get(dest);
            while (dcup == a || dcup == b || dcup == c) {
                dest = dest - 1;
                if (dest < 1) { dest = maxVal; }

                dcup = cups.get(dest);
            }
            dcup.insertAfter(c);
            dcup.insertAfter(b);
            dcup.insertAfter(a);
            curr = ccup.next.value;
        }

        CircleNode<Integer> o = cups.get(1).next;
        while (!o.value.equals(1)) {
            System.out.print(o.value);
            o = o.next;
        }

        new AdventOfCode23().part2(input.get(0));


    }

    private void part2(String input)
    {
        CircleNode<Integer> prev = null;
        Map<Integer, CircleNode<Integer>> cups = new HashMap<>();

        int curr = -1;
        int maxVal = -1;

        for (char c : input.toCharArray()) {
            int v = Character.getNumericValue(c);
            CircleNode<Integer> n = new CircleNode<>(v);
            if (prev == null) {
                curr = v;
            } else {
                prev.insertAfter(n);
            }
            prev = n;
            cups.put(v, n);
        }
        for (int v = 10; v <= 1_000_000; v++) {
            CircleNode<Integer> n = new CircleNode<>(v);
            prev.insertAfter(n);
            prev = n;
            cups.put(v, n);
        }
        maxVal = cups.keySet().stream().mapToInt(Integer::valueOf).max().getAsInt();

        for (int i = 0; i < 10_000_000; i++) {
            if (i % 100000 == 0) { System.out.println("i " + i); }

            CircleNode<Integer> ccup = cups.get(curr);
            CircleNode<Integer> a = ccup.next.remove();
            CircleNode<Integer> b = ccup.next.remove();
            CircleNode<Integer> c = ccup.next.remove();

            int dest = curr - 1;
            if (dest < 1) { dest = maxVal; }
            CircleNode<Integer> dcup = cups.get(dest);
            while (dcup == a || dcup == b || dcup == c) {
                dest = dest - 1;
                if (dest < 1) { dest = maxVal; }

                dcup = cups.get(dest);
            }

            dcup.insertAfter(c);
            dcup.insertAfter(b);
            dcup.insertAfter(a);
            curr = ccup.next.value;
        }

        CircleNode<Integer> o = cups.get(1).next;
        long a = o.value;
        long b = o.next.value;
        System.out.println(a * b);
    }



}

class CircleNode<T> {
    public CircleNode<T> next = this;
    public CircleNode<T> previous = this;

    public final T value;

    public CircleNode(T value) {
        this.value = value;
    }

    public CircleNode<T> remove() {
        previous.next = next;
        next.previous = previous;

        return this;
    }

    public CircleNode<T> jumpAhead(int count) {
        CircleNode<T> rv = this;
        for (int i = 0; i < count; i++) {
            rv = rv.next;
        }
        return rv;
    }

    public CircleNode<T> jumpBack(int count) {
        CircleNode<T> rv = this;
        for (int i = 0; i < count; i++) {
            rv = rv.previous;
        }
        return rv;
    }

    public CircleNode<T> insertAfter(CircleNode<T> node) {
        next.previous = node;
        node.previous = this;
        node.next = next;
        this.next = node;

        return node;
    }

    public CircleNode<T> insertBefore(CircleNode<T> node) {
        previous.next = node;
        node.previous = previous;
        node.next = this;
        this.previous = node;

        return node;
    }

    @Override
    public String toString() {
        return next.toString(new StringBuilder(), this);
    }

    public String toString(StringBuilder prefix, CircleNode<T> stop) {
        if (Objects.equals(this, stop)) {
            return prefix.toString();
        } else {
            prefix.append(',').append(value);
            return next.toString(prefix, stop);
        }
    }
}
