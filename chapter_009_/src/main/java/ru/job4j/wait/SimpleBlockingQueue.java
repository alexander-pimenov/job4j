package ru.job4j.wait;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Класс блокирующей очереди.
 *
 * @param <T> параметризованный типом дженериком T.
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    //Создадим обычную очередь для работы с ней.
    private Queue<T> queue = new LinkedList<>();

    //Создадим объект для синхронизации
    @GuardedBy("lock")
    private final Object lock = new Object();

    //максимальное количество элементов в очереди
    private final int limitBound;

    public SimpleBlockingQueue(int limitBound) {
        this.limitBound = limitBound;
    }

    /**
     * Заполняем очередь.
     *
     * @param value значение.
     */
    public void offer(T value) throws InterruptedException {
        synchronized (lock) {
            while (queue.size() == limitBound) {
                System.out.println(String.format("%s waiting...", Thread.currentThread().getName()));
                lock.wait();
            }
            queue.offer(value);
            lock.notifyAll();
        }
    }


    /**
     * Берем элемент из очереди.
     *
     * @return значение.
     */
    public T poll() throws InterruptedException {
        synchronized (lock) {
            T value = null;
            while (queue.size() == 0) {
                System.out.println(String.format("%s waiting...", Thread.currentThread().getName()));
                lock.wait();
            }
            value = queue.poll();
            lock.notifyAll();
            return value;
        }
    }

    public int size() {
        synchronized (lock) {
            return queue.size();
        }
    }

    public boolean isEmpty() {
        synchronized (lock) {
            return queue.isEmpty();
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
