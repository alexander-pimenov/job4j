package ru.job4j.basepatterns.creational.singleton.singleton2;

/**
 * здесь мы улучшаем Синглтон для многопоточности
 * здесь уже реализована Ленивая инициализация
 * метод synchronized  здесь будет использоваться 1 раз.
 * это пример ОЧЕНЬ ХОРОШЕЙ РЕАЛИЗАЦИИ СИНГЛТОНА, к-рый обладает и свойствами ленивой
 * инициализации, потому что инициализация происходит только по требованию getInstance(),
 * и решена проблема лишней синхронизации, когда каждый поток синхронизируется.
 *
 */
public class SingletonApp5 {
    public static void main(String[] args) throws InterruptedException {

        final int SIZE = 100;

        Thread[] t = new Thread[SIZE];
        for (int i = 0; i < SIZE; i++) {
            t[i] = new Thread(new R5());
            t[i].start();//тут вызываем потоки
        }
        //это чтобы мы подождали пока все потоки выполнытся
        for (int i = 0; i < SIZE; i++) {
            t[i].join();//join обозначает - подождать в этой строчке кода выполнение t[i]
        }
        //к этой строчке мы придем, когда все потоки будут выполнены
        System.out.println(Singleton5.counter);
    }
}

/*этот класс будет вызывать наши сингтоны в нескольких потоках одновременно */
class R5 implements Runnable {

    @Override
    public void run() {
        Singleton5.getInstance();
    }
}

class Singleton5 {

    public static int counter = 0;

    private static volatile Singleton5 instance = null;//volatile
    //volatile - это тема многопоточности, когда работает несколько потоков
    //работают с какими то разделяемыми ресурсами (они работают в 1 блоке if (instance == null)
    //если они не находятся в критической секции, а это внутри синхронайзд или
    //внутри блока синхронайзд, то нужно делать volatile. Там проблема с кэшами в
    //многопроцессорных системах

    private Singleton5() {
        counter++;
    }

    //перепишем getInstance
    //это пример ленивой инициализации
    //здесь instance получит только самый первый поток,а остальные не пройдут проверку на null
    //или, если и прорвется второй самый быстрый поток вслед за первым, он не пройдет блок synchronized (Singleton5.class)
    //вот таки образом synchronized работает только 1 раз и не тратятся ресурсы
    public static Singleton5 getInstance() {
        if (instance == null) { //в этот блок заходят все потоки
            synchronized (Singleton5.class) {//синзронизация по классу Singleton5, это возможность синхронизации в статическом методе
                if (instance == null)
                    instance = new Singleton5();//это то место где вызывается приватный конструктор,
                // происходит конструирование Синглтона
            }
        }
        return instance;
    }
}

