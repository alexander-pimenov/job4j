package ru.job4j.array;

import java.util.Arrays;

/**
 * Removing duplicates;
 *
 */
public class ArrayDuplicate {

    public String[] remove(String[] array) {
        int uniqe = array.length;
        for (int out = 0; out < uniqe; out++) {
            for (int in = out + 1; in < uniqe; in++) {
                if (array[out].equals(array[in])) {
                    array[in] = array[--uniqe];
                    in--;
                }
            }
        }
        return Arrays.copyOf(array, uniqe);
    }
}
