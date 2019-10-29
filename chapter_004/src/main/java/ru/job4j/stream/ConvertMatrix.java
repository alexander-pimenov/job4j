package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConvertMatrix {

    /*метод для перобразования матрицы чисел в список чисел*/
    List<Integer> convert(Integer[][] matrix) {
        return Stream.of(matrix).flatMap(e -> Arrays.stream((e))).collect(Collectors.toList());
    }
}
