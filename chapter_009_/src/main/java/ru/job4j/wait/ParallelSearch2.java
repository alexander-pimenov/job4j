package ru.job4j.wait;

/*
 * В этом примере мы видим, что если запустим этот код, то на консоли мы увидим,
 * что нить производитель закончила работу, а нить потребитель продолжает ждать событий.
 * Поэтому ставим consumer.interrupt(); чтобы консюмер прервал работу после того как
 * продюсер отработает и !queue.isEmpty() чтобы выбрать все элементы из очереди.
 * Используем длинные логи, чтобы во время экспериментов с размером очереди queue,
 * с временем сна в тредах (Thread consumer и Thread producer), мы смогли наблюдать
 * наполняемость очереди, и ее состояние в конце работы приложения.
 * */
public class ParallelSearch2 {
    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(5);
        final Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println(String.format("Took element \'%d\' from the queue. Queue size is %d", queue.poll(), queue.size()));
                            Thread.sleep(800);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }, "Consumer"
        );
        consumer.start();
        final Thread producer = new Thread(
                () -> {
                    for (int index = 0; index != 13; index++) {
                        try {
                            queue.offer(index);
                            System.out.println(String.format("put Index \'%d\' into queue. Queue size is %d", index, queue.size()));
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, "Producer"
        );
        producer.start();
        try {
            producer.join();
            consumer.interrupt();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finish... And queue size is: " + queue.size());
        System.out.println(queue);
    }
}
