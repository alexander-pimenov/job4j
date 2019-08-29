package ru.job4j.coffee;

import java.util.Arrays;

public class CoffeeMachine {

    int[] changes(int value, int price) {
        int change = value - price;
        int[] coins = {10, 5, 2, 1};
        int count = 0;
        int[] array = new int[change]; //размер массива [change], чтоб не выйти за пределы массива
        for (int coin : coins) {
            while (change >= coin) {
                change = change - coin;
                array[count++] = coin;
            }
        }
        //необходимо преобразовать в массив нужной дляины,
        //путем копирования array[change] в array[count], чтобы не выводить лишние нулевые элементы
        return Arrays.copyOf(array, count);
        //return array;
    }
}

//Вывод на эран:
// return array; -> [10, 10, 5, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
// return Arrays.copyOf(array, count); -> [10, 10, 5, 2, 2]
