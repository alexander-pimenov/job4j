package ru.job4j.concurrent;

public class ThreadStop {
    public static void main(String[] args) {
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
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Главная нить выставляет прерывание
        thread.interrupt();
    }
}
