package ru.job4j.loop;


//вычисляет сумму чётныx чисел в диапазоне от start до finish
public class Counter {

    public int add(int start, int finish) {
        int sum = 0;
        for (int i = 0; i <= 10; i++) {
            if ((i % 2) == 0) {
                sum = sum + i;
            }
        }
        return sum;
    }
}
