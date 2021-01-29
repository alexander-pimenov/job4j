package ru.job4j.simpleblockingqueue;

import java.util.Random;

public class SimpleBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue queue = new SimpleBlockingQueue(5);
        new Thread(new Producer(queue)).start();
        Thread.sleep(1000);
        new Thread(new Consumer(queue)).start();
    }

    static class Producer implements Runnable {
        private final SimpleBlockingQueue<Integer> queue;

        public Producer(SimpleBlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            System.out.println("[Producer] run");
            while (true) {
                try {
                    queue.offer(produce());
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private Integer produce() {
            Integer i = new Random().nextInt(100);
            System.out.println("[Producer] produce: " + i);
            return i;
        }
    }

    static class Consumer implements Runnable {
        private final SimpleBlockingQueue<Integer> queue;

        public Consumer(SimpleBlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            System.out.println("[Consumer] run");
            while (true) {
                try {
                    consume();
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void consume() throws InterruptedException {
            Integer i = queue.poll();
            System.out.println("[Consumer] consumed: " + i);
        }
    }
}
