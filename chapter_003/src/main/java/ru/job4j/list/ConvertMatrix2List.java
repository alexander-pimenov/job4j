package ru.job4j.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {

        List<Integer> list = new ArrayList<>();

        for (int[] ints : array) {
            for (int anInt : ints) {
                list.add(anInt);
            }
        }
        return list;

        //С помощью обычного for:
//        for (int i = 0; i < array.length; i++) {
//            for (int j = 0; j < array[i].length; j++) {
//                list.add(array[i][j]);
//            }
//        }
    }

    //main метод использовал для проверки
    public static void main(String[] args) {

        int[][] inputMatrixArray = {
                {1, 2, 3},
                {3, 4, 5},
                {5, 6, 7}
        };

        //показать, что имеем
        System.out.println(Arrays.deepToString(inputMatrixArray));

        ConvertMatrix2List list = new ConvertMatrix2List();
        List<Integer> listFromMatrixArray = list.toList(inputMatrixArray);

        //показать результат
        System.out.println(listFromMatrixArray);

    }
}

