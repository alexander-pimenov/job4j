package ru.job4j.stream;

import java.util.List;


/**
 * В классе представленны методы для подсчета суммы только четных чисел,
 * которые выбираем из списка, далее возводим их в квадрат и далее
 * находим сумму квадратов.
 */
public class Count {

    public int count1(List<Integer> data) {
        return data.stream()
                .filter(x -> x % 2 == 0)
                .map(x -> x * x)
                .reduce(Integer::sum)
                .orElse(-1);
    }

    public int count2(List<Integer> data) {
        return data.stream()
                .filter(x -> x % 2 == 0)
                .mapToInt(x -> x * x)
                .sum();
    }

    public int count3(List<Integer> data) {
        return data.stream()
                .mapToInt(p -> p % 2 == 0 ? p * p : 0)
                .sum();
    }

//    public static void main(String[] args) {
//        List<Integer> data = List.of(1, 2, 3, 4, 5);
//
//        System.out.println(count1(data));
//        System.out.println(count2(data));
//        System.out.println(count3(data));
//
//    }
}
