package ru.job4j.concurrent;

public class ConsoleProgress2 implements Runnable {

    @Override
    public void run() {
        try {
            int index = 0;
            char[] process = {'\\', '|', '/', '\u2014'}; //'\u2014' - длиное тире
            while (true) {
                System.out.print("\rLoading... " + process[index % 4]);
                Thread.sleep(500);
                index++;
            }
        } catch (InterruptedException e) {
//            System.out.println("\rLoaded.");
            System.out.println("\rThread was interrupted!");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress2());
        progress.start();
        Thread.sleep(5000);
        progress.interrupt(); //этот метод прервет выполнение потока progress
    }
}