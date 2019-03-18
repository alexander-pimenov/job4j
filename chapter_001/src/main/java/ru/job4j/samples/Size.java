package ru.job4j.samples;

public class Size {
    public int add (int left, int right){
        int result = left + right;
        return result ;
    }
    public void info (int size) {
        System.out.println("Your size is" + size);

    }
    public static void main (String[] args) {
        //Здесь из класса создаем контейнер.
        Size size = new Size();
        int nike = 20;
        int puma = 50;
        //Вызываем метод add.
        int value = size.add(nike, puma);
        //Вызываем метод info.
        size.info (value);
    }
}

