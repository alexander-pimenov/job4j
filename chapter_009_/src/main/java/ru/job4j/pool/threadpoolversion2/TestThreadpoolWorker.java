package ru.job4j.pool.threadpoolversion2;

public class TestThreadpoolWorker {
    public static void main(String[] args) {
        ThreadPoolWorker threadpool = ThreadPoolWorker.getInstance();
        int runnableCount = 10;
        Runnable r;
        for (int i = 0; i < runnableCount; i++) {
            r = new SimpleTask(i);
            threadpool.work(r);
        }

        threadpool.shutdown();
    }
}

// Задача класса для выполнения
class SimpleTask implements Runnable {
    //у каждой задачи будет id
    private int id;

    SimpleTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Executing " + this.toString()
                + " inside : " + Thread.currentThread().getName());
        //Иммитируем работу
        for (int i = 0; i < 50; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
//                System.out.println("----- " + Thread.currentThread().getName() + " " + e.getMessage());
                Thread.currentThread().interrupt(); //для правильной обработки перевыставим флаг
            }
        }
        System.out.println(this.toString() + " end..");
    }

    @Override
    public String toString() {
        return "Task{id=" + id + '}';
    }
}
