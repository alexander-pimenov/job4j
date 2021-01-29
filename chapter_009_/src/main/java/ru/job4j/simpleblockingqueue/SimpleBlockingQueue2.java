package ru.job4j.simpleblockingqueue;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.LinkedList;
import java.util.Queue;

/*
 * В классе добавлены логи в виде  System.out.println(...)
 * только для наглядности происходящего в методах класса
 * SimpleBlockingQueue2<T>. Т.е. когда добавляем элемент в
 * очередь или забираем элемент из очереди, то выводится
 * сообщение об элементе и количестве элементов в очереди.
 * В них смысла нет, за этими логами теряется смысл
 * самой программы, поэтому в SimpleBlockingQueue<T> они
 * опущенны.
 */
@ThreadSafe
public class SimpleBlockingQueue2<T> {
    @GuardedBy("lock")
    private final Object lock = this;

    private Queue<T> queue = new LinkedList<>();

    private final int limitBound;

    public SimpleBlockingQueue2(int limitBound) {
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

    public int size() {
        synchronized (lock) {
            return queue.size();
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
