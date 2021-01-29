package ru.job4j.pool.threadpoolnew;

import ru.job4j.simpleblockingqueue.SimpleBlockingQueue;

import java.util.concurrent.atomic.AtomicBoolean;

public class JobWorker extends Thread {
    private AtomicBoolean execute;
    private SimpleBlockingQueue<Runnable> runnables;

    public JobWorker(String name, AtomicBoolean execute, SimpleBlockingQueue<Runnable> runnables) {
        super(name);
        this.execute = execute;
        this.runnables = runnables;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()
                || execute.get() || !runnables.isEmpty()) {
            Runnable runnable;
            try {
                while ((runnable = runnables.poll()) != null) {
                    runnable.run();
                }
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}