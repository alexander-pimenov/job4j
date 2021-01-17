package ru.job4j.pool;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    @Test
    public void threadPoolTest() {
        ThreadPool threadPool = new ThreadPool();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            threadPool.work(() ->
                    System.out.println(
                            "Executing task [" + finalI + "] inside: "
                                    + Thread.currentThread().getName()));
            try {
                TimeUnit.MICROSECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //ждем 5 секунд
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //закрываем ThreadPool
        threadPool.shutdown();
    }

    @Test
    public void threadPoolTestOneRunnableObject() {
        Runnable task1 = () -> {
            System.out.println("Executing Task1 inside : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ignore) {
            }
            System.out.println("My task1 end..");
        };

        ThreadPool threadPool = new ThreadPool();

        threadPool.work(task1);
        //ждем 5 секунд
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.shutdown();
    }
}