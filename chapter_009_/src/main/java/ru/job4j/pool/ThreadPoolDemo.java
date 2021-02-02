package ru.job4j.pool;

import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {

    public static void main(String[] args) {
        System.out.println("The Main thread started!");

        ThreadPool threadPool = new ThreadPool();

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
            threadPool.work(r);
        }

        threadPool.waitUntilAllTasksFinished();
        threadPool.shutdown();

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
