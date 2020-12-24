package ru.job4j.nonblocking;

import org.junit.Test;
import ru.job4j.synch.Count;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class CASCountTest {

    @Test
    public void whenThreeThreadsIncreaseCASCounter() throws InterruptedException {
        CASCount casCount = new CASCount();
        Thread th1 = new Thread(
                () -> {
                    for (int i = 0; i < 1000000; i++) {
                        casCount.increment();
                    }
                }
        );
        Thread th2 = new Thread(
                () -> {
                    for (int i = 0; i < 1000000; i++) {
                        casCount.increment();
                    }
                }
        );
        Thread th3 = new Thread(
                () -> {
                    for (int i = 0; i < 1000000; i++) {
                        casCount.increment();
                    }
                }
        );
        long start = System.currentTimeMillis();
        th1.start();
        th2.start();
        th3.start();
        th1.join();
        th2.join();
        th3.join();
        System.out.println("Прошло времени: " + (System.currentTimeMillis() - start) + " ms");
        System.out.println(casCount.get());
        assertThat(casCount.get(), is(3000000));
    }

    /*Тест для проверки работы обычного класса Count с блоками synchronized()*/
    @Test
    public void whenThreeThreadsIncreaseCommonCounter() throws InterruptedException {
        Count count = new Count();
        Thread th1 = new Thread(
                () -> {
                    for (int i = 0; i < 1000000; i++) {
                        count.increment();
                    }
                }
        );
        Thread th2 = new Thread(
                () -> {
                    for (int i = 0; i < 1000000; i++) {
                        count.increment();
                    }
                }
        );
        Thread th3 = new Thread(
                () -> {
                    for (int i = 0; i < 1000000; i++) {
                        count.increment();
                    }
                }
        );
        long start = System.currentTimeMillis();
        th1.start();
        th2.start();
        th3.start();
        th1.join();
        th2.join();
        th3.join();
        System.out.println("Прошло времени: " + (System.currentTimeMillis() - start) + " ms");
        System.out.println(count.get());
        assertThat(count.get(), is(3000000));
    }
}