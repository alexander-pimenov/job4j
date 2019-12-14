package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Создание итератора для двухмерного массива.
 * Корректно работает не только с квадратной матрицей,
 * но и с jagged array.
 *
 * @author Pimenov Alex
 * @since 14.12.2019
 */

public class MatrixIterator implements Iterator {

    //Входящий массив
    private final int[][] matrix;
    //Индекс строк (внешний)
    private int indexOut = 0;
    //Индекс столбцов (внутренний)
    private int indexIn = 0;

    MatrixIterator(int[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public boolean hasNext() {
        return matrix.length > indexOut && matrix[indexOut].length > indexIn;
    }

    @Override
    public Object next() {
        int result;
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else {
            //Передвигаем курсор
            result = matrix[indexOut][indexIn++];
            if (indexIn > matrix[indexOut].length - 1) {
                indexOut++;
                indexIn = 0;
            }
            return result;
        }
    }
}
