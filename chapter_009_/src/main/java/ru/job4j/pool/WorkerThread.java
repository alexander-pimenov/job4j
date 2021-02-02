package ru.job4j.pool;

import ru.job4j.simpleblockingqueue.SimpleBlockingQueue;

public class WorkerThread extends Thread {
    private SimpleBlockingQueue<Runnable> taskQueue;
    private boolean isStopped = false;

    public WorkerThread(SimpleBlockingQueue<Runnable> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        while (!isStopped()) {
            Runnable runnable;
            try {
                while ((runnable = taskQueue.poll()) != null) {
                    runnable.run();
                }
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private synchronized boolean isStopped() {
        return isStopped;
    }

    public synchronized void doStop() {
        isStopped = true;
        this.interrupt();
    }
}
