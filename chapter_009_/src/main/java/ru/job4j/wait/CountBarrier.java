package ru.job4j.wait;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/**
 * Класс, который блокирует выполнение по условию счетчика.
 */
@ThreadSafe
public class CountBarrier {
    @GuardedBy("this")
    private final Object lock = this;

    private final int total;

    private int count = 0;

    public CountBarrier(int total) {
        this.total = total;
    }

    public void count() {
        synchronized (lock) {

            final String currentName = Thread.currentThread().getName();
            System.out.println(String.format("%s started....", currentName));
            count++;
            System.out.println(String.format("%s count = %d", currentName, count));
            lock.notifyAll();
        }
    }

    public void await() {
        synchronized (lock) {
            final String currentName = Thread.currentThread().getName();

            System.out.println(String.format("%s started....", currentName));
            try {
                while (count < total) {
                    System.out.println(String.format("%s is waiting...", currentName));
                    lock.wait();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(String.format("%s resumed!", currentName));
        }
    }

    public static void main(String[] args) {
        int totalCall = 10;
        CountBarrier countBarrier = new CountBarrier(totalCall);
        Thread th1 = new Thread(
                () -> {
                    System.out.println("-= " + Thread.currentThread().getName() + " =-");

                    for (int i = 0; i < totalCall; i++) {
                        countBarrier.count();
                    }
                }, "First"
        );
        Thread th2 = new Thread(
                () -> {
                    System.out.println("-= " + Thread.currentThread().getName() + " =-");
                    countBarrier.await();
                }, "Second"
        );

        th1.start();
        th2.start();

        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
