package ru.job4j.synch.withsharedresource;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class CountTest {

    /**
     * Класс описывает нить со счетчиком.
     */
    private class ThreadCount extends Thread {
        private final Count count;

        private ThreadCount(final Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }
    }

    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        //Создаем счетчик.
        final Count count = new Count();
        //Создаем нити.
        Thread first = new ThreadCount(count);
        Thread second = new ThreadCount(count);
        //Запускаем нити.
        first.start();
        second.start();
        //Заставляем главную нить дождаться выполнения наших нитей.
        first.join();
        second.join();
        //Проверяем результат.
        assertThat(count.get(), is(2));

    }

    /**
     * Класс описывает нить со счетчиком.
     * В методе run() в цикле увеличиваем счетчик.
     */
    private class ThreadCount2 extends Thread {
        private final Count count;

        private ThreadCount2(final Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(500);
                    this.count.increment();
                    System.out.println(Thread.currentThread().getName()
                            + " " + count.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void whenExecute3ThreadsThen15() throws InterruptedException {
        //Создаем счетчик.
        final Count count = new Count();
        //Создаем нити.
        Thread first = new ThreadCount2(count);
        Thread second = new ThreadCount2(count);
        Thread third = new ThreadCount2(count);
        //Запускаем нити.
        first.start();
        second.start();
        third.start();
        //Заставляем главную нить дождаться выполнения наших нитей.
        first.join();
        second.join();
        third.join();
        //Проверяем результат.
        assertThat(count.get(), is(15));
    }
}