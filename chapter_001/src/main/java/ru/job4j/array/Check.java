package ru.job4j.array;

public class Check {
    //метод на проверку однотипности элементов массива
    public boolean mono(boolean[] data) {
        boolean result = false;
        for (int i=0;i!=data.length;i++) {

            if (data[i]==data[data.length-1]) {
                result = true;
            } else {
                result = false;
                break;
            }
        }
        return result;
    }
}

