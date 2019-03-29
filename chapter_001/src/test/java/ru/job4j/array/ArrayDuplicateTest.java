package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Pimenov (pimalex1978@yandex.ru)
 * @since 0.1
 */

public class ArrayDuplicateTest {
/*
* тест, проверяющий удаление дубликатов строк из массива строк.
* с помощью arrayContainingInAnyOrder() проверяется отсутствие дубликатов, но не их порядок расположения в массиве
* */
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        String[] input = {"Привет", "Мир", "Привет", "Супер", "Мир", "Супер", "Мир"};
        String[] expect = {"Мир", "Привет", "Супер"};
        ArrayDuplicate duplicate = new ArrayDuplicate();
        String[] result = duplicate.remove(input);
        assertThat(result, arrayContainingInAnyOrder(expect));
    }

    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicateSample2() {
        String[] input = {"Мир", "Мир", "Мир", "Мир", "Мир", "Мир", "Мир"};
        String[] expect = {"Мир"};
        ArrayDuplicate duplicate = new ArrayDuplicate();
        String[] result = duplicate.remove(input);
        assertThat(result, arrayContainingInAnyOrder(expect));
    }
}
