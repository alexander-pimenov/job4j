package ru.job4j.synch;

public class Flag {
    private static volatile boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    while (flag) {
                        System.out.println(Thread.currentThread().getName());
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            System.out.println(Thread.currentThread().getName()
                                    + " is interrupted (true or false): "
                                    + Thread.currentThread().isInterrupted());
//                            e.printStackTrace();
                        }
                    }
                }
        );
        thread.start();
        Thread.sleep(5000);
        flag = false;
        thread.join();
    }
}
