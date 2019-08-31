package ru.job4j.array;

/*
 * метод проверяющий на однотипность элементов массива
 *
 * */
public class Check {
    public boolean mono(boolean[] data) {
        boolean result = true;
        for (int i = 0; i != data.length; i++) {

            if (data[i] != data[data.length - 1]) {
                result = false;
                break;
            }
        }
        return result;
    }
}

