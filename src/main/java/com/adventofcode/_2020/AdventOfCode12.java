package com.adventofcode._2020;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class AdventOfCode12 {


    public static void main(String[] args) {
        String inputFile = "2020/input12.txt";
        List<String> input;
        try {
            input = Files.readAllLines(Paths.get("src/main/resources", inputFile));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        int posx = 0;
        int posy = 0;
        int angle = 0;
        for (String str : input) {
            String dir = str.substring(0, 1);
            int mov = Integer.parseInt(str.substring(1));
            switch (dir) {
                case "N":
                    posy += mov;
                    break;
                case "S":
                    posy -= mov;
                    break;
                case "E":
                    posx += mov;
                    break;
                case "W":
                    posx -= mov;
                    break;
                case "L":
                    angle += mov;
                    angle %= 360;
                    break;
                case "R":
                    angle += 360 - mov;
                    angle %= 360;
                    break;
                case "F":
                    switch (angle) {
                        case 0:
                            posx += mov;
                            break;
                        case 90:
                            posy += mov;
                            break;
                        case 180:
                            posx -= mov;
                            break;
                        case 270:
                            posy -= mov;
                            break;
                    }
            }

        }

        System.out.println("result 1 =>" + (Math.abs(posx) + Math.abs(posy)));

        int result2 = part2(input);
        System.out.println("result 2 =>" + result2);

    }


    private static int part2(List<String> input) {
        int posx = 0;
        int posy = 0;

        int wayx = 10;
        int wayy = 1;

        for (String str : input) {
            String dir = str.substring(0, 1);
            int mov = Integer.parseInt(str.substring(1));
            switch (dir) {
                case "N":
                    wayy += mov;
                    break;
                case "S":
                    wayy -= mov;
                    break;
                case "E":
                    wayx += mov;
                    break;
                case "W":
                    wayx -= mov;
                    break;
                case "L":
                    switch (mov) {
                        case 90: {
                            int temp = wayy;
                            wayy = wayx;
                            wayx = -temp;
                            break;
                        }
                        case 180: {
                            wayy *= -1;
                            wayx *= -1;
                            break;
                        }
                        case 270: {
                            int temp = wayy;
                            wayy = -wayx;
                            wayx = temp;
                            break;
                        }
                    }
                    break;
                case "R":
                    switch (mov) {
                        case 270: {
                            int temp = wayy;
                            wayy = wayx;
                            wayx = -temp;
                            break;
                        }
                        case 180: {
                            wayy *= -1;
                            wayx *= -1;
                            break;
                        }
                        case 90: {
                            int temp = wayy;
                            wayy = -wayx;
                            wayx = temp;
                            break;
                        }
                    }
                    break;
                case "F":
                    posx += mov * wayx;
                    posy += mov * wayy;
                    break;
            }

        }

        return Math.abs(posx) + Math.abs(posy);
    }

}
