package ru.job4j.coffee;

import java.util.Arrays;

public class CoffeeMachine {

    int[] changes(int value, int price) {
        int change = value - price;
        int[] coins = {10, 5, 2, 1};
        int count = 0;
        int[] array = new int[change];
        for (int coin : coins) {
            while (change >= coin) {
                change = change - coin;
                array[count++] = coin;
            }
        }
        return Arrays.copyOf(array, count);
    }
}
