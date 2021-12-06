package com.adventofcode._2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class AdventOfCode04 {

    public static void main(String[] args) {
        String inputFile = "2021/input04.txt";
      String string;
        try {
            string = Files.readString(Paths.get("src/main/resources", inputFile));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }



        String[] split = string.split("\n\n");
        int[] nums = Arrays.stream(split[0].split(",")).mapToInt(Integer::parseInt).toArray();
        List<int[][]> cards = Arrays.stream(split, 1, split.length)
                .map(s -> Arrays.stream(s.split("\n"))
                        .map(r -> Arrays.stream(r.split(" "))
                                .filter(i->!i.isBlank()).mapToInt(Integer::parseInt)
                                .toArray()).toArray(int[][]::new)).collect(Collectors.toCollection(ArrayList::new));
        out:  for(int num : nums){
           for(int[][] card : cards){
                if(markCard(card, num) && checkCard(card)){
                    System.out.println("ans 1 = " + result(card, num));
                    break out;
                }
            }
        }


     out:   for(int num : nums){
            for(int i = 0; i<cards.size(); i++){
                int[][] card = cards.get(i);
                if(markCard(card, num) && checkCard(card)){
                    if(cards.size() == 1){
                        System.out.println("ans 2 = "+ result(card, num));
                        break out;
                    } else {
                        cards.remove(i);
                        i--;
                    }
                }
            }
        }


    }

    public static boolean replace(int[][] grid, int orig, int replacement){
        boolean changed = false;
        for(int i = 0; i< grid.length; i++){
            for(int j = 0; j< grid[i].length; j++){
                if(grid[i][j] == orig){
                    grid[i][j] = replacement;
                    changed = true;
                }
            }
        }
        return changed;
    }


    private static int result(int[][] card, int num) {
        return Arrays.stream(card).flatMapToInt(Arrays::stream)
                .filter(c->c!=-1).sum() * num;
    }

    private  static boolean markCard(int[][] card, int num){
        return replace(card,num, -1);
    }

    private  static boolean checkCard(int[][] grid){
        for(int[] nums : grid){
            if(Arrays.stream(nums).allMatch(n -> n==-1)){
                return true;
            }
        }
        out: for(int i = 0; i< grid[0].length; i++){
            for(int j = 0; j< grid[i].length; j++){
                if(grid[j][i] != -1){
                    continue out;
                }
            }
            return true;
        }
        return false;
    }
}
