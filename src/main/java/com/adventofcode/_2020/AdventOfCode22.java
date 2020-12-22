package com.adventofcode._2020;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.LongStream;

import static java.lang.Math.toIntExact;
import static java.util.stream.Collectors.toCollection;


public class AdventOfCode22 {


    public static void main(String[] args) {
        String inputFile = "2020/input22.txt";
        List<String> input;
        try {
            input = Files.readAllLines(Paths.get("src/main/resources", inputFile));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        Queue<Integer> player1 = new LinkedList<>();
        Queue<Integer> player2 = new LinkedList<>();
        boolean p1 = false;
        boolean p2 = false;
        for (String str : input) {
            if (str.isEmpty()) {
                continue;
            }
            if (str.equals("Player 1:")) {
                p1 = true;
                continue;
            }
            if (str.equals(("Player 2:"))) {
                p1 = false;
                p2 = true;
                continue;
            }
            if (p1) {
                player1.add(Integer.parseInt(str));
            }
            if (p2) {
                player2.add(Integer.parseInt(str));
            }
        }

        while (!player1.isEmpty() && !player2.isEmpty()) {
            Integer remove1 = player1.remove();
            Integer remove2 = player2.remove();

            if (remove1 > remove2) {
                player1.add(remove1);
                player1.add(remove2);
            } else {
                player2.add(remove2);
                player2.add(remove1);
            }
        }

        int player1Size = player1.size();
        System.out.println("player1Size = " + player1Size);
        int player2Size = player2.size();
        System.out.println("player2Size = " + player2Size);
        long answer = 0L;
        while (player1Size != 0) {

            answer += player1.remove() * player1Size;
            player1Size--;
        }
        while (player2Size != 0) {
            answer += player2.remove() * player2Size;
            player2Size--;
        }

        System.out.println("answer = " + answer);


        player1 = new LinkedList<>();
        player2 = new LinkedList<>();

        p1 = false;
        p2 = false;
        for (String str : input) {
            if (str.isEmpty()) {
                continue;
            }
            if (str.equals("Player 1:")) {
                p1 = true;
                continue;
            }
            if (str.equals(("Player 2:"))) {
                p1 = false;
                p2 = true;
                continue;
            }
            if (p1) {
                player1.add(Integer.parseInt(str));
            }
            if (p2) {
                player2.add(Integer.parseInt(str));
            }
        }

        long answer2 = 0L;

        if(isPlayer1Winner(player1, player2))
        {
            player1Size = player1.size();
            while (player1Size != 0) {
                answer2 += player1.remove() * player1Size;
                player1Size--;
            }
        }
        else
        {
            player2Size = player2.size();
            while (player2Size != 0 ) {
                answer2 += player2.remove() * player2Size;
                player2Size--;
            }
        }

        System.out.println("answer2 = " + answer2);


        Object o = new AdventOfCode22().part1(input);
        System.out.println("o = " + o);

        Object o1 = new AdventOfCode22().part2(input);
        System.out.println("o1 = " + o1);

    }


    public Object part1(List<String> in) {
        Deque<Long> p12 = new ArrayDeque<>();
        Deque<Long> p22 = new ArrayDeque<>();
        boolean p1 = false;
        boolean p2 = false;
        for (String str : in) {
            if (str.isEmpty()) {
                continue;
            }
            if (str.equals("Player 1:")) {
                p1 = true;
                continue;
            }
            if (str.equals(("Player 2:"))) {
                p1 = false;
                p2 = true;
                continue;
            }
            if (p1) {
                p12.add(Long.parseLong(str));
            }
            if (p2) {
                p22.add(Long.parseLong(str));
            }
        }
        while (p12.size() > 0 && p22.size() > 0) {
            long l1 = p12.pop();
            long l2 = p22.pop();
            if (l1 > l2) {
                p12.add(l1);
                p12.add(l2);
            } else {
                p22.add(l2);
                p22.add(l1);
            }
        }
        Deque<Long> winner = p12.size() > 0 ? p12 : p22;
        return calcScore(winner);
    }


    public Object part2(List<String> in) {

        Deque<Long> p12 = new ArrayDeque<>();
        Deque<Long> p22 = new ArrayDeque<>();
        boolean p1 = false;
        boolean p2 = false;
        for (String str : in) {
            if (str.isEmpty()) {
                continue;
            }
            if (str.equals("Player 1:")) {
                p1 = true;
                continue;
            }
            if (str.equals(("Player 2:"))) {
                p1 = false;
                p2 = true;
                continue;
            }
            if (p1) {
                p12.add(Long.parseLong(str));
            }
            if (p2) {
                p22.add(Long.parseLong(str));
            }
        }

        return calcScore(playGame(p12, p22) == Player.P1 ? p12 : p22);
    }

    private long calcScore(Deque<Long> winner) {
        return LongStream.rangeClosed(1, winner.size()).boxed().sorted(Comparator.reverseOrder()).mapToLong(l -> winner.pop() * l).sum();
    }

    private ArrayDeque<Long> getInput(int i, String[] input) {
        return Arrays.stream(input[i].split("\n")).filter(e -> !e.startsWith("Player")).map(Long::parseLong)
                .collect(toCollection(ArrayDeque::new));
    }

    public Player playGame(Deque<Long> p1, Deque<Long> p2) {
        Set<List<Long>> playedGames = new HashSet<>();
        while (p1.size() > 0 && p2.size() > 0) {
            if (!playedGames.add(new ArrayList<>(p1))) {
                return Player.P1;
            }

            long l1 = p1.pop();
            long l2 = p2.pop();
            if (p1.size() < l1 || p2.size() < l2) {
                if (l1 > l2) {
                    p1.add(l1);
                    p1.add(l2);
                } else {
                    p2.add(l2);
                    p2.add(l1);
                }
            } else {
                if (playGame(new ArrayDeque<>(new ArrayList<>(p1).subList(0, toIntExact(l1))), new ArrayDeque<>(new ArrayList<>(p2).subList(0, toIntExact(l2)))) == Player.P1) {
                    p1.add(l1);
                    p1.add(l2);
                } else {
                    p2.add(l2);
                    p2.add(l1);
                }
            }
        }
        return p1.size() > 0 ? Player.P1 : Player.P2;
    }

    enum Player {P1, P2}


    private static boolean isPlayer1Winner(Queue<Integer> player1, Queue<Integer> player2) {
        Set<List<Integer>> playedGamesByPlayer1 = new HashSet<>();
        Set<List<Integer>> playedGamesByPlayer2 = new HashSet<>();
        while (!player1.isEmpty() && !player2.isEmpty()) {
            if (!playedGamesByPlayer1.add(new ArrayList<>(player1)) && !playedGamesByPlayer2.add(new ArrayList<>(player2))) {
                return true;
            }
                Integer remove1 = player1.remove();
                Integer remove2 = player2.remove();


            if (player1.size() < remove1 || player2.size() < remove2) {
                if (remove1 > remove2) {
                    player1.add(remove1);
                    player1.add(remove2);
                } else {
                    player2.add(remove2);
                    player2.add(remove1);
                }
            } else {
                if (isPlayer1Winner(new LinkedList<>(new ArrayList<>(player1).subList(0, remove1)), new LinkedList<>(new ArrayList<>(player2).subList(0, remove2)))) {
                /*if (isPlayer1Winner(player1.stream()
                        .limit(player1.size()).collect(Collectors.toCollection(LinkedList::new)), player2.stream()
                        .limit(player2.size()).collect(Collectors.toCollection(LinkedList::new)))) {*/
                    player1.add(remove1);
                    player1.add(remove2);
                } else {
                    player2.add(remove2);
                    player2.add(remove1);
                }
            }


        }
        return !player1.isEmpty();
    }


}
