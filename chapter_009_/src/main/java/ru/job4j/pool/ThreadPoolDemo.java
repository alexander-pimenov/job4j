package ru.job4j.pool;

import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {

    public static void main(String[] args) {
        System.out.println("The Main thread started!");

        ThreadPool threadPool = new ThreadPool();

        threadPool.work(new Task(1));
        threadPool.work(new Task(2));
        threadPool.work(new Task(3));
        threadPool.work(new Task(4));
        threadPool.work(new Task(5));
        threadPool.work(new Task(6));

        //задержка 15 сек, чтоб проверить работу с ней и без неё.
//        try {
//            TimeUnit.SECONDS.sleep(15);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

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
