package ru.job4j.synch.locklist;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.DynamicSimpleArrayList;

import java.util.Iterator;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    @GuardedBy("this")
    private final DynamicSimpleArrayList<T> list = new DynamicSimpleArrayList<>();

    public synchronized void add(T value) {
        list.add(value);
    }

    public synchronized T get(int index) {
        return list.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy().iterator();
    }

    private synchronized DynamicSimpleArrayList<T> copy() {
        DynamicSimpleArrayList<T> copyContainer = new DynamicSimpleArrayList<>();

        for (T value : list) {
            copyContainer.add(value);
        }
        return copyContainer;
    }

    @Override
    public synchronized String toString() {
        return "SingleLockList{" +
                "list=" + list +
                '}';
    }
}
