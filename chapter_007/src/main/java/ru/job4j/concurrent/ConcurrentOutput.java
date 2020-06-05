package ru.job4j.concurrent;

public class ConcurrentOutput {
    public static void main(String[] args) throws InterruptedException {
        //
        Thread another = new Thread(
                () -> System.out.println("нить another -> " + Thread.currentThread().getName())
        );

        //
        Thread second = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("нить second -> " + Thread.currentThread().getName());
                    }
                }
        );

        //
        Thread third = new Thread(
                () -> System.out.println("нить third -> " + Thread.currentThread().getName())
        );

        another.start();
        second.start();
        third.start();

        another.run();
        second.run();
        third.run();

        Thread.sleep(3000);
        System.out.println("main метод -> " + Thread.currentThread().getName());
    }
}
//Вывод результата в консоль:
//
//нить another -> main
//нить second -> main
//нить third -> main
//нить another -> Thread-0
//нить third -> Thread-2
//нить second -> Thread-1
//main метод -> main
