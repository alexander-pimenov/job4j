package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс IteratorOfIteratorsConverter с методом converter()
 * для преобразования Итератора итераторов в Итератор чисел,
 * т.е. на вход метод получает объект Итератор итераторов
 * и возвращает Итератор чисел.
 * Например,
 * На вход подаем Iterator<Iterator<Integer>> - ((4 2 0 4 6 4 9),(0 9 8 7 5),(1 3 5 6 7 0 9 8 4))
 * Метод возвращает Iterator<Integer> - (4 2 0 4 6 4 9 0 9 8 7 5 1 3 5 6 7 0 9 8 4)
 */


public class IteratorOfIteratorsConverter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Integer> inner = it.next();

            @Override
            public boolean hasNext() {
                if (inner == null) {
                    return false;
                }
                while (!inner.hasNext() && it.hasNext()) {
                    inner = it.next();
                }
                return inner.hasNext();
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
//                Этот блок можно убрать.
//                if (inner.hasNext()) {
//                    return inner.next();
//                } else {
//                    inner = it.next();
//                    return inner.next();
//                }
                return inner.next();
            }
        };
    }
}
