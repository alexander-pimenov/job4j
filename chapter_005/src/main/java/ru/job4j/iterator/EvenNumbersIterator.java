package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Создаем итератор возвращающий только четные цифры.
 * Итератор может принимать список произвольных чисел.
 */


public class EvenNumbersIterator implements Iterator {
    private final int[] values;
    private int index = 0;

    public EvenNumbersIterator(int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        while (index < values.length) {
            if (values[index] % 2 == 0) {
                result = true;
                break;
            }
            index++;
        }
        return result;
    }
/*
    //В этом методе используется цикл while(),
    //как его избежать, смотри код метода next() ниже.

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while (values[index] % 2 != 0) {
            index++;
        }
        return values[index++];
    }
*/

    //Чтобы избежать цикла while() в методе next(),
    //перепишем код:
    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
//        Этот код еще упрощаем. В одну строку.
//        var result = values[index];
//        index++;
//        return result;
        //Сначала считает index, потом получит значение,
        //а потом уже увеличит на 1.
        return values[index++];
    }
}
