package ru.job4j.concurrent;

public class ThreadStop {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    int count = 0;
                    //метод isInterrupted() проверяет состояние флага прерывания.
                    //если он выставлен, то мы не заходим больше в тело цикла и выходим из метода run().
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println(count++);
                    }
                }
        );
        thread.start();
        Thread.sleep(500);

        //Главная нить выставляет прерывание
        thread.interrupt();
        Thread.sleep(500);
    }
}
