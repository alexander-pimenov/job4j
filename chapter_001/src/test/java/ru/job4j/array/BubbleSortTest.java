package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {
    /*
     * здесь тест, проверяющий сортировку массива из 10 элементов методом пузырька,
     * например {1, 5, 4, 2, 3, 1, 7, 8, 0, 5}.
     * */
    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {
        BubbleSort sorter = new BubbleSort();
        int[] input = new int[]{1, 5, 4, 2, 3, 1, 7, 8, 0, 5};
        int[] result = sorter.sort(input);
        int[] expect = new int[]{0, 1, 1, 2, 3, 4, 5, 5, 7, 8};
        assertThat(result, is(expect));
    }

    /*
     * здесь тест, проверяющий сортировку массива из 0 элементов методом пузырька,
     * например {}.
     * */
    @Test
    public void whenSortArrayWithZeroElementsThenSortedArray() {
        BubbleSort sorter = new BubbleSort();
        int[] input = new int[]{};
        int[] result = sorter.sort(input);
        int[] expect = new int[]{};
        assertThat(result, is(expect));
    }

    /*
     * здесь тест, проверяющий сортировку массива из 5 одинаковых элементов методом пузырька,
     * например {2,2,2,2,2}.
     * */
    @Test
    public void whenSortArrayWithAllElementsEqualThenSortedArray() {
        BubbleSort sorter = new BubbleSort();
        int[] input = new int[]{2, 2, 2, 2, 2};
        int[] result = sorter.sort(input);
        int[] expect = new int[]{2, 2, 2, 2, 2};
        assertThat(result, is(expect));
    }
}
