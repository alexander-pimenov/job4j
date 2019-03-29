package ru.job4j.array;

public class BubbleSort {
    /*
    * Внешний цикл каждый раз сокращает массив,
    * так как внутренний цикл каждый раз ставит
    * в конец максимальный элемент.
    *
    * Оператором if сравниваем элементы попарно,
    * если они имеют неправильный порядок,
    * то меняем местами
    *
    * */
    public int[] sort(int[] array){
        for(int i = array.length-1 ; i > 0 ; i--){
            for(int j = 0 ; j < i ; j++){
            /**/
            if( array[j] > array[j+1] ){
                int tmp = array[j];
                array[j] = array[j+1];
                array[j+1] = tmp;
            }
        }
    }
        return array;
    }
}
