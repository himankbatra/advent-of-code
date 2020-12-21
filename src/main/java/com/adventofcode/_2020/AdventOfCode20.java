package com.adventofcode._2020;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdventOfCode20 {

    public static void main(String[] args) {


        List<Tile> tiles = null;

        Scanner reader = null;
        try {
            reader = new Scanner(new File("src/main/resources/2020/input20.txt"));
        } catch (Exception e) {
            throw new RuntimeException("File not found");
        }

        tiles = new ArrayList<>();
        List<String> input = new ArrayList<>();

        while (reader.hasNext()) {
            String line = reader.nextLine();
            if (line.isEmpty()) {
                // we are at a blank line in the input so we should convert the elements of
                // input into a Tile object and then clear the input array
                String s = input.get(0);
                int num = Integer.parseInt(s.substring(s.indexOf(" ") + 1, s.indexOf(":")));
                input.remove(0);
                char[][] grid = new char[input.size()][input.get(0).length()];
                for (int r = 0; r < grid.length; r++)
                    for (int c = 0; c < grid[0].length; c++)
                        grid[r][c] = input.get(r).charAt(c);
                // Tile object created with (id #, char[][])
                tiles.add(new Tile(num, grid));
                input.clear();
            } else {
                input.add(line);
            }
        }

        String s = input.get(0);
        int num = Integer.parseInt(s.substring(s.indexOf(" ") + 1, s.indexOf(":")));
        input.remove(0);
        char[][] grid = new char[input.size()][input.get(0).length()];
        for (int r = 0; r < grid.length; r++)
            for (int c = 0; c < grid[0].length; c++)
                grid[r][c] = input.get(r).charAt(c);
        // Tile object created with (id #, char[][])
        tiles.add(new Tile(num, grid));

        new AdventOfCode20().part1(tiles);
        new AdventOfCode20().part2(tiles);


    }


    public void part1(List<Tile> tiles) {
        // find four tiles whose edges only have two matches (these are the corners)
        // no need to solve the whole puzzle for part 1
        long prod = 1;
        for (int i = 0; i < tiles.size(); i++) {
            Tile t1 = tiles.get(i);
            int count = 0; // count how many sides of this tile match sides of other tiles (consider
            // rotations/flips)
            for (int j = 0; j < tiles.size(); j++) {

                if (i == j) // don't compare a tile to itself
                    continue;

                Tile t2 = tiles.get(j);

                if (t1.getE1().equals(t2.getE1()))
                    count++;
                else if (t1.getE1().equals(t2.getE2()))
                    count++;
                else if (t1.getE1().equals(t2.getE3()))
                    count++;
                else if (t1.getE1().equals(t2.getE4()))
                    count++;

                else if (t1.getE1().equals(rev(t2.getE1())))
                    count++;
                else if (t1.getE1().equals(rev(t2.getE2())))
                    count++;
                else if (t1.getE1().equals(rev(t2.getE3())))
                    count++;
                else if (t1.getE1().equals(rev(t2.getE4())))
                    count++;

                else if (t1.getE2().equals(t2.getE1()))
                    count++;
                else if (t1.getE2().equals(t2.getE2()))
                    count++;
                else if (t1.getE2().equals(t2.getE3()))
                    count++;
                else if (t1.getE2().equals(t2.getE4()))
                    count++;

                else if (t1.getE2().equals(rev(t2.getE1())))
                    count++;
                else if (t1.getE2().equals(rev(t2.getE2())))
                    count++;
                else if (t1.getE2().equals(rev(t2.getE3())))
                    count++;
                else if (t1.getE2().equals(rev(t2.getE4())))
                    count++;

                else if (t1.getE3().equals(t2.getE1()))
                    count++;
                else if (t1.getE3().equals(t2.getE2()))
                    count++;
                else if (t1.getE3().equals(t2.getE3()))
                    count++;
                else if (t1.getE3().equals(t2.getE4()))
                    count++;

                else if (t1.getE3().equals(rev(t2.getE1())))
                    count++;
                else if (t1.getE3().equals(rev(t2.getE2())))
                    count++;
                else if (t1.getE3().equals(rev(t2.getE3())))
                    count++;
                else if (t1.getE3().equals(rev(t2.getE4())))
                    count++;

                else if (t1.getE4().equals(t2.getE1()))
                    count++;
                else if (t1.getE4().equals(t2.getE2()))
                    count++;
                else if (t1.getE4().equals(t2.getE3()))
                    count++;
                else if (t1.getE4().equals(t2.getE4()))
                    count++;

                else if (t1.getE4().equals(rev(t2.getE1())))
                    count++;
                else if (t1.getE4().equals(rev(t2.getE2())))
                    count++;
                else if (t1.getE4().equals(rev(t2.getE3())))
                    count++;
                else if (t1.getE4().equals(rev(t2.getE4())))
                    count++;

            }
            if (count == 2) {// must be a corner piece
                prod *= t1.getTileNumber();
                t1.setIsCorner(true); // for part 2
            }

        }

        System.out.println("Product of 4 corner pieces: " + prod);

    }

    public void part2(List<Tile> tiles) {

        int size = (int) Math.sqrt(tiles.size()); // length and width of our square puzzle
        Tile[][] puzzle = new Tile[size][size]; // a 2D array of Tiles we will use to order our solved puzzle

        // place the top left corner piece
        int ti = 0;
        while (!tiles.get(ti).isCorner())
            ti++;

        puzzle[0][0] = tiles.get(ti);
        tiles.remove(ti); // removed used puzzle pieces from list

        // ensure the corner is oriented such that top and left sides do not match any
        // other edges
        int e1m = 0; // count how many other puzzle pieces edge 1 (top) matches
        int e2m = 0; // count how many other puzzle pieces edge 2 (right) matches
        int e3m = 0; // count how many other puzzle pieces edge 3 (bottom) matches
        int e4m = 0; // count how many other puzzle pieces edge 4 (left) matches
        for (int i = 0; i < tiles.size(); i++) {
            Tile tlc = puzzle[0][0]; // top left corner piece
            Tile other = tiles.get(i); // all other puzzle pieces
            if (tlc.getE1().equals(other.getE1()))
                e1m++;
            else if (tlc.getE1().equals(other.getE2()))
                e1m++;
            else if (tlc.getE1().equals(other.getE3()))
                e1m++;
            else if (tlc.getE1().equals(other.getE4()))
                e1m++;

            else if (tlc.getE1().equals(rev(other.getE1())))
                e2m++;
            else if (tlc.getE1().equals(rev(other.getE2())))
                e2m++;
            else if (tlc.getE1().equals(rev(other.getE3())))
                e2m++;
            else if (tlc.getE1().equals(rev(other.getE4())))
                e2m++;

            if (tlc.getE2().equals(other.getE1()))
                e2m++;
            else if (tlc.getE2().equals(other.getE2()))
                e2m++;
            else if (tlc.getE2().equals(other.getE3()))
                e2m++;
            else if (tlc.getE2().equals(other.getE4()))
                e2m++;

            else if (tlc.getE2().equals(rev(other.getE1())))
                e2m++;
            else if (tlc.getE2().equals(rev(other.getE2())))
                e2m++;
            else if (tlc.getE2().equals(rev(other.getE3())))
                e2m++;
            else if (tlc.getE2().equals(rev(other.getE4())))
                e2m++;

            if (tlc.getE3().equals(other.getE1()))
                e3m++;
            else if (tlc.getE3().equals(other.getE2()))
                e3m++;
            else if (tlc.getE3().equals(other.getE3()))
                e3m++;
            else if (tlc.getE3().equals(other.getE4()))
                e3m++;

            else if (tlc.getE3().equals(rev(other.getE1())))
                e3m++;
            else if (tlc.getE3().equals(rev(other.getE2())))
                e3m++;
            else if (tlc.getE3().equals(rev(other.getE3())))
                e3m++;
            else if (tlc.getE3().equals(rev(other.getE4())))
                e3m++;

            if (tlc.getE4().equals(other.getE1()))
                e4m++;
            else if (tlc.getE4().equals(other.getE2()))
                e4m++;
            else if (tlc.getE4().equals(other.getE3()))
                e4m++;
            else if (tlc.getE4().equals(other.getE4()))
                e4m++;

            else if (tlc.getE4().equals(rev(other.getE1())))
                e4m++;
            else if (tlc.getE4().equals(rev(other.getE2())))
                e4m++;
            else if (tlc.getE4().equals(rev(other.getE3())))
                e4m++;
            else if (tlc.getE4().equals(rev(other.getE4())))
                e4m++;
        }

        // rotate top left corner piece until the two sides that don't match are on the
        // outside
        if (e1m == 0 && e2m == 0) // top&right -> rotate CW 3 times
            puzzle[0][0].rotateClockwise(3);
        else if (e2m == 0 && e3m == 0) // right&bottom -> rotate CW 2 times
            puzzle[0][0].rotateClockwise(2);
        else if (e3m == 0 && e4m == 0) // bottom&left -> rotate CW 1 time
            puzzle[0][0].rotateClockwise(1);
        // if e1m == 0 && e4m == 0, it's oriented correctly already

        // Traverse puzzle array and find pieces that fit.
        // When a piece fits, be sure to flip/rotate it before placing in array.
        // If we are in row 0, we must match pieces with the puzzle piece to the left
        // (because nothing is above)
        // If we are in any other row, we should match pieces with the puzzle piece
        // above (because there is potentially nothing to our left)
        for (int r = 0; r < puzzle.length; r++) {
            for (int c = 0; c < puzzle[0].length; c++) {
                if (puzzle[r][c] == null) { // so we skip top left corner
                    if (r == 0) {
                        // match with right side of tile to your left
                        String edgeToMatch = puzzle[r][c - 1].getE2();
                        int i = 0;
                        boolean pieceFound = false;
                        while (!pieceFound) {
                            Tile t = tiles.get(i);
                            if (t.getE1().equals(edgeToMatch)) {
                                t.flipHorizontal();
                                t.rotateClockwise(3);
                                puzzle[r][c] = t;
                                tiles.remove(i);
                                pieceFound = true;
                            } else if (t.getE2().equals(edgeToMatch)) {
                                t.flipHorizontal();
                                puzzle[r][c] = t;
                                tiles.remove(i);
                                pieceFound = true;
                            } else if (t.getE3().equals(edgeToMatch)) {
                                t.rotateClockwise(1);
                                puzzle[r][c] = t;
                                tiles.remove(i);
                                pieceFound = true;
                            } else if (t.getE4().equals(edgeToMatch)) {
                                puzzle[r][c] = t;
                                tiles.remove(i);
                                pieceFound = true;
                            } else if (rev(t.getE1()).equals(edgeToMatch)) {
                                t.rotateClockwise(3);
                                puzzle[r][c] = t;
                                tiles.remove(i);
                                pieceFound = true;
                            } else if (rev(t.getE2()).equals(edgeToMatch)) {
                                t.rotateClockwise(2);
                                puzzle[r][c] = t;
                                tiles.remove(i);
                                pieceFound = true;
                            } else if (rev(t.getE3()).equals(edgeToMatch)) {
                                t.flipHorizontal();
                                t.rotateClockwise(1);
                                puzzle[r][c] = t;
                                tiles.remove(i);
                                pieceFound = true;
                            } else if (rev(t.getE4()).equals(edgeToMatch)) {
                                t.flipHorizontal();
                                t.rotateClockwise(2);
                                puzzle[r][c] = t;
                                tiles.remove(i);
                                pieceFound = true;
                            }
                            i++;
                        }
                    } else {
                        // match with bottom of tile above you
                        String edgeToMatch = puzzle[r - 1][c].getE3();
                        int i = 0;
                        boolean pieceFound = false;
                        while (!pieceFound) {
                            Tile t = tiles.get(i);
                            if (t.getE1().equals(edgeToMatch)) {
                                puzzle[r][c] = t;
                                tiles.remove(i);
                                pieceFound = true;
                            } else if (t.getE2().equals(edgeToMatch)) {
                                t.rotateClockwise(3);
                                puzzle[r][c] = t;
                                tiles.remove(i);
                                pieceFound = true;
                            } else if (t.getE3().equals(edgeToMatch)) {
                                t.flipHorizontal();
                                t.rotateClockwise(2);
                                puzzle[r][c] = t;
                                tiles.remove(i);
                                pieceFound = true;
                            } else if (t.getE4().equals(edgeToMatch)) {
                                t.rotateClockwise(1);
                                t.flipHorizontal();
                                puzzle[r][c] = t;
                                tiles.remove(i);
                                pieceFound = true;
                            } else if (rev(t.getE1()).equals(edgeToMatch)) {
                                t.flipHorizontal();
                                puzzle[r][c] = t;
                                tiles.remove(i);
                                pieceFound = true;
                            } else if (rev(t.getE2()).equals(edgeToMatch)) {
                                t.flipHorizontal();
                                t.rotateClockwise(1);
                                puzzle[r][c] = t;
                                tiles.remove(i);
                                pieceFound = true;
                            } else if (rev(t.getE3()).equals(edgeToMatch)) {
                                t.rotateClockwise(2);
                                puzzle[r][c] = t;
                                tiles.remove(i);
                                pieceFound = true;
                            } else if (rev(t.getE4()).equals(edgeToMatch)) {
                                t.rotateClockwise(1);
                                puzzle[r][c] = t;
                                tiles.remove(i);
                                pieceFound = true;
                            }
                            i++;
                        }
                    }
                }
            }
        }

        // Traverse your 2D array of solved puzzle pieces and use each piece to
        // create a 2D char array containing all chars from puzzle pieces without edges
        char[][] image = new char[puzzle.length * 8][puzzle.length * 8];
        int ri = 0; // row of image
        int ci = 0; // col of image
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[0].length; j++) {
                Tile t = puzzle[i][j]; // current tile we are copying into image
                char[][] g = t.getGrid();
                // using the coordinates of our puzzle piece, calculate where to begin placing
                // chars in image array
                ri = i * 8;
                ci = j * 8;
                for (int r = 1; r <= g.length - 2; r++) {
                    for (int c = 1; c <= g[0].length - 2; c++) {
                        image[ri][ci] = g[r][c];
                        ci++;
                    }
                    ri++; // go down one row
                    ci = j * 8; // go back to starting column
                }
            }
        }

        // I'm making a Tile out of the puzzle so I can use my rotate/flip methods to
        // help find monsters. I'm afraid monsters may be oriented in all 8 possible ways.
        // findMonsters method will traverse grid in search of seaMonsters and replace
        // '#' with 'O' when found.
        Tile completedPuzzle = new Tile(0, image);
        completedPuzzle.findMonsters(); // check normal oriented puzzle for monsters
        completedPuzzle.rotateClockwise(1); // rotated once CW
        completedPuzzle.findMonsters(); // re-check
        completedPuzzle.rotateClockwise(1); // upside down puzzle
        completedPuzzle.findMonsters(); // re-check
        completedPuzzle.rotateClockwise(1); // rotated three times CW
        completedPuzzle.findMonsters(); // re-check
        completedPuzzle.rotateClockwise(1); // back to normal
        completedPuzzle.flipHorizontal(); // flip it
        completedPuzzle.findMonsters(); // check reversed puzzle
        completedPuzzle.rotateClockwise(1); // rotate once CW
        completedPuzzle.findMonsters(); // re-check
        completedPuzzle.rotateClockwise(1); // upside down and reversed
        completedPuzzle.findMonsters(); // re-check
        completedPuzzle.rotateClockwise(1); // rotated three times CW
        completedPuzzle.findMonsters(); // re-check

        // count how many chars in completedPuzzle are still '#'
        int count = 0;
        char[][] g = completedPuzzle.getGrid();
        for (int i = 0; i < g.length; i++)
            for (int j = 0; j < g[0].length; j++)
                if (g[i][j] == '#')
                    count++;

        System.out.println("Water roughness " + count);

    }

    private static String rev(String s) {
        return new StringBuilder(s).reverse().toString();
    }


}


