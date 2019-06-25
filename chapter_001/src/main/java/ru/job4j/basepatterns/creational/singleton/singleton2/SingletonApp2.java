package ru.job4j.basepatterns.creational.singleton.singleton2;

/**
 * Пример проверки нашего Синглтона в многопоточной среде
 * это когда несколько клиентов пытаются вызвать метод getInstance
 * counter на выходе будет давать разные числа:1, 2, 3, 4,5. Это потому, что
 * несколько начальных потоков успевают создать объекты Синглтон
 * Т.е. в таком виде Сингтон не работает для многопоточности,
 * некорректная работа
 */
public class SingletonApp2 {
    public static void main(String[] args) throws InterruptedException {
        final int SIZE = 100;

        //для тестирования создаем не 100 синглтонов в массиве, как в предыдущем
        // примере, а 100 потоков. Мы выделяем массив для 100 потоков
        //создаем 100 объектов типа Thread (тред)
        Thread[] t = new Thread[SIZE];
        for (int i = 0; i < SIZE; i++) {
            //инициализируем каждый поток новым экземпляром потока
            //и в него передаем экземпляр класса R, к-рый реализует интерфейс Runnable
            //и методе void run() пытаемся взять instance нашего Синглтона
            //т.е. он либо создается либо берет существующий
            t[i] = new Thread(new R2());
            t[i].start();//тут вызываем потоки
        }
        //это чтобы мы подождали пока все потоки выполнытся
        for (int i = 0; i < SIZE; i++) {
            t[i].join();//join обозначает - подождать в этой строчке кода выполнение t[i]
        }
        //к этой строчке мы придем, когда все потоки будут выполнены
        System.out.println(Singleton2.counter);
    }
}


/*этот класс будет вызывать наши сингтоны в нескольких потоках одновременно */
class R2 implements Runnable {

    @Override
    public void run() {
        Singleton2.getInstance();
    }
}

//Singleton2 вызывается из SingletonApp2
class Singleton2 {

    //создаем счетчик, для проверки работы Singleton,
    //и будем его инкрементировать в нашем конструкторе
    public static int counter = 0;

    //ПОЛЕ - ссылка на сам же класс Singleton
    //в instance будет хранится статический объект
    private static Singleton2 instance;

    //private чтобы не было прямого доступа к конструктору из вне
    private Singleton2() {
        counter++;
    }

    //чтобы вызвать снаружи создадим статический паблик метод
    //который будет возвращать нам экземпляр, инициализируется конструктор
    //тоже внутри метода.
    public static Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();//это то место где вызывается приватный конструктор
        }
        return instance;
    }
}

