package ru.job4j.loop;


public class Factorial {
    //В качестве аргумента в метод приходит положительное целое число n,
    // сам метод должен возвращать рассчитанный факториал для этого числа.
    public int calc(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }
}
