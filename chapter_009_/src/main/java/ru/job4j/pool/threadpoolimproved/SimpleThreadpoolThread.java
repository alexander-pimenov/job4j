package ru.job4j.pool.threadpoolimproved;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleThreadpoolThread extends Thread {
    // Этот флаг используется для управления выполнением while цикла внутри run() метода.
    // Если execute флаг установлен false, а очередь пуста, поток останавливается.
    private AtomicBoolean execute;

    // Это очередь, в которой хранятся исполняемые файлы.
    private ConcurrentLinkedQueue<Runnable> runnables;

    public SimpleThreadpoolThread(String name, AtomicBoolean execute, ConcurrentLinkedQueue<Runnable> runnables) {
        super(name);
        this.execute = execute;
        this.runnables = runnables;
    }

    @Override
    public void run() {
        try {
            // Продолжить выполнение, когда установлен флаг выполнения или когда в очереди есть исполняемые файлы
            while (execute.get() || !runnables.isEmpty()) {
                Runnable runnable;
                // Взять запускаемый объект из очереди и выполнить его
                while ((runnable = runnables.poll()) != null) {
                    runnable.run();
                }
                // Сон на случай, если в очереди не было запускаемых файлов. Это помогает избежать перегрузки процессора.
                // Thread.sleep(1) во внешнем цикле. Это сделано для того, чтобы поток не
                // вел себя как бесконечный цикл и не перегружал весь доступный ему ЦП.
                // Цикл входит в эту строку только тогда, когда очередь пуста, что не
                // должно происходить очень часто в хорошо оптимизированной системе.
                Thread.sleep(1);
            }
        } catch (RuntimeException | InterruptedException e) {
            throw new ThreadpoolException(e);
        }
    }
}
