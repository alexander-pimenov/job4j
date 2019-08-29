package ru.job4j.coffee;

import java.util.Arrays;

public class RunCoffeeMachine {
    public static void main(String[] args) {
        CoffeeMachine cm = new CoffeeMachine();

        //переменная result для вывода результата
        int[] result = new int[0];
        //в параметрах cm.changes() сначала вводим value(купюру), а затем price(стоимость кофе)
        //иначе получим NegativeArraySizeException
        try {
            result = cm.changes(64, 35);
        } catch (NegativeArraySizeException e) {
            System.out.println("Не достаточно денег");
        }
        System.out.println(Arrays.toString(result));
    }
}
