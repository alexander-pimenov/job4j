package ru.job4j.wait;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleBlockingQueueTest {
    private static final int BOUND = 5;
    private final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(BOUND);
    private List<Integer> result = new ArrayList<>();

    @Test
    public void whenAddTenNumbersAndGetSizeQueueAfterConsumer() {
        Thread producer = new Thread(
                () -> {
                    IntStream.range(0, 10).forEach(
                            queue::offer);
                }, "Producer"
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {

                    try {
                        while (queue.size() > 0 || !Thread.currentThread().isInterrupted()) {
                            if (queue.size() > 0) {
                                result.add(queue.poll());
                            }
                        }
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    }
                }, "Consumer"
        );
        consumer.start();
        try {
            producer.join();
            Thread.sleep(2000);
            consumer.interrupt();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(queue.size(), is(0));
        assertThat(result.size(), is(10));
        assertThat(result, is(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }

    @Test
    public void whenAdd10ElementsThemGetSizeWillGetSize5() {
        Thread producer = new Thread(
                () -> {
                    try {
                        IntStream.range(0, 10).forEach(
                                queue::offer);
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    }
                }, "Producer"
        );
        producer.start();
        try {
            Thread.sleep(2000);
            producer.interrupt();
            producer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Queue size is " + queue.size());
        assertThat(queue.size(), is(5));
    }
}