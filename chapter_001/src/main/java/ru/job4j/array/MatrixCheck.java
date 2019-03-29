package ru.job4j.array;
/*
* Метод проверяет, что все элементы по диагоналям равны true или false,
* т.е. однотипные. И возвращает результат true, если так и есть.
* */
public class MatrixCheck {
    public boolean mono(boolean[][] data) {
        boolean result = true;
        for (int i = 0; i < data.length; i++) {
            if (data[0][0] != data[i][i]) {
                result = false;
                break;
            }
            if (data[0][data.length - 1] != data[i][data.length - 1 - i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
