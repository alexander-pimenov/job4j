package ru.job4j.concurrent;

public class Wget2 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    System.out.print("\rLoading : " + i + " %");
                    Thread.sleep(500);
                }
                System.out.print("\rLoaded successfully.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println();
                System.out.println(Thread.currentThread().isInterrupted());
                System.out.println(Thread.currentThread().getState());
            }
        });
        System.out.println("Starting thread.");
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
        thread.join();
        System.out.println("Thread has finished.");
    }
}
