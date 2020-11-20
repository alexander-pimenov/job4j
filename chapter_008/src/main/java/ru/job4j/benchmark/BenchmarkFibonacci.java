package ru.job4j.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Java Microbenchmark Harness
 * Jmh - это средство Java для создания, запуска и анализа
 * тестов нано / микро / макросов, написанных на Java и других
 * языках, ориентированных на JVM.
 * Этот более точный, но и более сложный в исполнении. Нам
 * потребуются дополнительные библиотеки maven для добавления
 * в наш проект.
 * Вы можете настроить JMH разными способами. Здесь мы
 * используем простую конфигурацию для измерения среднего
 * времени. Количество итераций для прогрева и измерения
 * установлено на 2 и 1. По умолчанию они больше - 10 и 20.
 * -= @Benchmark
 * -= @BenchmarkMode(Mode.AverageTime)
 * -= @Fork(value = 1)
 * -= @Warmup(iterations = 2) //количество итерацияй для прогрева
 * -= @Measurement(iterations = 1) //количество измерений
 */
public class BenchmarkFibonacci {

    public static void main(String[] args) throws RunnerException {
        /*Построитель JMH, который имитирует аргументы командной строки,
        которые могут быть переданы автономному исполняемому файлу JAR*/
        Options opt = new OptionsBuilder()
                .include(BenchmarkFibonacci.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    /*Конфигурирование бенчмарка*/
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 1)
    public void init() {
        fib(43);
    }

    static long fib(int n) {
        return n <= 1 ? (Math.max(n, 0)) : (fib(n - 1) + fib(n - 2));
    }

    static long fibonacciLoop(int n) {
        if (n == 0) {
            return 0;
        }
        long x = 1;
        long y = 1;
        for (int i = 2; i < n; i++) {
            y = x + y;
            x = y - x;
        }
        return y;
    }
}
