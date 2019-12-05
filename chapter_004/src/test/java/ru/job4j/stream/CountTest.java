package ru.job4j.stream;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class CountTest {

    /*Список целых чисел. Из списка выбираются только четные,
     * затем их возводят в квадрат, и затем полуют сумму квадратов.
     * 1,2,3,4,5 => 2,4 => 2*2+4*4=20
     */
    private final List<Integer> data = List.of(1, 2, 3, 4, 5);

    @Test
    public void whenCountMethod1() {
        Count count = new Count();
        int expected = 20;
        int result = count.count1(data);
        assertThat(result, is(expected));
    }

    @Test
    public void whenCountMethod2() {
        Count count = new Count();
        int expected = 20;
        int result = count.count2(data);
        assertThat(result, is(expected));
    }

    @Test
    public void whenCountMethod3() {
        Count count = new Count();
        int expected = 20;
        int result = count.count3(data);
        assertThat(result, is(expected));
    }

}