class Tile {

    /**
     * A Tile is used to represent a puzzle piece
     *
     * @param grid       The 2D image on the puzzle piece
     * @param e1         A string representation of the top edge of the grid
     * (left-right)
     * @param e2         A string representation of the right edge of the grid
     * (top-down)
     * @param e3         A string representation of the bottom edge of the grid
     * (left-right)
     * @param e4         A string representation of the left edge of the grid
     * (top-down)
     * @param tileNumber The id # assigned to this puzzle piece
     * @param isCorner   True if this piece has been designated a corner piece
     */

    private char[][] grid;
    private String e1, e2, e3, e4;
    private int tileNumber;
    private boolean isCorner;

    public Tile(int n, char[][] g) {
        grid = g;
        tileNumber = n;
        isCorner = false;
        e1 = e2 = e3 = e4 = "";
        for (int c = 0; c < g[0].length; c++)
            e1 += g[0][c];
        for (int r = 0; r < g.length; r++)
            e2 += g[r][g[0].length - 1];
        for (int c = 0; c < g[0].length; c++)
            e3 += g[g.length - 1][c];
        for (int r = 0; r < g.length; r++)
            e4 += g[r][0];
    }

    public void setIsCorner(boolean c) {
        isCorner = c;
    }

    public boolean isCorner() {
        return isCorner;
    }

