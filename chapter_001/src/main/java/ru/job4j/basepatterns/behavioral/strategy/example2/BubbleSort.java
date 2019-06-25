package ru.job4j.basepatterns.behavioral.strategy.example2;

import java.util.Arrays;

/**
 * Bubble sorting strategy (сортировка пузырьком). Первый алгоритм сортировки
 * реализуем первую стратегию сортировки
 */
public class BubbleSort implements Sorting {
    @Override
    public void sort(int[] arr) {
        System.out.println("Сортировка пузырьком");
        System.out.println("до:\t" + Arrays.toString(arr)); //Arrays.toString удобно располагает наш массив в виде строки
        for (int barier = arr.length - 1; barier >= 0; barier--) {
            for (int i = 0; i < barier; i++) {
                if (arr[i] > arr[i + 1]) {
                    int tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;
                }
            }
        }
        System.out.println("после:\t" + Arrays.toString(arr));
    }
}

