package ru.job4j.synch.locklist;

import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SingleLockListTest {

    @Test
    public void add() throws InterruptedException {
        SingleLockList<Integer> list = new SingleLockList<>();
        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(() -> list.add(2));
        first.start();
        second.start();
        first.join();
        second.join();
        Set<Integer> rsl = new TreeSet<>();
        list.iterator().forEachRemaining(rsl::add);
        assertThat(rsl, is(Set.of(1, 2)));
    }

    @Test
    public void addMultipleObjects() throws InterruptedException {
        SingleLockList<Integer> list = new SingleLockList<>();
        Thread th1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                list.add(1);
            }
            System.out.println(list);
        });
        Thread th2 = new Thread(() -> {
            for (int i = 0; i < 4; i++) {
                list.add(2);
            }
            System.out.println(list);
        });
        Thread th3 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                list.add(3);
            }
            System.out.println(list);
        });
        th1.start();
        th2.start();
        th3.start();
        th1.join();
        th2.join();
        th3.join();
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
    }
}