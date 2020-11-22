package ru.job4j.concurrent;

public class Wget3 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            /*В этом try-catch мы ловим прерывание потока thread
             * из main метода, поймав его мы проваливаемся в
             * блок catch, выводим сообщение "Tread was interrupted!"
             * и поток завершает свою работу.
             * Если этот блок не использовать, то прерывание из метода
             * main мы можем услышать используя блок условия if() и
             * с помощью break: выйти из цикла, тем самым завершив работу
             * потока */
            try {
                for (int i = 0; i <= 100; i++) {
//                    if (Thread.currentThread().isInterrupted()) {
//                        System.out.println("Thread was interrupted!!!");
//                        break;
//                    }
                    System.out.print("\rLoading : " + i + " %");
                    Thread.sleep(400);
                }
                System.out.print("\rLoaded successfully.");
            } catch (InterruptedException e) {
                System.out.println("\nTread was interrupted!");
            }
        });
        System.out.println("Starting thread.");
        thread.start();
        Thread.sleep(5000);
        thread.interrupt(); //прерываем поток thread из главного потока main
        thread.join();
        System.out.println("Thread has finished.");
    }
}
