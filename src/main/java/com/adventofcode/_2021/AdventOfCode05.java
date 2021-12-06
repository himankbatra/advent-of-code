package com.adventofcode._2021;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AdventOfCode05 {

    public static void main(String[] args) {
        String inputFile = "2021/input05.txt";
        List<String> strings;
        try {
            strings = Files.lines(Paths.get("src/main/resources", inputFile))
                    .collect(Collectors.toList());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        var in = strings.stream()
                .map(e -> new Coords(Integer.parseInt(e.split("->")[0].trim().split(",")[0])
               ,Integer.parseInt(e.split("->")[0].trim().split(",")[1]),Integer.parseInt(e.split("->")[1].trim().split(",")[0]
                ),Integer.parseInt(e.split("->")[1].trim().split(",")[1]))).toList();
        Set<Point> all = new HashSet<>();
        Set<Point> vis = new HashSet<>();
        for(Coords c : in) {
            if(c.x1 == c.x2){
                for(int y = Math.min(c.y1, c.y2); y<=Math.max(c.y1, c.y2); y++){
                    var l = new Point(Math.toIntExact(c.x1), Math.toIntExact(y));
                    if(!all.add(l)){
                        vis.add(l);
                    }
                }
            } else if(c.y1 == c.y2){
                for(int x = Math.min(c.x1, c.x2); x<=Math.max(c.x1, c.x2); x++){
                    var l = new Point(Math.toIntExact(x), Math.toIntExact(c.y1));
                    if(!all.add(l)){
                        vis.add(l);
                    }
                }
            }
        }
        System.out.println("ans 1 = "+ vis.size());


     all = new HashSet<>();
       vis = new HashSet<>();
        for(Coords c : in) {
            if(c.x1 == c.x2){
                for(long y = Math.min(c.y1, c.y2); y<=Math.max(c.y1, c.y2); y++){
                    var l = new Point(Math.toIntExact(c.x1), Math.toIntExact(y));
                    if(!all.add(l)){
                        vis.add(l);
                    }
                }
            } else if(c.y1 == c.y2){
                for(long x = Math.min(c.x1, c.x2); x<=Math.max(c.x1, c.x2); x++){
                    var l = new Point(Math.toIntExact(x), Math.toIntExact(c.y1));
                    if(!all.add(l)){
                        vis.add(l);
                    }
                }
            } else if((c.x1 > c.x2 && c.y1 > c.y2) || (c.x1 < c.x2 && c.y1 < c.y2)){
                for(long x = 0; x<=Math.max(c.x1, c.x2)-Math.min(c.x1, c.x2); x++){
                    var l = new Point(Math.toIntExact(Math.min(c.x1, c.x2)+x), Math.toIntExact(Math.min(c.y1, c.y2)+x));
                    if(!all.add(l)){
                        vis.add(l);
                    }
                }
            }else if(c.x1 < c.x2){
                for(long x = 0; x<= c.x2 - c.x1; x++){
                    var l = new Point(Math.toIntExact(c.x1+x), Math.toIntExact(c.y1-x));
                    if(!all.add(l)){
                        vis.add(l);
                    }
                }
            }else {
                for(long x = 0; x<= c.x1 - c.x2; x++){
                    var l = new Point(Math.toIntExact(c.x1-x), Math.toIntExact(c.y1+x));
                    if(!all.add(l)){
                        vis.add(l);
                    }
                }
            }
        }
        System.out.println("ans 2 = "+ vis.size());


    }



    public record Coords(int x1, int y1, int x2, int y2) {}

}
