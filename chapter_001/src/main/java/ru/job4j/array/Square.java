package ru.job4j.array;

public class Square {
    public int[] calculate(int bound) {
        int[] rst = new int[bound];
        //заполняем массив через цикл элементами от 1 до bound возведенными в квадрат
        for (int index = 0; index < bound; index++) {
            rst[index] = (index + 1) * (index + 1);
        }
        return rst;
    }
}
