package ru.job4j.list;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleQueueTest {

    @Test
    public void whenPushThreeElementsShouldSizeThree() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(10);
        queue.push(20);
        queue.push(30);

        assertThat(queue.size(), is(3));
    }

    @Test
    public void whenFirstInThenFirstOut() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(10);
        queue.push(20);
        queue.push(30);

        assertThat(queue.poll(), is(10));
        assertThat(queue.poll(), is(20));
        assertThat(queue.poll(), is(30));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyQueueAndPollThenException() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(10);
        queue.poll();
        queue.poll();
    }

    @Test
    public void whenFirstPushThenPollAndAgainPushAndPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(10);
        queue.push(20);
        assertThat(queue.poll(), is(10));

        queue.push(30);
        queue.push(40);
        assertThat(queue.poll(), is(20));
        assertThat(queue.poll(), is(30));

        assertThat(queue.size(), is(1));
    }
}