package ru.job4j.array;

public class Turn {
    //Метод должен перевернуть массив задом наперёд.
    public int[] back(int[] array) {
        //управление циклом осуществляется одновременно двумя переменными start и end
        for (int start = 0, end = array.length - 1; start <= end; start++, end--) {
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
        }
        return array;
    }
}
