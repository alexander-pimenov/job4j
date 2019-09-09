package ru.job4j.comparator;

import java.util.Comparator;

/*
 * Необходимо реализовать поэлементное сравнение двух списков,
 * т.е. сравниваем элементы двух списков, находящихся на одних
 * и тех же позициях (по одним и тем же индексом). Сравнение
 * в лексикографическом порядке.
 */

public class ListCompare implements Comparator<String> {

    @Override
    public int compare(String left, String right) {

        int result = 0;
        int lim = Math.min(left.length(), right.length());
        for (int index = 0; index < lim; index++) {
            result = Character.compare(left.charAt(index), right.charAt(index));

            //тут достаточно проверить, что результат не 0 и сделать break
            if (result != 0) {
                break;
            }

//            if (result < 0) {
//                result = -1;
//                break;
//            }
//            if (result > 0) {
//                result = 1;
//                break;
//            }
        }
        if (result == 0) {
            result = Integer.compare(left.length(), right.length());
        }
        return result;
    }
}
