package com.adventofcode._2020;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class AdventOfCode11 {


    public static void main(String[] args) {
        String inputFile = "2020/input11.txt";
        List<String> input;
        try {
            input = Files.readAllLines(Paths.get("src/main/resources", inputFile));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        char[][] seats = new char[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            seats[i] = input.get(i).toCharArray();
        }

        boolean changed;
        do {
            changed = false;
            char[][] updatedSeats = new char[input.size()][seats[0].length];
            for (int i = 0; i < input.size(); i++) {
                for (int j = 0; j < seats[i].length; j++) {
                    updatedSeats[i][j] = seats[i][j];
                    int occupied = 0;
                    for (int i1 = -1; i1 <= 1; i1++) {
                        for (int j1 = -1; j1 <= 1; j1++) {
                            int i2 = i + i1;
                            int j2 = j + j1;
                            if (0 <= i2 && i2 < input.size() && 0 <= j2 && j2 < seats[i].length && !(i1 == 0 && j1 == 0)) {
                                if (seats[i2][j2] == '#') {
                                    occupied++;
                                }
                            }
                        }
                    }
                    if (seats[i][j] == 'L' && occupied == 0) {
                        updatedSeats[i][j] = '#';
                        changed = true;
                    } else if (seats[i][j] == '#' && occupied >= 4) {
                        updatedSeats[i][j] = 'L';
                        changed = true;
                    }
                }
            }
            seats = updatedSeats;
        }
        while (changed);
        int countOccupied = 0;

        for (char[] chars : seats) {
            for (char aChar : chars) {
                if (aChar == '#') {
                    countOccupied++;
                }
            }
        }

        System.out.println(" result 1 => " + countOccupied);


        char[][] seats2 = new char[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            seats2[i] = input.get(i).toCharArray();
        }

        boolean changed2;
        do {
            changed2 = false;
            char[][] updatedSeats2 = new char[input.size()][seats2[0].length];
            for (int i = 0; i < input.size(); i++) {
                for (int j = 0; j < seats2[i].length; j++) {
                    updatedSeats2[i][j] = seats2[i][j];
                    int occupied2 = 0;
                    for (int i1 = -1; i1 <= 1; i1++) {
                        for (int j1 = -1; j1 <= 1; j1++) {
                            if (i1 == 0 && j1 == 0) {
                                continue;
                            }
                            int i2 = i + i1;
                            int j2 = j + j1;
                            while (0 <= i2 && i2 < input.size() && 0 <= j2 && j2 < seats2[i].length && seats2[i2][j2] == '.') {
                                i2 = i2 + i1;
                                j2 = j2 + j1;
                            }
                            if (0 <= i2 && i2 < input.size() && 0 <= j2 && j2 < seats2[i].length) {
                                if (seats2[i2][j2] == '#') {
                                    occupied2++;
                                }
                            }
                        }
                    }
                    if (seats2[i][j] == 'L' && occupied2 == 0) {
                        updatedSeats2[i][j] = '#';
                        changed2 = true;
                    } else if (seats2[i][j] == '#' && occupied2 >= 5) {
                        updatedSeats2[i][j] = 'L';
                        changed2 = true;
                    }
                }
            }
            seats2 = updatedSeats2;
        }
        while (changed2);
        int countOccupied2 = 0;

        for (char[] chars : seats2) {
            for (char aChar : chars) {
                if (aChar == '#') {
                    countOccupied2++;
                }
            }
        }
        System.out.println(" result 2 => " + countOccupied2);

    }


}
