package ru.job4j.basepatterns.behavioral.strategy.example2;

import java.util.Arrays;

/**
 * Inserting sorting strategy (Сортировка вставками)
 * третья стратегия сортировки
 */
public class InsertingSort implements Sorting {
    @Override
    public void sort(int[] arr) {
        System.out.println("Сортировка вставками");
        System.out.println("до:\t" + Arrays.toString(arr));
        for (int barier = 1; barier < arr.length; barier++) {
            int index = barier;
            while (index - 1 >= 0 && arr[index] < arr[index - 1]) {
                int tmp = arr[index];
                arr[index] = arr[index - 1];
                arr[index - 1] = tmp;
                index--;
            }
        }
        System.out.println("после:\t" + Arrays.toString(arr));
    }
}
