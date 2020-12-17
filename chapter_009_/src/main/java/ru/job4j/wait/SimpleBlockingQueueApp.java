package ru.job4j.wait;

import java.util.Random;

/**
 * Программа для наглядного тестирования работы с классом SimpleBlockingQueue.
 */
public class SimpleBlockingQueueApp {

    /**
     * Метод, в котором мы создаем массив потоков из 10 шт.
     * Сперва они заполняют очередь случайными целыми числами.
     * Потом вызываем 10 потоков, которые читают данные из очереди.
     * Видим, что сначала потоки записывают в очередь числа, а
     * потом Консюмер вычитывает эти же числа.
     */
    public static void start1() {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(15);

        Thread[] producers = new Thread[10];
        Random random = new Random();
        for (int i = 0; i < producers.length; i++) {
            producers[i] = new Thread(
                    () -> {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int num = random.nextInt(100);
                        queue.offer(num);
                    }, "Producer-" + (i + 1));
        }

        Thread[] consumers = new Thread[10];
        for (int i = 0; i < producers.length; i++) {
            consumers[i] = new Thread(
                    () -> {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int num = queue.poll();
                    }, "Consumer-" + (i + 1));
        }

        for (int i = 0; i < producers.length; i++) {
            producers[i].start();
            try {
                producers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < producers.length; i++) {
            consumers[i].start();
            try {
                consumers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод, в котором мы создаем массив потоков из 10 шт.
     * Одновременно в цикле запускаем потоки, заполняющие очередь случайными целыми числами.
     * Т.е. Продюсер. И потоки, которые читают данные из очереди.
     * Видим, что происходит запись одного элемента и следом идет его считывание.
     */
    public static void start2() {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);

        Thread[] producers = new Thread[10];
        Random random = new Random();
        for (int i = 0; i < producers.length; i++) {
            producers[i] = new Thread(
                    () -> {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int num = random.nextInt(100);
                        queue.offer(num);
                    }, "Producer-" + (i + 1));
        }

        Thread[] consumers = new Thread[10];
        for (int i = 0; i < producers.length; i++) {
            consumers[i] = new Thread(
                    () -> {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int num = queue.poll();
                    }, "Consumer-" + (i + 1));
        }

        for (int i = 0; i < producers.length; i++) {
            producers[i].start();
            consumers[i].start();
            try {
                producers[i].join();
                consumers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
//        start1();
        start2();
    }
}
