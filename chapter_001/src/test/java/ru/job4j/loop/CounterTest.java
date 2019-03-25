package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CounterTest {
    @Test
    public void whenSumEvenNumbersFromOneToTenThenThirty() {
        //тест, проверяющий, что сумма чётных чисел от 1 до 10
        // при вызове метода counter.add будет равна 30.
        Counter counter = new Counter();
        int add = counter.add(1, 10);
        assertThat(add, is(30));
        //System.out.println("Сумма чётных чисел =" + add);
    }
}

