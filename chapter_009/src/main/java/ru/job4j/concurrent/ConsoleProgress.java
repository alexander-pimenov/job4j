package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        int index = 0;
        char[] process = {'\\', '|', '/', '\u2014'}; //'\u2014' - длиное тире
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.print("\rLoading... " + process[index % 4]);
                Thread.sleep(500);
                index++;
            }
        } catch (InterruptedException e) {
            System.out.println("\rLoaded. \u263A"); //'\u263A' - код смайлика
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000);
        progress.interrupt();
    }
}