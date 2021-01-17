package ru.job4j.wait;

/*
 * В этом примере мы видим, что если запустим этот код, то на консоли мы увидим,
 * что нить производитель закончила работу, а нить потребитель продолжает ждать событий.
 * Поэтому ставим consumer.interrupt(); чтобы консюмер прервал работу после того как
 * продюсер отработает и !queue.isEmpty() чтобы выбрать все элементы из очереди.
 * Используем длинные логи, чтобы во время экспериментов с размером очереди queue,
 * с временем сна в тредах (Thread consumer и Thread producer), мы смогли наблюдать
 * наполняемость очереди, и ее состояние в конце работы приложения.
 * Здесь мы проверяем, что очередь пустая или нить выключили.
 * Двойная проверка в Thread consumer нужна,
 * если производитель закончил свою работу и сразу подаст сигнал об отключении потребителя,
 * то мы не сможет прочитать все данные. С другой стороны, если мы успели прочитать все
 * данные и находимся в режиме wait пришедший сигнал запустит нить и
 * проверит состояние очереди и завершит цикл. Потребитель закончит свою работу.
 * */
public class ParallelSearch2 {
    public static void main(String[] args) {
        SimpleBlockingQueue3<Integer> queue = new SimpleBlockingQueue3<Integer>(5);
        final Thread consumer = new Thread(
                () -> {
                    //проверяем, что очередь пустая или нить выключили
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
//                        if (!queue.isEmpty()) {
//                            System.out.println(queue.poll());
//                        }
                        try {
                            System.out.println(String.format("%s took element \'%d\' from the queue. Queue size is %d",
                                    Thread.currentThread().getName(), queue.poll(), queue.size()));
                            Thread.sleep(800);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }, "Consumer"
        );
        consumer.start();
        final Thread producer1 = new Thread(
                () -> {
                    for (int index = 0; index != 13; index++) {
                        try {
                            queue.offer(index);
                            System.out.println(String.format("%s put Index \'%d\' into queue. Queue size is %d",
                                    Thread.currentThread().getName(), index, queue.size()));
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, "Producer-1"
        );
        producer1.start();

        final Thread producer2 = new Thread(
                () -> {
                    for (int index = 15; index != 25; index++) {
                        try {
                            queue.offer(index);
                            System.out.println(String.format("%s put Index \'%d\' into queue. Queue size is %d",
                                    Thread.currentThread().getName(), index, queue.size()));
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, "Producer-2"
        );
        producer2.start();

        try {
            producer1.join();
            producer2.join();
            consumer.interrupt();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finish... And queue size is: " + queue.size());
        System.out.println(queue);
    }
}
