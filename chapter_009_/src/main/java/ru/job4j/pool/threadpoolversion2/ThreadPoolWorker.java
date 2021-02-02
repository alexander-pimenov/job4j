package ru.job4j.pool.threadpoolversion2;

import ru.job4j.simpleblockingqueue.SimpleBlockingQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolWorker {
    // Количество созданных пулов потоков
    private static AtomicInteger poolCount = new AtomicInteger(0);

    //Список потоков в пуле
    private final List<JobWorker> threads;

    //Очередь запускаемых файлов runnable
    private final SimpleBlockingQueue<Runnable> tasks;

    // Флаг для управления объектами JobWorker
    private AtomicBoolean execute;

    //Инициализация пула должна быть по количеству ядер в системе.
    private ThreadPoolWorker(int threadCount) {
        poolCount.incrementAndGet();
        this.tasks = new SimpleBlockingQueue<>(8);
        this.execute = new AtomicBoolean(true);
        this.threads = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            JobWorker thread = new JobWorker(
                    "ThreadPool-" + poolCount.get() + "-Thread-" + i,
                    this.execute, this.tasks);
            thread.start();
            this.threads.add(thread);
        }
    }

    public static ThreadPoolWorker getInstance() {
        return getInstance(Runtime.getRuntime().availableProcessors());
    }

    public static ThreadPoolWorker getInstance(int threadCount) {
        return new ThreadPoolWorker(threadCount);
    }


    public void work(Runnable job) {
        if (this.execute.get()) {
            try {
                tasks.offer(job);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Останавливает добавление новых запускаемых файлов в пул потоков и
     * завершает работу пула после выполнения всех запускаемых модулей в
     * очереди.
     */
    public synchronized void stop() {
        tasks.clear();
        shutdown();
    }

    /**
     * Метод устанавливает execute флаг в false.
     * Работа в пуле потоков продолжается до тех пор,
     * пока очередь имеет объекты runnables.
     * <p>
     * Упорядоченное завершение работы, при котором ранее
     * отправленные задачи выполняются, но новые задачи
     * не принимаются.
     */
    public synchronized void shutdown() {
        execute.set(false);
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}