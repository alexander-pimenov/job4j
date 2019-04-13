package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArraySortTest {
    /*
     * здесь тест проверяет слияние двух отсортированных массивов из 2 элементов
     * получаем третий отсортированный массив из 4 элементов
     * */
    @Test
    public void whenSortedArrayPlusOtherSortedArraysGetThirdSortedArray1() {
        int[] a = {1, 3};
        int[] b = {2, 4};
        int[] expect = {1, 2, 3, 4};
        ArraySort newAr = new ArraySort();
        int[] result = newAr.merge(a, b);
        assertThat(result, is(expect));
    }

    @Test
    /*
     * здесь тест проверяет слияние двух отсортированных массивов из 4 элементов
     * получаем третий отсортированный массив из 8 элементов
     * */
    public void whenSortedArrayPlusOtherSortedArraysGetThirdSortedArray2() {
        int[] a = {1, 3, 5, 7};
        int[] b = {2, 4, 6, 8};
        int[] expect = {1, 2, 3, 4, 5, 6, 7, 8};
        ArraySort newAr = new ArraySort();
        int[] result = newAr.merge(a, b);
        assertThat(result, is(expect));
    }
}
