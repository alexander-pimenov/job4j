package ru.job4j.wait;

public class ParallelSearch {
    public static void main(String[] args) {
        SimpleBlockingQueue3<Integer> queue = new SimpleBlockingQueue3<>(5);
        final Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        if (!queue.isEmpty()) {
                            System.out.println(queue.poll());
                        }
//                        System.out.println(queue.poll());
                    }
                }, "Consumer"
        );
        consumer.start();
        final Thread producer = new Thread(
                () -> {
                    for (int index = 0; index != 13; index++) {
                        try {
                            queue.offer(index);
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
    }
}
