package ru.job4j.benchmark.oracle;

/**
 * Бенчмаркинг не кажется таким уж сложным. В конце концов, все должно
 * сводиться к измерению того, сколько времени занимает некоторая операция,
 * и если операция слишком быстрая, мы всегда можем повторить ее в цикле.
 * Хотя этот подход подходит для программы, написанной на статически
 * компилируемом языке, таком как C, с адаптивной виртуальной машиной все
 * обстоит иначе.
 * <p>
 * Реализация. Давайте возьмем наивный подход и сами разработаем фреймворк
 * для тестирования. Решение вписывается в один статический метод, как
 * показано ниже в примере.
 * Код взят отсюда:
 * https://www.oracle.com/technical-resources/articles/java/architect-benchmarking.html
 * <p>
 * Тестируем разные методы: nothing, distance, constant.
 * <p>
 * Пропускная способность constant оказывается ниже, чем у distance,
 * хотя constant вычислений не выполняется.
 * <p>
 * nothing имеет самую низкую пропускную способность, хотя и меньше всего.
 */
public class WrongBench {
    /*
     * Введем следующие константы для наших экспериментов:
     *  RUN_MILLIS: 4-секундные прогоны,
     *  REPEAT: 10 измерений,
     *  WARMUP: 15 раундов разминки
     *  LOOP: внутренний цикл из 10 000 итераций:
     * */

    static final long RUN_MILLIS =
            4000;
    static final int REPEAT = 10;
    static final int WARMUP = 15;
    static final int LOOP = 10_000;

    public static void main(String[] args) {

        bench("nothing", RUN_MILLIS, LOOP, WARMUP, REPEAT,
                WrongBench::nothing);

        bench("distance", RUN_MILLIS, LOOP, WARMUP, REPEAT,
                () -> distance(0.0, 0.0, 10.0, 10.0));

        bench("constant", RUN_MILLIS, LOOP, WARMUP, REPEAT,
                () -> constant(0.0d, 0.0d, 10.0d, 10.0d));

    }

    /*тестируемый метод, с вычислением*/
    static double distance(
            double x1, double y1,
            double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        return Math.sqrt((dx * dx)
                + (dy * dy));
    }

    /*тестируемый метод, константный*/
    static double constant(
            double x1, double y1,
            double x2, double y2) {
        return 0.0d;
    }

    /*тестируемый метод, пустой*/
    static void nothing() {

    }

    /**
     * Метод тестирующий другие методы.
     *
     * @param name      название.
     * @param runMillis скольок секунд делаем прогоны.
     * @param loop      количество циклов повторений.
     * @param warmup    количество раундов разминки.
     * @param repeat    количество измерений.
     * @param runnable  запускаемый метод.
     */
    public static void bench(String name, long runMillis, int loop,
                             int warmup, int repeat, Runnable runnable) {
        System.out.println("Running: " + name);
        int max = repeat + warmup;
        long average = 0L;
        for (int i = 0; i < max; i++) {
            long nops = 0;
            long duration = 0L;
            long start = System.currentTimeMillis();
            while (duration < runMillis) {
                for (int j = 0; j < loop; j++) {
                    runnable.run();
                    nops++;
                }
                duration = System.currentTimeMillis() - start;
            }
            long throughput = nops / duration;
            boolean benchRun = i >= warmup;
            if (benchRun) {
                average = average + throughput;
            }
            System.out.println(throughput + " ops/ms" + "["
                    + (!benchRun ? " (warmup) | " : " | ") + "]");
        }
        average = average / repeat;
        System.out.println("\n[ ~" + average + " ops/ms ]\n");
    }
}

