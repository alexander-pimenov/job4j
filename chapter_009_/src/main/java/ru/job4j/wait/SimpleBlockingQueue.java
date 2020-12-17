package ru.job4j.wait;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("lock")
    private final Object lock = this;

    private Queue<T> queue = new LinkedList<>();

    private final int limitBound;

    public SimpleBlockingQueue(int limitBound) {
        this.limitBound = limitBound;
    }

    /**
     * Заполняем очередь.
     *
     * @param value значение.
     */
    public void offer(T value) {
        synchronized (lock) {
            try {
                while (queue.size() == limitBound) {
                    System.out.println(String.format("%s waiting...", Thread.currentThread().getName()));
                    lock.wait();
                }
                queue.offer(value);
                System.out.println(String.format("%s put element \"%s\" to the queue. Queue size is %d",
                        Thread.currentThread().getName(), value, queue.size()));
                lock.notifyAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Берем элемент из очереди.
     *
     * @return значение.
     */
    public T poll() {
        synchronized (lock) {
            T value = null;
            try {
                while (queue.size() == 0) {
                    System.out.println(String.format("%s waiting...", Thread.currentThread().getName()));
                    lock.wait();
                }
                value = queue.poll();
                System.out.println(String.format("%s took element \"%s\" from  the queue. Queue size is %d",
                        Thread.currentThread().getName(), value, queue.size()));
                lock.notifyAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return value;
        }
    }
}
