package ru.job4j.pool.threadpoolversion1;

import ru.job4j.simpleblockingqueue.SimpleBlockingQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPool {

    // Количество созданных пулов потоков
    private static AtomicInteger poolCount = new AtomicInteger(0);

    // Флаг для управления рабочими потоками пула
    private AtomicBoolean execute;

    // Контейнер для рабочих потоков в пуле
    private final List<Thread> threads;

    // Очередь запускаемых файлов (Runnable)
    private final SimpleBlockingQueue<Runnable> tasksQueue;

    //Инициализация пула должна быть по количеству ядер в системе.
    //устанавливаем размер внутренней блокирующей очереди, например, равной 8.
    public ThreadPool() {
        poolCount.incrementAndGet();
        this.tasksQueue = new SimpleBlockingQueue<>(8);
        this.execute = new AtomicBoolean(true);
        this.threads = new ArrayList<>();

        //количество ядер в системе
        int poolSize = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < poolSize; i++) {

            Thread thread = new Thread(
                    () -> {
                        while (!Thread.currentThread().isInterrupted()
                                || this.execute.get() || !this.tasksQueue.isEmpty()) {
                            Runnable runnable;
                            try {
                                while ((runnable = this.tasksQueue.poll()) != null) {
                                    runnable.run();
                                }
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                        //Зададим имя пула потоков и рабочего потока
                    }, "ThreadPool-" + poolCount.get() + "-Thread-" + i);
            thread.start();
            this.threads.add(thread);
        }
    }

    /**
     * Добавляет Runnable объект в очередь на обработку.
     *
     * @param job Runnable объект
     */
    public void work(Runnable job) {
        if (this.execute.get()) {
            try {
                this.tasksQueue.offer(job);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Метод устанавливает флаг execute в false, и работа пула потоков
     * продолжается до тех пор, пока очередь имеет Runnable объекты.
     */
    public synchronized void shutdown() {
        execute.set(false);
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}