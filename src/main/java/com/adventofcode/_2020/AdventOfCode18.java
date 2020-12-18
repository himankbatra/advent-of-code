package com.adventofcode._2020;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class AdventOfCode18 {


    public static void main(String[] args) {
        String inputFile = "2020/input18.txt";
        List<String> input;
        try {
            input = Files.readAllLines(Paths.get("src/main/resources", inputFile));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        long calc = calc(input);
        System.out.println(calc);
        long l = calc1(input);
        System.out.println(l);
        solve(input);
        long l1 = calc3(input);
        System.out.println("l1 = " + l1);

    }

    private static long calc(List<String> strs) {
        long sum = 0L;
        for (String str : strs) {
            while (str.split(" ").length > 1) {
                String[] s = str.split(" ");
                str = operate(s).trim();
            }
            sum += Long.parseLong(str);
        }
        return sum;
    }


    static String operate(String[] s) {
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < s.length; i++) {
            String str = s[i];
            if (str.endsWith(")")) {
                i1 = i - 2;
                break;
            }
            if (str.startsWith("(")) {
                i2 = i;
            }
        }
        int gap = 0;
        if (i2 != i1) {
            gap = i1 - i2;
            i1 = i2;
        }

        String o = s[i1] + " " + s[i1 + 1] + " " + s[i1 + 2];
        if (i1 == 0) {
            return evaluate(o) + " " + Arrays.stream(s).skip(3).collect(Collectors.joining(" "));
        } else {
            StringBuilder updated = new StringBuilder();
            for (int h = 0; h < s.length; h++) {
                if (h == i1) {

                    if (s[i1].startsWith("(") || s[i1 + 2].endsWith(")")) {
                        if (gap > 0) {
                            if (s[i1].startsWith("(") && !s[i1].startsWith("((")) {
                                updated.append(" ").append("(")
                                        .append(evaluate(o));
                            }
                            if (s[i1].startsWith("((")) {
                                updated.append(" ").append("((")
                                        .append(evaluate(o));
                            }
                            if (s[i1 + 2].endsWith(")") && !s[i1].endsWith("))")) {
                                updated.append(" ")
                                        .append(evaluate(o)).append(")");
                            }
                            if (s[i1 + 2].endsWith("))")) {
                                updated.append(" ")
                                        .append(evaluate(o)).append("))");
                            }
                        } else {
                            if (s[i1 + 2].endsWith("))")) {
                                updated.append(" ")
                                        .append(evaluate(o)).append(")");
                            } else if (s[i1].startsWith("((")) {
                                updated.append(" (")
                                        .append(evaluate(o));
                            } else {
                                updated.append(" ")
                                        .append(evaluate(o));
                            }


                        }
                    }
                } else if (h != i1 + 1 && h != i1 + 2) {
                    updated.append(" ").append(s[h]);
                }
            }
            return updated.toString();
        }
    }


    static long evaluate(String str) {
        int i = str.indexOf("(");
        int i1 = str.indexOf(")");
        String evaluate;
        if (i != -1 && i1 != -1) {
            if (str.charAt(i + 1) == '(') {
                i = i + 1;
            }
            evaluate = str.substring(i + 1, i1);
        } else if (i != -1) {
            if (str.charAt(i + 1) == '(') {
                i = i + 1;
            }
            evaluate = str.substring(i + 1);
        } else if (i1 != -1) {
            evaluate = str.substring(0, i1);
        } else {
            evaluate = str;
        }
        return applyMaths(evaluate);
    }

    static long applyMaths(String str) {
        String[] s = str.split(" ");
        long o1 = Long.parseLong(s[0]);
        long o2 = Long.parseLong(s[2]);
        switch (s[1]) {
            case "*":
                return o1 * o2;
            case "+":
                return o1 + o2;

        }
        throw new RuntimeException("not wanted");
    }


    private static long calc2(List<String> strs) {
        return 0L;
    }


    static int i;

    private static long calc1(List<String> strs) {
        long sum = 0;
        for (String str : strs) {
            i = -1;
            long tal = calc1(str);
            sum += tal;
        }
        return sum;
    }

    private static long calc1(String str) {
        long lastTal = -1;
        char lastOp = 0;
        i++;
        for (; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isDigit(c)) {
                long tal = c - '0';
                if (lastTal == -1) {
                    lastTal = tal;
                } else {
                    if (lastOp == '+') {
                        lastTal += tal;
                    } else if (lastOp == '*') {
                        lastTal *= tal;
                    }
                }
            } else if (c == '+' || c == '*') {
                lastOp = c;
            } else if (c == '(') {
                long tal = calc1(str);
                if (lastTal == -1) {
                    lastTal = tal;
                } else {
                    if (lastOp == '+') {
                        lastTal += tal;
                    } else if (lastOp == '*') {
                        lastTal *= tal;
                    }
                }
            } else if (c == ')') {
                return lastTal;
            }
        }
        return lastTal;
    }


    static long doMathL2H(String expression) {

        String[] s = expression.split(" ");
        if (s.length > 2) {
            return doMathL2H(
                    applyMaths(s[0] + " " + s[1] + " " + s[2])
                            + " " + Arrays.stream(s).skip(3).collect(Collectors.joining(" ")));

        } else {
            return Long.parseLong(s[0]);
        }
    }

    static long doMathPlusFirst(String expression) {

        String[] s = expression.split(" ");
        if (s.length > 2) {
            int i1 = expression.indexOf("+");
            if (i1 == -1) {
                return doMathL2H(expression);
            }
            int i;
            for (i = 0; i < s.length; i++) {
                if (s[i].equals("+")) {
                    break;
                }
            }
            String updated = "";
            for (int j = 0; j < s.length; j++) {

                if (j == i || j == i + 1) {
                    continue;
                }
                if (j == i - 1) {
                    updated = updated + " " + applyMaths(s[i - 1] + " " + s[i] + " " + s[i + 1]);
                } else {
                    updated = updated + " " + s[j];
                }
            }
            return doMathPlusFirst(updated.trim());

        } else {
            return Long.parseLong(s[0]);
        }
    }


    static void solve(List<String> strs) {
        long sum = 0L;
        long sum2 = 0L;
        for (String str : strs) {
            sum += operate(str);
            sum2 += operate2(str);
        }
        System.out.println("sum = " + sum);
        System.out.println("sum2 = " + sum2);
    }

    private static long operate(String str) {
        long sum = 0;
        if (str.contains("(")) {
            int startIndex = str.lastIndexOf("(");
            int endIndex = str.indexOf(")", startIndex);
            String substring = str.substring(
                    startIndex + 1, endIndex);
            String str1 = str.substring(0, startIndex)
                    + doMathL2H(substring) + str.substring(endIndex + 1);
            sum += operate(str1);
        } else {
            sum += doMathL2H(str);
        }
        return sum;
    }

    private static long operate2(String str) {
        long sum = 0;
        if (str.contains("(")) {
            int startIndex = str.lastIndexOf("(");
            int endIndex = str.indexOf(")", startIndex);
            String substring = str.substring(
                    startIndex + 1, endIndex);
            String str1 = str.substring(0, startIndex)
                    + doMathPlusFirst(substring) + str.substring(endIndex + 1);
            sum += operate2(str1);
        } else {
            sum += doMathPlusFirst(str);
        }
        return sum;
    }

    static int j;

    private static long calc3(List<String> strs) {
        long sum = 0;
        for (String str : strs) {
            j = -1;
            long tal = calc3(str);
            sum += tal;
        }
        return sum;
    }

    private static long calc3(String str) {
        List<Long> ggr = new ArrayList<>();
        long lastTal = -1;
        char lastOp = 0;
        j++;
        for (; j < str.length(); j++) {
            char c = str.charAt(j);
            if (Character.isDigit(c)) {
                long tal = c - '0';
                if (lastTal == -1) {
                    lastTal = tal;
                } else {
                    if (lastOp == '+') {
                        lastTal += tal;
                    } else if (lastOp == '*') {
                        ggr.add(lastTal);
                        lastTal = tal;
                    }
                }
            } else if (c == '+' || c == '*') {
                lastOp = c;
            } else if (c == '(') {
                long tal = calc3(str);
                if (lastTal == -1) {
                    lastTal = tal;
                } else {
                    if (lastOp == '+') {
                        lastTal += tal;
                    } else if (lastOp == '*') {
                        ggr.add(lastTal);
                        lastTal = tal;
                    }
                }
            } else if (c == ')') {
                for (Long term : ggr) {
                    lastTal *= term;
                }
                return lastTal;
            }
        }
        for (Long term : ggr) {
            lastTal *= term;
        }
        return lastTal;
    }

}
