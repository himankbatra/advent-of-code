package com.adventofcode._2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class AdventOfCode2020_01 {

    public static void main(String[] args) {
        /* List<Integer> numbers = new ArrayList<>();
        numbers.add(1993);
        numbers.add(1715);
        numbers.add(1997);
        numbers.add(1666);
        numbers.add(1676);
        numbers.add(1830);
        numbers.add(1203);
        numbers.add(1800);
        numbers.add(1125);
        numbers.add(1191);
        numbers.add(1902);
        numbers.add(1972);
        numbers.add(1471);
        numbers.add(1137);
        numbers.add(2003);
        numbers.add(1250);
        numbers.add(1548);
        numbers.add(1070);
        numbers.add(1152);
        numbers.add(2004);
        numbers.add(1127);
        numbers.add(1111);
        numbers.add(1898);
        numbers.add(1848);
        numbers.add(1934);
        numbers.add(1236);
        numbers.add(1704);
        numbers.add(1950);
        numbers.add(1387);
        numbers.add(1713);
        numbers.add(1214);
        numbers.add(1266);
        numbers.add(1114);
        numbers.add(1089);
        numbers.add(1677);
        numbers.add(1207);
        numbers.add(1341);
        numbers.add(1689);
        numbers.add(1772);
        numbers.add(1901);
        numbers.add(1932);
        numbers.add(1645);
        numbers.add(1285);
        numbers.add(1884);
        numbers.add(883);
        numbers.add(1291);
        numbers.add(1543);
        numbers.add(1455);
        numbers.add(1213);
        numbers.add(1088);
        numbers.add(1784);
        numbers.add(1506);
        numbers.add(1879);
        numbers.add(1811);
        numbers.add(1880);
        numbers.add(994);
        numbers.add(1021);
        numbers.add(1585);
        numbers.add(1662);
        numbers.add(1683);
        numbers.add(1071);
        numbers.add(1643);
        numbers.add(1754);
        numbers.add(1389);
        numbers.add(1124);
        numbers.add(1820);
        numbers.add(1168);
        numbers.add(1875);
        numbers.add(1017);
        numbers.add(1180);
        numbers.add(1375);
        numbers.add(1359);
        numbers.add(1311);
        numbers.add(1357);
        numbers.add(1501);
        numbers.add(1719);
        numbers.add(1584);
        numbers.add(1609);
        numbers.add(1977);
        numbers.add(1786);
        numbers.add(1232);
        numbers.add(1263);
        numbers.add(1748);
        numbers.add(1664);
        numbers.add(1693);
        numbers.add(1766);
        numbers.add(1598);
        numbers.add(1053);
        numbers.add(1277);
        numbers.add(1466);
        numbers.add(1877);
        numbers.add(1844);
        numbers.add(1829);
        numbers.add(1165);
        numbers.add(1606);
        numbers.add(1298);
        numbers.add(1963);
        numbers.add(1873);
        numbers.add(1911);
        numbers.add(1729);
        numbers.add(1418);
        numbers.add(1372);
        numbers.add(1777);
        numbers.add(1371);
        numbers.add(1588);
        numbers.add(1329);
        numbers.add(1029);
        numbers.add(1931);
        numbers.add(1115);
        numbers.add(1810);
        numbers.add(1595);
        numbers.add(1237);
        numbers.add(1282);
        numbers.add(1838);
        numbers.add(1642);
        numbers.add(1937);
        numbers.add(1343);
        numbers.add(1578);
        numbers.add(1425);
        numbers.add(1814);
        numbers.add(1690);
        numbers.add(1129);
        numbers.add(1321);
        numbers.add(1174);
        numbers.add(1863);
        numbers.add(1405);
        numbers.add(1066);
        numbers.add(1220);
        numbers.add(1780);
        numbers.add(1410);
        numbers.add(1156);
        numbers.add(1991);
        numbers.add(1568);
        numbers.add(1368);
        numbers.add(99);
        numbers.add(1750);
        numbers.add(1280);
        numbers.add(1400);
        numbers.add(1601);
        numbers.add(1804);
        numbers.add(1363);
        numbers.add(1613);
        numbers.add(1252);
        numbers.add(1434);
        numbers.add(1094);
        numbers.add(1867);
        numbers.add(1542);
        numbers.add(1093);
        numbers.add(1926);
        numbers.add(1251);
        numbers.add(1348);
        numbers.add(689);
        numbers.add(1441);
        numbers.add(1913);
        numbers.add(1969);
        numbers.add(1409);
        numbers.add(1201);
        numbers.add(1459);
        numbers.add(1110);
        numbers.add(1452);
        numbers.add(1051);
        numbers.add(1860);
        numbers.add(1346);
        numbers.add(1537);
        numbers.add(1060);
        numbers.add(1182);
        numbers.add(1386);
        numbers.add(1141);
        numbers.add(1184);
        numbers.add(1989);
        numbers.add(1852);
        numbers.add(1097);
        numbers.add(1135);
        numbers.add(1078);
        numbers.add(1587);
        numbers.add(1984);
        numbers.add(1970);
        numbers.add(1259);
        numbers.add(1281);
        numbers.add(1092);
        numbers.add(1294);
        numbers.add(1233);
        numbers.add(1186);
        numbers.add(1555);
        numbers.add(1755);
        numbers.add(1886);
        numbers.add(1030);
        numbers.add(1706);
        numbers.add(1313);
        numbers.add(1481);
        numbers.add(1998);
        numbers.add(1181);
        numbers.add(1244);
        numbers.add(1269);
        numbers.add(1684);
        numbers.add(1798);
        numbers.add(1023);
        numbers.add(1960);
        numbers.add(1050);
        numbers.add(1293);*/

        String inputFile = "2020/input_01.txt";
        List<Integer> numbers;
        try {
            numbers = Files.lines(Paths.get("src/main/resources", inputFile))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        numbers.stream()
                .map(i -> 2020 - i)
                .filter(numbers::contains)
                .peek(System.out::println)
                .reduce((integer, integer2) -> integer * integer2)
                .ifPresent(a -> System.out.println("answer 1st part :: " + a));

        numbers.stream()
                .map(i -> 2020 - i)
                .filter(i ->
                        numbers.stream().map(i2 -> i - i2)
                                .anyMatch(o -> /*{
                                    boolean contains = */
                                        numbers.contains(o)/*;
                                    if (contains) {
                                        int i1 = 2020 - i;
                                        int i2 = i - o;
                                        System.out.println("i1 => " + i1 + " i2 => " + i2 + " i3 => " + o);
                                    }
                                    return contains;
                                }*/)
                )
                .map(i -> 2020 - i)
                .peek(System.out::println)
                .reduce((integer, integer2) -> integer * integer2)
                .ifPresent(a -> System.out.println("answer second part :: " + a));
    }

}
