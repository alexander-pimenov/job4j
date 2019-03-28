package ru.job4j.array;

import org.junit.Test;

//import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TurnTest {
    @Test
    public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
        //здесь тест, проверяющий переворот массива с чётным числом элементов, например {4, 1, 6, 2}.
        Turn turner = new Turn();
        int[] input = new int[]{4, 1, 6, 2};
        int[] result = turner.back(input);
        int[] expect = new int[]{2, 6, 1, 4};
        assertThat(result, is(expect));
        //System.out.println(Arrays.toString(result));
    }

    @Test
    public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray() {
        //здесь тест, проверяющий переворот массива с нечётным числом элементов, например {1, 2, 3, 4, 5}.
        Turn turner = new Turn();
        int[] input = new int[]{1, 2, 3, 4, 5};
        int[] result = turner.back(input);
        int[] expect = new int[]{5, 4, 3, 2, 1};
        assertThat(result, is(expect));
        //System.out.println(Arrays.toString(result));
    }
}
