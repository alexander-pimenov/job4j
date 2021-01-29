package ru.job4j.simpleblockingqueue;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/*
 * Класс SimpleBlockingQueue3<T> отличается от класса SimpleBlockingQueue<T> лишь
 * тем, что методы offer(T value) и T poll() не пробрасывают исключения,
 * а обрабатывают их внутри.
 */
@ThreadSafe
public class SimpleBlockingQueue3<T> {

    //Создадим объект для синхронизации
    @GuardedBy("this")
    private final Object lock = this;

//    private final Object lock = this;

    //Создадим обычную очередь для работы с ней.
    private Queue<T> queue = new LinkedList<>();

    //максимальное количество элементов в очереди
    private final int limitBound;

    public SimpleBlockingQueue3(int limitBound) {
        this.limitBound = limitBound;
    }

    /**
     * Заполняем очередь.
     *
     * @param value значение.
     */
    public synchronized void offer(T value) {
        try {
            while (queue.size() == limitBound) {
                System.out.println(String.format("%s waiting...", Thread.currentThread().getName()));
                this.wait();
            }
            if (queue.size() == 0) {
                this.notifyAll();
            }
            queue.offer(value);
            this.notifyAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Берем элемент из очереди.
     *
     * @return значение.
     */
    public synchronized T poll() {
        T value = null;
        try {
            while (queue.size() == 0) {
                System.out.println(String.format("%s waiting...", Thread.currentThread().getName()));
                this.wait();
            }
            if (queue.size() == limitBound) {
                this.notifyAll();
            }
            value = queue.poll();
            this.notifyAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return value;
    }

    public synchronized int size() {
        return queue.size();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    public synchronized void clear() {
        this.queue.clear();
    }

    @Override
    public String toString() {
        return queue.toString();
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " started.");
        SimpleBlockingQueue3<String> queue3 = new SimpleBlockingQueue3<>(3);

        //поток ПРОИЗВОДИТЕЛЬ.
        //Он в цикле добавляет в очередь слова из массива words, с задержкой в 3 сек.
        Thread producer = new Thread(() -> {
            String[] words = new String[]{"123", "abc", "qwerty", "queue", "stack", "array", "list", "TEST"};

            for (int i = 0; i < words.length && !Thread.interrupted(); ) {
                try {
                    TimeUnit.SECONDS.sleep(3);
//                    Thread.sleep(4000);
                    queue3.offer(words[i]);
                    System.out.println(Thread.currentThread().getName() + ": записал в очередь "
                            + "\'" + words[i] + "\'"
                            + ", число элементов в очереди: " + queue3.size());
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Производитель закончил работу.");
        }, "Производитель");

        //поток ПОТРЕБИТЕЛЬ.
        //Он с задержкой в 7 сек. берет слово из очереди и печатает это слово
        // перевернотое наоборот.
        Thread consumer = new Thread(() -> {
            StringBuilder sb = new StringBuilder();
            while (!Thread.interrupted()) {
                try {
                    sb.setLength(0);
                    Thread.sleep(7000);
                    sb.append(queue3.poll());
                    System.out.println(Thread.currentThread().getName() + ": обработал из очереди "
                            + "\'" + sb.reverse() + "\'" + ", число элементов: " + queue3.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.println("Потребитель закончил работу.");
        }, "Потребитель");

        producer.start();
        consumer.start();
        System.out.println(Thread.currentThread().getName() + " finished.");
    }
}

//В Output будет видно, что поток Потребителя не закончит свою работу,
// а перейдет в режим ожидания новых заданий (элементов) из очереди:
//main started.
//main finished.
//Производитель: записал в очередь '123', число элементов в очереди: 1
//Производитель: записал в очередь 'abc', число элементов в очереди: 2
//Потребитель: обработал из очереди '321', число элементов: 1
//Производитель: записал в очередь 'qwerty', число элементов в очереди: 2
//Производитель: записал в очередь 'queue', число элементов в очереди: 3
//Потребитель: обработал из очереди 'cba', число элементов: 2
//Производитель: записал в очередь 'stack', число элементов в очереди: 3
//Производитель waiting...
//Производитель: записал в очередь 'array', число элементов в очереди: 3
//Потребитель: обработал из очереди 'ytrewq', число элементов: 3
//Производитель waiting...
//Производитель: записал в очередь 'list', число элементов в очереди: 3
//Потребитель: обработал из очереди 'eueuq', число элементов: 3
//Производитель waiting...
//Производитель: записал в очередь 'TEST', число элементов в очереди: 3
//Производитель закончил работу.
//Потребитель: обработал из очереди 'kcats', число элементов: 3
//Потребитель: обработал из очереди 'yarra', число элементов: 2
//Потребитель: обработал из очереди 'tsil', число элементов: 1
//Потребитель: обработал из очереди 'TSET', число элементов: 0
//Потребитель waiting...
//
//Process finished with exit code -1
