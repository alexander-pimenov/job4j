package ru.job4j.pool;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    @Test
    public void threadPoolTest() {
        ThreadPool threadPool = new ThreadPool();
        for (int i = 0; i < 500; i++) {
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
}