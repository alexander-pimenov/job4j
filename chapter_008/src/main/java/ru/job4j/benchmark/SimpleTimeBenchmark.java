package ru.job4j.benchmark;

import java.util.concurrent.TimeUnit;

/**
 * Benchmarking – измерение производительности.
 * Замерять производительность можно разными инструментами.
 * <p>
 * Рассматривается пример из этого источника
 * https://blog.softhints.com/java-benchmarks-examples/
 * <p>
 * Если нам нужно приблизительное представление об истекшем
 * времени в секундах или миллисекундах, мы можем использовать:
 * <p>
 * System.nanoTime ();
 * System.currentTimeMillis ()
 * <p>
 * В программе ниже мы получаем 43-е число Фибоначчи рекурсией.
 * Мы измеряем время начала и окончания.
 */
public class SimpleTimeBenchmark {
    public static void main(String[] args) {

        int n = 2;
//        int n = 43;

        System.out.println("Fibonacci iteration:");

        //
        // Начало, действие и конец
        //
        long start = System.nanoTime();

//        long fibN = fibonacci(n);
//        long fibN = fib(n);
        long fibN = fibonacciLoop(n);
        System.out.printf("result = %d is: %d \n", n, fibN);

        long elapsedTime = System.nanoTime() - start;

        //
        // Вывод в разных форматах: мс, нс, секунды, минуты
        // Преобразуем с помощью метода:
        // double seconds = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
        //

        double seconds = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
        double secondDecimalPrecision = (double) elapsedTime / 1000000000.0;
        double ms = TimeUnit.MILLISECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
        double minutes = TimeUnit.MINUTES.convert(elapsedTime, TimeUnit.NANOSECONDS);

        System.out.printf("Nanoseconds: %d ns\n", elapsedTime);
        System.out.printf("Milliseconds: %f ms\n", ms);
        System.out.printf("Seconds: %f s\n", seconds);
        System.out.printf("Seconds2: %f s\n", secondDecimalPrecision);
        System.out.printf("Minutes: %f minutes\n", minutes);

    }

    /*
     * Ниже представленны методы вычисления числа Фибоначи
     * с помощью рекурсии, с помощью цикла.
     * <p>
     * Определение чисел Фибоначчи
     * Числа Фибоначчи - это последовательность натуральных
     * чисел, которая начинается с чисел ноль и один, а
     * каждое следующее число равно сумме двух предыдущих:
     * <p>
     * F_0 = 0
     * F_1 = 1
     * F_n = F_{n - 1} + F_{n - 2}
     * Первые 10 чисел Фибоначчи:
     * <p>
     * 0=0, 1=1, 2=1, 3=2, 4=3, 5=5, 6=8, 7=13, 8=21, 9=34
     * <p>
     * В программе вызываем разные методы, чтобы видесь их
     * скорость выполнения.
     */

    /**
     * Метод вычисления числа Фибоначи с помощью рекурсии.
     *
     * @param n входное число.
     * @return число Фибоначчи.
     */
    private static long fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    /**
     * Метод вычисления числа Фибоначи с помощью цикла.
     *
     * @param n входное число.
     * @return число Фибоначчи.
     */
    public static long fibonacciLoop(int n) {
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

    /**
     * Метод вычисления числа Фибоначи с помощью рекурсии.
     * Метод в одну строку.
     *
     * @param n входное число.
     * @return число Фибоначчи.
     */
    private static long fib(int n) {
        return n <= 1 ? (Math.max(n, 0)) : (fib(n - 1) + fib(n - 2));
    }
}
