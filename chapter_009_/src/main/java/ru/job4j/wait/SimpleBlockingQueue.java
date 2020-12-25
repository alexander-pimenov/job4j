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
//                while (queue.isEmpty()) {
                    System.out.println(String.format("%s waiting...", Thread.currentThread().getName()));
                    lock.wait();
                }
                value = queue.poll();
                lock.notifyAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return value;
        }
    }

    public int size() {
        synchronized (lock) {
            return queue.size();
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }


    @Override
    public String toString() {
        return queue.toString();
    }
}
