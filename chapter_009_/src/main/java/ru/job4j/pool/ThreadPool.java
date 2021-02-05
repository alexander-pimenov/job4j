package ru.job4j.pool;

import ru.job4j.simpleblockingqueue.SimpleBlockingQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPool {

    // Количество созданных пулов потоков
    private static AtomicInteger poolCount = new AtomicInteger(0);

    // Флаг для управления рабочими потоками пула
    private volatile boolean execute;

    // Контейнер для рабочих потоков в пуле
    private final List<WorkerThread> threads;

    // Очередь запускаемых файлов (Runnable)
    private final SimpleBlockingQueue<Runnable> tasksQueue;

    //Инициализация пула должна быть по количеству ядер в системе.
    //устанавливаем размер внутренней блокирующей очереди, например, равной 8.
    public ThreadPool() {
        poolCount.incrementAndGet();
        this.tasksQueue = new SimpleBlockingQueue<>(8);
        this.execute = true;
        this.threads = new ArrayList<>();

        //количество ядер в системе
        int poolSize = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < poolSize; i++) {
            WorkerThread workerThread = new WorkerThread(tasksQueue);
            workerThread.setName("ThreadPool-" + poolCount.get() + "-Thread-" + i);
            workerThread.start();
            this.threads.add(workerThread);
        }
    }

    /**
     * Добавляет Runnable объект в очередь на обработку.
     *
     * @param job Runnable объект
     */
    public void work(Runnable job) {

        if (!this.execute) {
            throw new IllegalStateException("Thread is stopped.");
        }

        try {
            this.tasksQueue.offer(job);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Метод устанавливает флаг execute в false, и работа пула потоков
     * продолжается до тех пор, пока очередь имеет Runnable объекты.
     */
    public void shutdown() {
        this.execute = false;
//        execute.set(false);
        for (WorkerThread thread : threads) {
            thread.doStop();
        }
    }

    /**
     * Метод ожидает пока есть задания в очереди.
     */
    public void waitUntilAllTasksFinished() {
        while (tasksQueue.size() > 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}