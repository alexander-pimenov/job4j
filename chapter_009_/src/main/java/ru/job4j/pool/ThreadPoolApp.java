package ru.job4j.pool;

import java.util.concurrent.TimeUnit;

public class ThreadPoolApp {
    public static void main(String[] args) {

        //Создаем задачи (Runnable Object) для выполнения. (шаг 1)
        Runnable task1 = () -> {
            System.out.println("Executing Task1 inside : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
//                    e.printStackTrace();
            }
            System.out.println("My task1 end..");
        };

        Runnable task2 = () -> {
            System.out.println("Executing Task2 inside : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
//                    e.printStackTrace();
            }
            System.out.println("My task2 end..");
        };

        Runnable task3 = () -> {
            System.out.println("Executing Task3 inside : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
//                    e.printStackTrace();
            }
            System.out.println("My task3 end..");
        };

        Runnable task4 = () -> {
            System.out.println("Executing Task4 inside : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
            System.out.println("My task4 end..");
        };

        Runnable task5 = () -> {
            System.out.println("Executing Task5 inside : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
            System.out.println("My task5 end..");
        };

        Runnable task6 = () -> {
            System.out.println("Executing Task6 inside : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
//                e.printStackTrace();

            }
            System.out.println("My task6 end..");
        };

        //создаем пул исполнителей (ШАГ 2)
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ThreadPool threadPool = new ThreadPool();

        //передаем объекты в пул (шаг 3)
        threadPool.work(task1);
        threadPool.work(task2);
        threadPool.work(task3);
        threadPool.work(task4);
        threadPool.work(task5);
        threadPool.work(task6);

        //просто задержка 20 сек.
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Создаем задачи (Runnable Object) для выполнения пулом потоков. (снова шаг 1)
        //создание пула исполнителей (потоков) пропустим, используем уже имеющийся.
        for (int i = 0; i < 500; i++) {
            int finalI = i;
            //передаем объекты в пул (шаг 3)
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
        //просто задержка 5 секунд.
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //закрываем пул потоков ThreadPool (шаг 4)
        threadPool.shutdown();

        //Возвращает {@code true}, если все задачи были выполнены после завершения работы.
        //Обратите внимание, что {@code isTerminated} никогда не будет {@code true}, если
        //сначала не было вызвано {@code shutdown} или {@code shutdownNow}.
//        while (!threadPool.isTerminated()) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
