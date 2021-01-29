package ru.job4j.pool.threadpoolimproved;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleThreadpool {
    // Количество созданных пулов потоков
    private static AtomicInteger poolCount = new AtomicInteger(0);

    // Очередь запускаемых файлов (Queue of runnables)
    private ConcurrentLinkedQueue<Runnable> runnables;

    // Флаг для управления объектами SimpleThreadpoolThread
    private AtomicBoolean execute;

    // Содержит «пул» потоков
    private List<SimpleThreadpoolThread> threads;

    /**
     * Частный конструктор для управления созданием пулов потоков. Увеличивает
     * счетчик пула всякий раз, когда создается новый пул.
     *
     * @param threadCount Количество SimpleThreadpoolThreads для добавления в пул.
     */
    private SimpleThreadpool(int threadCount) {
        // Increment pool count
        poolCount.incrementAndGet();
        this.runnables = new ConcurrentLinkedQueue<>();
        this.execute = new AtomicBoolean(true);
        this.threads = new ArrayList<>();
        for (int threadIndex = 0; threadIndex < threadCount; threadIndex++) {
            SimpleThreadpoolThread thread = new SimpleThreadpoolThread("SimpleThreadpool-" + poolCount.get() + "-Thread-" + threadIndex, this.execute, this.runnables);
            thread.start();
            this.threads.add(thread);
        }
    }

    /**
     * Получает новый экземпляр пула потоков с количеством потоков,
     * равным количеству доступных процессоров (или потоков ЦП)
     *
     * @return new SimpleThreadpool
     */
    public static SimpleThreadpool getInstance() {
        return getInstance(Runtime.getRuntime().availableProcessors());
    }

    /**
     * Получает новый экземпляр пула потоков с указанным количеством потоков.
     *
     * @param threadCount Потоки для добавления в пул.
     * @return new SimpleThreadpool
     */
    public static SimpleThreadpool getInstance(int threadCount) {
        return new SimpleThreadpool(threadCount);
    }

    /**
     * Добавляет запускаемый объект в очередь на обработку
     *
     * @param runnable Runnable to be added to the pool
     */
    public void execute(Runnable runnable) {
        if (this.execute.get()) {

            runnables.add(runnable);
        } else {
            throw new IllegalStateException("Threadpool terminating, unable to execute runnable");
        }
    }

    /**
     * Ожидает таймаута timeout мс до завершения потоков в пуле потоков.
     *
     * @param timeout Timeout в milliseconds
     * @throws TimeoutException      Выбрасывается, если завершение занимает больше времени, чем тайм-аут.
     * @throws IllegalStateException Вызывается, если методы stop () или terminate () не были вызваны до ожидания.
     */
    public void awaitTermination(long timeout) throws TimeoutException {
        if (this.execute.get()) {
            throw new IllegalStateException("Threadpool not terminated before awaiting termination");
        }
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime <= timeout) {
            boolean flag = true;
            for (Thread thread : threads) {
                if (thread.isAlive()) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new ThreadpoolException(e);
            }
        }
        throw new TimeoutException("Unable to terminate threadpool within the specified timeout (" + timeout + "ms)");
    }

    /**
     * Ожидает завершения потоков в пуле потоков бесконечно.
     *
     * @throws IllegalStateException Вызывается, если методы stop() или terminate() не были вызваны до ожидания.
     */
    public void awaitTermination() throws TimeoutException {
        if (this.execute.get()) {
            throw new IllegalStateException("Threadpool not terminated before awaiting termination");
        }
        while (true) {
            boolean flag = true;
            for (Thread thread : threads) {
                if (thread.isAlive()) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new ThreadpoolException(e);
            }
        }
    }

    /**
     * У нас есть два способа остановить Threadpool - terminate() или shutdown().
     * <p>
     * Метод terminate() очищает очередь исполняемых файлов и останавливает
     * пул потоков. Все выполняемые в данный момент исполняемые файлы продолжат
     * выполнение.
     * <p>
     * Метод terminate() является более резким, чем метод shutdown(), так как он
     * очищает очередь, а затем вызывает shutdown(). Если есть какие-либо
     * выполняющиеся Runnables, они продолжают выполняться до завершения,
     * но ожидающие runnables не выполняются.
     */
    public void terminate() {
        runnables.clear();
        shutdown();
    }

    /**
     * Метод shutdown() останавливает добавление новых запускаемых файлов в
     * пул потоков и завершает работу пула после выполнения всех запускаемых
     * модулей в очереди.
     * <p>
     * Метод shutdown() устанавливает execute флаг в false, и Threadpool
     * продолжается до тех пор, пока очередь имеет объекты runnables.
     */
    public void shutdown() {
        execute.set(false);
    }
}
