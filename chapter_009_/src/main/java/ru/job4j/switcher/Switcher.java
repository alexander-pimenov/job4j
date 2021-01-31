package ru.job4j.switcher;

/*
 * В задании нужно многопоточные нити сделать последовательными.
 * Есть две нити. Нужно, чтобы сначала нить А печатала на консоль текст,
 * а потом нить В. Нить А всегда печатает первой.
 * Для реализации работы использовать метод wait, notify, notifyAll.
 */
public class Switcher {
    public static void main(String[] args) {
        MasterSlaveBarrier masterSlaveBarrier = new MasterSlaveBarrier();
        Thread first = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                masterSlaveBarrier.tryMaster();
                masterSlaveBarrier.doneMaster();
            }
        }, "Thread A");
        Thread second = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                masterSlaveBarrier.trySlave();
                masterSlaveBarrier.doneSlave();
            }
        }, "Thread B");

        second.start();
        first.start();

        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