    public String toString() {
        String str = "Tile " + tileNumber + ":\n";
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                str += grid[r][c];
            }
            str += "\n";
        }
        return str;
    }

    public char[][] getGrid() {
        return grid;
    }

    public String getE1() {
        return e1;
    }

    public String getE2() {
        return e2;
    }

    public String getE3() {
        return e3;
    }

    public String getE4() {
        return e4;
    }

    public int getTileNumber() {
        return tileNumber;
    }

    /**
     * Flips the grid horizontally and reassigns e1, e2, e3 and e4 accordingly
     */
    public void flipHorizontal() {
        char[][] copy = new char[grid.length][grid[0].length];
        for (int r = 0; r < copy.length; r++) {
            for (int c = 0; c < copy[0].length; c++) {
                copy[r][c] = grid[r][grid[0].length - 1 - c];
            }
        }

        String temp = e4;
        e4 = e2;
        e2 = temp;
        e1 = rev(e1);
        e3 = rev(e3);

        grid = copy;
    }

    private String rev(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    /**
     * Rotates the grid clockwise a given number of times
     *
     * @param i The number of times to rotate
     */
    public void rotateClockwise(int i) {
        for (int j = 1; j <= i; j++) {
            char[][] copy = new char[grid.length][grid[0].length];
            for (int r = 0; r < copy.length; r++) {
                for (int c = 0; c < copy[0].length; c++) {
                    copy[r][c] = grid[grid.length - 1 - c][r];
                }
            }

            String temp = e1;
            e1 = rev(e4);
            e4 = e3;
            e3 = rev(e2);
            e2 = temp;

            grid = copy;
        }

    }

    /**
     * Traverses the entire grid in search of sea monsters by looking at 15 specific
     * points. When a sea monster is found, the coordinates of the monster are
     * changed from # -> O.
     */
    public void findMonsters() {
//		time to find sea monsters...
//		.#.#...#.###...#.##.O#..
//		#.O.##.OO#.#.OO.##.OOO##
//		..#O.#O#.O##O..O.#O##.##
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (c + 19 < grid[0].length && r + 2 < grid.length)
                    if (grid[r][c + 18] == '#' && grid[r + 1][c] == '#' && grid[r + 1][c + 5] == '#'
                            && grid[r + 1][c + 6] == '#' && grid[r + 1][c + 11] == '#' && grid[r + 1][c + 12] == '#'
                            && grid[r + 1][c + 17] == '#' && grid[r + 1][c + 18] == '#' && grid[r + 1][c + 19] == '#'
                            && grid[r + 2][c + 1] == '#' && grid[r + 2][c + 4] == '#' && grid[r + 2][c + 7] == '#'
                            && grid[r + 2][c + 10] == '#' && grid[r + 2][c + 13] == '#' && grid[r + 2][c + 16] == '#') {

                        grid[r][c + 18] = 'O';
                        grid[r + 1][c] = 'O';
                        grid[r + 1][c + 5] = 'O';
                        grid[r + 1][c + 6] = 'O';
                        grid[r + 1][c + 11] = 'O';
                        grid[r + 1][c + 12] = 'O';
                        grid[r + 1][c + 17] = 'O';
                        grid[r + 1][c + 18] = 'O';
                        grid[r + 1][c + 19] = 'O';
                        grid[r + 2][c + 1] = 'O';
                        grid[r + 2][c + 4] = 'O';
                        grid[r + 2][c + 7] = 'O';
                        grid[r + 2][c + 10] = 'O';
                        grid[r + 2][c + 13] = 'O';
                        grid[r + 2][c + 16] = 'O';

                    }
            }
        }

    }


}
