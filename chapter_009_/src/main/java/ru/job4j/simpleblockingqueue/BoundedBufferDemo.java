package ru.job4j.simpleblockingqueue;

import java.util.Random;

import static ru.job4j.colorsccheme.ColorScheme.BLUE;
import static ru.job4j.colorsccheme.ColorScheme.RED;

/*
 * В примере рассматриваем блокирующий буфер. На мой взгляд опасность этого
 * буфера лишь в том, что он основан на массиве, в который можем добавлять
 * объекты типа Object, что может привести к некоторой путанице.
 * Блокирующие очереди основанные на параметризованных коллекциях такой путаницы
 * не допустят.
 *
 * */
public class BoundedBufferDemo {
    public static void main(String[] args) throws InterruptedException {
        BoundedBuffer buffer = new BoundedBuffer();
        new Thread(new Producer(buffer)).start();
        Thread.sleep(1000);
        new Thread(new Consumer(buffer)).start();
    }

    //Класс Производитель
    static class Producer implements Runnable {
        private final BoundedBuffer queue;

        public Producer(BoundedBuffer queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            System.out.println(RED + "[Producer] run");
            while (true) {
                try {
                    queue.put(produce());
                    Thread.sleep(200); //иммитация задержки работы
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        //Метод генерирует произвольное целое число от 1 до 100
        private Integer produce() {
            Integer i = new Random().nextInt(100);
            System.out.println(RED + "[Producer] produce: " + i);
            return i;
        }
    }

    //Класс Потребитель
    static class Consumer implements Runnable {
        private final BoundedBuffer queue;

        public Consumer(BoundedBuffer queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            System.out.println(BLUE + "[Consumer] run");
            while (true) {
                try {
                    consume();
                    Thread.sleep(1500); //иммитация задержки работы
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        //Метод выбирает из бифера объекты
        private void consume() throws InterruptedException {
            Object i = queue.take();
            System.out.println(BLUE + "[Consumer] consumed: " + i);
        }
    }
}
