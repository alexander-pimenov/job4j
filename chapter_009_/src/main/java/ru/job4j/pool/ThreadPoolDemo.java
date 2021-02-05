package ru.job4j.pool;

import java.util.concurrent.TimeUnit;

/*В данной программе рассмотрим работу двух пулов потоков.*/
public class ThreadPoolDemo {

    public static void main(String[] args) {
        System.out.println("The Main thread started!");

        //для примера создаем 1-1 пул потоков
        ThreadPool threadPool = new ThreadPool();
        //для примера создаем 2-й пул потоков
        ThreadPool threadPool2 = new ThreadPool();

        threadPool2.work(new Task(1000));
        threadPool.work(new Task(100));
        threadPool.work(new Task(200));
        threadPool.work(new Task(300));
        threadPool.work(new Task(400));
        threadPool.work(new Task(500));
        threadPool.work(new Task(600));

        //задержка 15 сек, чтоб проверить работу с ней и без неё.
//        try {
//            TimeUnit.SECONDS.sleep(15);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        int runnableCount = 15;
        Runnable r;
        for (int i = 0; i < runnableCount; i++) {
            r = new Task(i);
            threadPool2.work(r);
            threadPool.work(r);
        }

        threadPool.waitUntilAllTasksFinished();
        threadPool2.waitUntilAllTasksFinished();

        threadPool.shutdown();
        threadPool2.shutdown();

        System.out.println("The Main thread has finished running!");
    }
}

class Task implements Runnable {
    //у каждой задачи будет id
    private int id;

    Task(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Executing " + this.toString()
                + " inside : " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();//для правильной обработки перевыставим флаг
        }
        System.out.println(this.toString() + " end..");
    }

    @Override
    public String toString() {
        return "Task{id=" + id + '}';
    }
}

