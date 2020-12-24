package ru.job4j.nonblocking;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount<T> {
    //new AtomicReference<Integer>(0) - нужно инициализировать, указав initialValue=0
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        Integer expectedValue;
        Integer newValue;
        do {
            expectedValue = count.get();
            newValue = expectedValue + 1;
        } while (!count.compareAndSet(expectedValue, newValue));
    }

    public int get() {
        return count.get();
    }
}
