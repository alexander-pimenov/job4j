package ru.job4j.gc;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * В составе JDK есть полезная утилита jvisualvm (Java VisualVM),
 * позволяющая снимать дампы, анализировать производительность, состояние потоков и памяти.
 * <p>
 * Для анализа создадим вот такую программу на Java «GCTestProgram.java»
 */
public class GCTestProgram {
    private static final int THREAD_COUNT = 10;
    private static final int TASKS_COUNT = 10;
    private static final long WORK_MINUTES = 60L;
//    private static final long WORK_MINUTES = 10L;

    private static class MyClass {
        private byte[] bytes = new byte[100_000];
    }

    private static final ConcurrentMap<Integer, MyClass> concurrentMap = new ConcurrentHashMap<>();

    private static class MyTask implements Callable<Boolean> {
        //пишем в методе ту логику, которую надо выполнить
        @Override
        public Boolean call() throws Exception {
            long startTime = System.currentTimeMillis();
            // Каждый поток работает только WORK_MINUTES минут с момента старта.
            while (System.currentTimeMillis() < startTime + 1000L * 60L * WORK_MINUTES) {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
                concurrentMap.put(
                        ThreadLocalRandom.current().nextInt( //описание это класса смотри ниже
                                Integer.MIN_VALUE, Integer.MAX_VALUE),
                        new MyClass());
            }
            return true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //создаем пул потоков
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        Future[] futures = new Future[TASKS_COUNT];
//        Future<Boolean>[] futures = new Future[TASKS_COUNT];
        System.out.println("Scheduling threads..."); //планирование потоков
        for (int n = 0; n < TASKS_COUNT; n++) {
            futures[n] = executorService.submit(new MyTask());
        }
        System.out.println("Awaiting termination...");
        executorService.awaitTermination(WORK_MINUTES * 2L, TimeUnit.MINUTES);
        System.out.println("Finished.");
    }
}

/*
 * <p>
 * class ThreadLocalRandom extends Random:
 * <p>
 * Генератор случайных чисел, изолированный от текущего потока. Подобно глобальному
 * генератору {@link java.util.Random}, используемому классом {@link java.lang.Math},
 * {@code ThreadLocalRandom} инициализируется внутренне сгенерированным начальным числом,
 * которое иначе не может быть изменено. Если возможно, использование {@code ThreadLocalRandom}
 * вместо общих объектов {@code Random} в параллельных программах обычно вызывает гораздо
 * меньше накладных расходов и конфликтов.
 * <p>
 * Использование {@code ThreadLocalRandom} особенно подходит, когда несколько задач
 * (например, каждая из них - {@link ForkJoinTask}) используют случайные числа параллельно
 * в пулах потоков.
 * <p>
 * Использование этого класса обычно должно иметь следующий вид:
 * {@code ThreadLocalRandom.current (). nextX (...)} (где {@code X} - {@code Int}, {@code Long} и т. д.).
 * Когда все использования имеют такую форму, невозможно случайно разделить
 * {@code ThreadLocalRandom} между несколькими потоками.
 * <p>
 * Этот класс также предоставляет дополнительные часто используемые методы ограниченной
 * случайной генерации.
 * <p>
 * Экземпляры {@code ThreadLocalRandom} не являются криптографически безопасными.
 * Вместо этого рассмотрите возможность использования {@link java.security.SecureRandom}
 * в чувствительных к безопасности приложениях.
 * Кроме того, созданные по умолчанию экземпляры не используют криптографически случайное
 * начальное число, если для системного свойства {@linkplain System # getProperty}
 * {@code java.util.secureRandomSeed} не задано значение {@code true}.
 * */
