package ru.job4j.lambda;

/*
 * Простой пример функциональных интерфейсов и
 * лямбда-выражений. Введение в эту тему.
 */

import java.util.function.BiFunction;
import java.util.function.Consumer;

public class Calculator {

    public void multiple(int start, int finish, int value,
                         BiFunction<Integer, Integer, Double> op,
                         Consumer<Double> media) {
        for (int index = start; index != finish; index++) {
            media.accept(op.apply(value, index));
        }
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.multiple(0, 10, 2, (value, index) -> {
                    double result = value * index;
                    System.out.printf("Multiple %s * %s = %s %n",
                            value, index, result);
                    return result;
                },

                result -> System.out.println(result)
        );

        System.out.println("Рассмотрим работу ссылки на метод:");
        calc.multiple(0, 10, 2, MathUtil::add,
                result -> System.out.println(result));
    }
}
