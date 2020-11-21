package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(
                () -> System.out.println("Сообщение из нити " + Thread.currentThread().getName() + ", ёё - состояние " + Thread.currentThread().getState()));
        Thread second = new Thread(
                () -> System.out.println("Сообщение из нити " + Thread.currentThread().getName() + ", ёё - состояние " + Thread.currentThread().getState()));
        System.out.println("Нить " + first.getName() + ", состояние - " + first.getState());
        System.out.println("Нить " + second.getName() + ", состояние - " + second.getState());
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getName() + " " + first.getState());
            System.out.println(second.getName() + " " + second.getState());
        }
        first.join();
        second.join();
        System.out.println("Нить " + first.getName() + ", состояние - " + first.getState());
        System.out.println("Нить " + second.getName() + ", состояние - " + second.getState());
        System.out.println("Работа завершена.");
    }
}
