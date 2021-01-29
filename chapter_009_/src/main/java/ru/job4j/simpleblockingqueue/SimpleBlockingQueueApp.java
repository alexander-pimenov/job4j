package ru.job4j.simpleblockingqueue;

import java.util.Random;

/**
 * Программа для наглядного тестирования работы с классом SimpleBlockingQueue.
 */
public class SimpleBlockingQueueApp {

    /**
     * Метод, в котором мы создаем массив потоков из 10 шт.
     * Сперва они заполняют очередь случайными целыми числами.
     * Очередь заполнена. Немного ждем.
     * Потом вызываем 10 потоков, которые читают данные из очереди.
     * Видим, что сначала потоки записывают в очередь числа Продюсером,
     * а потом Консюмер вычитывает эти же числа.
     * Благодаря тому, что очередь имеет размер на 15 элементов, а мы
     * записываем в неё не более 10, то всё отработает нормально. Если
     * изменить ёмкость очереди, например поставить не на 15 элементов,
     * а на 5, то программа зависнет после записи 5-го элемента и будет
     * ждать пока Консюмер вычитает (освободит) очередь от элементов
     * или хотя бы одного.
     */
    public static void start1() {
        SimpleBlockingQueue2<Integer> queue = new SimpleBlockingQueue2<>(15);

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

        //в цикле запускаем несколько потоков продюсеров.
        //чтобы заполнить очередь элементами.
        for (int i = 0; i < producers.length; i++) {
            producers[i].start();
            try {
                producers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //ждем немного
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //в цикле запускаем несколько потоков консюмеров.
        //чтобы вычитывать данные из очереди.
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
     * Т.е. Продюсер. И потоки, которые читают данные из очереди. Т.е. Консюмер.
     * Видим, что происходит запись одного элемента и следом идет его считывание.
     * Не важно сколько будет записываться в очередь элементов, продюсер сможет
     * записать туда не более 5, следом консюмер будет вычитывать по одному эелементу.
     * Программа не зависнет, как в методе start1(), когда если уменьшить ёмкость очереди
     * до 5.
     */
    public static void start2() {
        SimpleBlockingQueue2<Integer> queue = new SimpleBlockingQueue2<>(5);

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
        start1();
//        start2();

    }
}
