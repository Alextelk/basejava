package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 3, 2, 3};
        System.out.println(minValue(array));

        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println(oddOrEven(list));
    }

    public static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (a, b) -> 10 * a + b);
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        int result = integers.stream()
                .mapToInt(Integer::intValue)
                .sum() % 2;
        System.out.println(result);
        return integers.stream()
                .filter(x -> x % 2 != result)
                .collect(Collectors.toList());
    }
}
