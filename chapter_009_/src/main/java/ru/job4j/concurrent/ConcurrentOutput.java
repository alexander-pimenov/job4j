package ru.job4j.concurrent;

public class ConcurrentOutput {
    public static void main(String[] args) throws InterruptedException {
        Thread another = new Thread(
                () -> System.out.println("нить another -> " + Thread.currentThread().getName())
        );
        Thread second = new Thread(
                () -> System.out.println("нить second -> " + Thread.currentThread().getName())
        );
        Thread third = new Thread(
                () -> System.out.println("нить third -> " + Thread.currentThread().getName())
        );
        another.start();
        second.start();
        third.start();
        Thread.sleep(2000);
        System.out.println("main метод -> " + Thread.currentThread().getName());
    }
}
