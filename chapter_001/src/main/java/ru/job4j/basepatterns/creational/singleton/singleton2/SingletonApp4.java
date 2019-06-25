package ru.job4j.basepatterns.creational.singleton.singleton2;

/**
 * здесь мы улучшаем Синглтон для многопоточности
 * здесь уже реализована Ленивая инициализация
 * но есть новый недостаток: метод synchronized исключает что этот код
 * будет выполнен несколькими потоками (несколькими клиентами одновременно)
 * синхронизация нам требуется лишь 1 раз при первом обращении. Потом Singleton4
 * будет уже проинициализирован. А синхронизация будет вызываться каждый раз,
 * но сама операция синхронизирования дорогая - занимает время и ресурсы системы
 *
 */
public class SingletonApp4 {
    public static void main(String[] args) throws InterruptedException {

        final int SIZE = 100;

        Thread[] t = new Thread[SIZE];
        for (int i = 0; i < SIZE; i++) {
            t[i] = new Thread(new R4());
            t[i].start();//тут вызываем потоки
        }
        //это чтобы мы подождали пока все потоки выполнытся
        for (int i = 0; i < SIZE; i++) {
            t[i].join();//join обозначает - подождать в этой строчке кода выполнение t[i]
        }
        //к этой строчке мы придем, когда все потоки будут выполнены
        System.out.println(Singleton4.counter);
    }

}

/*этот класс будет вызывать наши сингтоны в нескольких потоках одновременно */
class R4 implements Runnable {

    @Override
    public void run() {
        Singleton4.getInstance();
    }
}

class Singleton4 {

    public static int counter = 0;

    private static Singleton4 instance = null;

    private Singleton4() {
        counter++;
    }

    //перепишем getInstance
    public static synchronized Singleton4 getInstance(){
        if (instance == null) {
            instance = new Singleton4();//это то место где вызывается приватный конструктор
        }
        return instance;
    }


//    //нам нужет только геттер, чтоб вернуть Singleton4
//    public static Singleton4 getInstance() {
//        return instance;
//    }

//    //этот код уже не нужен
//    public static Singleton4 getInstance() {
//        if (instance == null) {
//            instance = new Singleton4();//это то место где вызывается приватный конструктор
//        }
//        return instance;
//    }
}

