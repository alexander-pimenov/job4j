package ru.job4j.pool;

import ru.job4j.wait.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {

    //количество ядер в системе
    private final int poolSize = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    //устанавливаем размер внутренней блокирующей очереди равной 8
    //от этого будет зависеть как долго будут ждать задания добавления в очередь
    //нашего ThreadPool, в состоянии ожидания будет находиться поток main.
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(8);

    //Инициализация пула должна быть по количеству ядер в системе.
    public ThreadPool() {
        for (int i = 0; i < poolSize; i++) {
            Thread thread = new Thread(
                    () -> {
                        while (!Thread.currentThread().isInterrupted()) {
                            try {
                                tasks.poll().run();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    });
            threads.add(thread);
            thread.start();
        }
    }

    public void work(Runnable job) {
        try {
            tasks.offer(job);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* Инициирует упорядоченное завершение работы, при котором ранее
     * отправленные задачи выполняются, но новые задачи не принимаются.
     * */
    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}