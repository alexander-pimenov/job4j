package ru.job4j.pool.threadpoolimproved;

import java.util.concurrent.TimeoutException;

public class TestSimpleThreadpool {
    public static void main(String[] args) {
        SimpleThreadpool threadpool = SimpleThreadpool.getInstance();
        int runnableCount = 10;
        Runnable r;
        for (int i = 0; i < runnableCount; i++) {
            r = new SimpleTask(i);
            threadpool.execute(r);
        }

        threadpool.shutdown();
//        threadpool.terminate(); //быстрая остановка

        try {
            threadpool.awaitTermination();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}

// Задача класса для выполнения (Шаг 1)
class SimpleTask implements Runnable {
    //у каждого потока будет id
    private int id;

    SimpleTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Executing " + this.toString()
                + " inside : " + Thread.currentThread().getName());
        for (int i = 0; i < 50; i++) {
            System.out.println(Thread.currentThread().getName() + " производит счёт. i = " + i );
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
//                e.printStackTrace();
                System.out.println("----- " + Thread.currentThread().getName() + " " + e.getMessage());
            }
        }
        System.out.println(this.toString() + " end..");
    }

    @Override
    public String toString() {
        return "Task{id=" + id + '}';
    }
}