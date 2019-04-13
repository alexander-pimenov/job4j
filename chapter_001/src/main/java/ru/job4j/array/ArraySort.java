package ru.job4j.array;

public class ArraySort {
    /*
     * Метод, который два отсортированных массива объединяет в третий отсортированный массив.
     * Работает верно, если исходники отсортированны.
     * (если исходные массивы не отсортированы, то в результирующем третьем массиве числа не будут отсортированы) !!!
      * Нужно искать другой вариант.
     * */
    public int[] merge(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int aIndex = 0;
        int bIndex = 0;

        for (int i = 0; i < result.length; i++) {
            if (aIndex < a.length && bIndex < b.length) {
                if (a[aIndex] < b[bIndex]) {
                    result[i] = a[aIndex];
                    aIndex++;
                } else {
                    result[i] = b[bIndex];
                    bIndex++;
                }
            } else if (aIndex < a.length) {
                result[i] = a[aIndex];
                aIndex++;
            } else {
                result[i] = b[bIndex];
                bIndex++;
            }
        }

        return result;
    }
}

/*
* public class ArraySample {
 public static void main(String[] args) {
  int[] a = {1, 3};
  int[] b = {2, 4};
  ArraySample newAr = new ArraySample();
System.out.println(Arrays.toString(newAr.merge(a,b))); // выводим новый массив на экран
}
public int[] merge(int[] a, int[] b) {
    int[] result = new int[a.length + b.length]; //создаем новый массив по размеру объединяя два имеющихся
    int aIndex = 0;
    int bIndex = 0;

    for (int i = 0; i < result.length; i++) {
          if (a[aIndex] < b[bIndex]) { // проверяем какой элемент из 2-х исходных массивов меньше и записываем в третий
                result[i] = a[aIndex];
                aIndex++;
            } else {
                result[i] = b[bIndex];
                bIndex++;
            }
          }

    return result;
 }
}
* */