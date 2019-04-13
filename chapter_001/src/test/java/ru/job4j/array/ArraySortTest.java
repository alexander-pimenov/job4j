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

    @Test
    /*
     * */
    public void whenSortedArrayPlusOtherSortedArraysGetThirdSortedArray3() {
        int[] a = {1, 1, 1, 1};
        int[] b = {2, 2, 2, 2};
        int[] expect = {1, 1, 1, 1, 2, 2, 2, 2};
        ArraySort newAr = new ArraySort();
        int[] result = newAr.merge(a, b);
        assertThat(result, is(expect));
    }
    /*
     * здесь тест проверяет слияние двух отсортированных массивов
     * первый массив меньше второго
     * */
    @Test
    public void whenSortedArrayPlusOtherSortedArraysGetThirdSortedArray4() {
        int[] a = {1, 3, 5};
        int[] b = {2, 4, 6, 8};
        int[] expect = {1, 2, 3, 4, 5, 6, 8};
        ArraySort newAr = new ArraySort();
        int[] result = newAr.merge(a, b);
        assertThat(result, is(expect));
    }
    /*
     * здесь тест проверяет слияние двух отсортированных массивов
     * первый массив больше второго
     * */
    @Test
    public void whenSortedArrayPlusOtherSortedArraysGetThirdSortedArray5() {
        int[] a = {1, 3, 5, 7, 9};
        int[] b = {2, 4, 6};
        int[] expect = {1, 2, 3, 4, 5, 6, 7, 9};
        ArraySort newAr = new ArraySort();
        int[] result = newAr.merge(a, b);
        assertThat(result, is(expect));
    }
    /*
    * здесь тест проверяет слияние двух пустых массивов
    * */
    @Test
    public void whenSortedArrayPlusOtherSortedArraysGetThirdSortedArray6() {
        int[] a = {};
        int[] b = {};
        int[] expect = {};
        ArraySort newAr = new ArraySort();
        int[] result = newAr.merge(a, b);
        assertThat(result, is(expect));
    }
}
