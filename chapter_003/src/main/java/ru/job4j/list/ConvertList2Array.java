package ru.job4j.list;

import java.util.List;

public class ConvertList2Array {
    public static int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size() % rows > 0 ? list.size() / rows + 1 : list.size() / rows;

        int[][] array = new int[rows][cells];
        int row = 0;
        int cell = 0;

        for (Integer element : list) {
            array[row][cell++] = element;
            if (cell >= cells) {
                cell = 0;
                row++;
            }
        }
        return array;
    }

//    Для проверки использовал не только Test, но и main метод
//    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
//
//        System.out.println("Начальный список:");
//        System.out.println(list);
//
//        int[][] twoDimArrayFromList = ConvertList2Array.toArray(list, 3);
//        System.out.println("Конечный двумерный массив:");
//        System.out.println(Arrays.deepToString(twoDimArrayFromList));
//
//
//    }
}

//Начальный список:
//[1, 2, 3, 4, 5, 6, 7]
//Конечный двумерный массив:
//[[1, 2, 3], [4, 5, 6], [7, 0, 0]]