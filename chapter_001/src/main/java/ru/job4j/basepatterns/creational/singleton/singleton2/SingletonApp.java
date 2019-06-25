package ru.job4j.basepatterns.creational.singleton.singleton2;

/**
 * Пример данного Синглетона не будет работать в многопоточной среде
 * пример взчт по urt: https://youtu.be/u11nr3ifLd0
 */

public class SingletonApp {
    public static void main(String[] args) {

/*
        //мы можем создать несколько экземпляров класса Singleton: s1, s2, s3..
        //нужно сделать так, чтобы нельзя было создавать другие экземпляры
        //ниже для этого заводим private конструктор
        Singleton s1 = new Singleton();
        Singleton s2 = new Singleton();
        Singleton s3 = new Singleton();

        //чтобы вызвать снаружи private конструктор для создания класса,
        //нам потребуется статический метод public static Singleton getInstance()
*/

        final int SIZE = 100;
        //создаем массив под Singleton мы можем, т.е. при этом сам объект еще не создается
        Singleton[] arr = new Singleton[SIZE];

        //теперь получам Singleton, вызывая у класса getInstance
        for (int i = 0; i < SIZE; i++) {
            arr[i] = Singleton.getInstance();
        }
        System.out.println(Singleton.counter); //смотрим сколько реально было нашего Singleton
        //в консоли мы видим 1. Это значит, что конструктор вызвался 1 раз.
        //после первого вызова конструктора counter = 1, а instance НЕ= null
        //поэтому каждый раз в метод getInstance возращает тот же объект instance

    }
}

class Singleton {

    //создаем счетчик, для проверки работы Singleton,
    //и будем его инкрементировать в нашем конструкторе
    public static int counter = 0;

    //ПОЛЕ - ссылка на сам же класс Singleton
    //в instance будет хранится статический объект
    private static Singleton instance;

    //private чтобы не было прямого доступа к нему
    private Singleton() {
        counter++;
    }

    //чтобы вызвать снаружи создадим статический паблик метод
    //который будет возвращать нам экземпляр, инициализируется конструктор
    //тоже внутри метода.
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();//это то место где вызывается приватный конструктор
        }
        return instance;
    }
}