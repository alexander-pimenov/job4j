package ru.job4j.search;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));//был
        queue.put(new Task("low-low", 6));
        queue.put(new Task("urgent", 1));//был
        queue.put(new Task("middle", 3));//был
        queue.put(new Task("up-middle", 2));
        queue.put(new Task("low-middle", 4));
        Task result = queue.take();

        assertThat(result.getDesc(), is("urgent"));
    }
}
