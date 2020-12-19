package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class CountBarrierExt {
    @GuardedBy("this")
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrierExt(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            System.out.println(String.format("%s started...", Thread.currentThread().getName()));
            count++;
            System.out.println(String.format("%s count = %d", Thread.currentThread().getName(), count));

            monitor.notifyAll();
        }
    }

    public void await() {
        synchronized (monitor) {
            System.out.println(String.format("%s started...", Thread.currentThread().getName()));
            try {
                while (count < total) {
                    System.out.println(String.format("%s is waiting...", Thread.currentThread().getName()));
                    monitor.wait();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(String.format("%s resumed!", Thread.currentThread().getName()));
            System.out.println(String.format("%s finally waited for someone!", Thread.currentThread().getName()));
        }
    }

    public static void main(String[] args) {
        int totalCall = 10;
        CountBarrierExt countBarrier = new CountBarrierExt(totalCall);
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
        Thread th3 = new Thread(
                () -> {
                    System.out.println("-= " + Thread.currentThread().getName() + " =-");
                    countBarrier.await();
                }, "Third"
        );
        th1.start();
        th2.start();
        th3.start();

        try {
            th1.join();
            th2.join();
            th3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
