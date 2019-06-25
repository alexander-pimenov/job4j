package ru.job4j.basepatterns.creational.singleton.singleton2;

/**
 * Способ приспособить Синглтон к многопоточности, это инициализировать в момент объявления ПОЛЯ
 * этот способ будет работать. counter дает 1 постоянно
 * Работает, потому что Singleton3 инициализировали только 1 раз, в момент загрузки класса
 * и поэтому здесь нет проблем с многопоточностью
 * МИНУС этого метода в том, что когда наш Singleton3 определяется в ПОЛЕ , у нас не работает
 * ленивая инициализация!!! Это когда Singleton3 инициализировался бы при вызове метода getInstance
 * По скорости, эта реализация Синглтона самая быстрая, т.к. инициализация его происходт в ПОЛЕ
 * private static Singleton3 instance = new Singleton3();
 */
public class SingletonApp3 {

    public static void main(String[] args) throws InterruptedException {

        final int SIZE = 100;

        Thread[] t = new Thread[SIZE];
        for (int i = 0; i < SIZE; i++) {
            t[i] = new Thread(new R3());
            t[i].start();//тут вызываем потоки
        }
        //это чтобы мы подождали пока все потоки выполнытся
        for (int i = 0; i < SIZE; i++) {
            t[i].join();//join обозначает - подождать в этой строчке кода выполнение t[i]
        }
        //к этой строчке мы придем, когда все потоки будут выполнены
        System.out.println(Singleton3.counter);
    }

}

/*этот класс будет вызывать наши сингтоны в нескольких потоках одновременно */
class R3 implements Runnable {

    @Override
    public void run() {
        Singleton3.getInstance();
    }
}

class Singleton3 {

    public static int counter = 0;

    private static Singleton3 instance = new Singleton3();

    private Singleton3() {
        counter++;
    }

    //нам нужет только геттер, чтоб вернуть Singleton3
    public static Singleton3 getInstance() {
        return instance;
    }

}