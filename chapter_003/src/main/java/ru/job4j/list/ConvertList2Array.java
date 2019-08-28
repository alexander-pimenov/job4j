package ru.job4j.list;

/*Преобразование списка в двумерный массив, с количеством строк rows
 * и солбцов cells*/

import java.util.ArrayList;
import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
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

    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();

        for (int[] array : list) {
            for (int element : array) {
                result.add(element);
            }
        }
        return result;
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
//        int[] array1 = new int[]{1,2,3,4};
//        int[] array2 = new int[]{5,6};
//        int[] array3 = new int[]{7,8,9};
//
//        List<int[]> list2 = new ArrayList<>();
//        list2.add(array1);
//        list2.add(array2);
//        list2.add(array3);
//
//        System.out.println("Объединеные и конвертируемые в список массивы:");
//        List<Integer> result = ConvertList2Array.convert(list2);
//        System.out.println(result);
//    }
}

//Начальный список:
//[1, 2, 3, 4, 5, 6, 7]
//Конечный двумерный массив:
//[[1, 2, 3], [4, 5, 6], [7, 0, 0]]
//Объединеные и конвертируемые в список массивы:
//[1, 2, 3, 4, 5, 6, 7, 8, 9]