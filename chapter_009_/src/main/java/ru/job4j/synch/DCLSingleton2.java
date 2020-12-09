package ru.job4j.synch;

import static ru.job4j.colorsccheme.ColorScheme.*;

/**
 * Разбираем модель памяти при работе с Thread.
 * С помощью ключевого слова volatile решаем проблему видимости (share visibility problem).
 * Когерентность кешей.
 * Приведен код синглтона - double check locking (двойная проверка блокировки).
 * Для удодства чтения вывода использован класс ColorScheme.
 */
public class DCLSingleton2 {
    private static volatile DCLSingleton2 inst;
    private static boolean flag1 = true;
    private static boolean flag2 = true;
    private static boolean flag3 = true;


    public static DCLSingleton2 instOf() {
        if (inst == null) {
            synchronized (DCLSingleton2.class) {
                if (inst == null) {
                    System.out.println("Создан DCLSingleton!!!");
                    inst = new DCLSingleton2();
                }
            }
        }
        return inst;
    }

    private DCLSingleton2() {
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(
                () -> {
                    while (flag1) {
                        System.out.println(GREEN + Thread.currentThread().getName());
                        try {
                            Thread.sleep(300);
                            System.out.println(inst != null);
                            instOf();
                            System.out.println(inst != null);

                        } catch (InterruptedException e) {
                            System.out.println(Thread.currentThread().getName()
                                    + " is interrupted (true or false): "
                                    + Thread.currentThread().isInterrupted());
                        }
                    }
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    while (flag2) {
                        System.out.println(PURPLE + Thread.currentThread().getName());
                        try {
                            Thread.sleep(500);
                            System.out.println(inst != null);
                            instOf();
                            System.out.println(inst != null);

                        } catch (InterruptedException e) {
                            System.out.println(Thread.currentThread().getName()
                                    + " is interrupted (true or false): "
                                    + Thread.currentThread().isInterrupted());
                        }
                    }
                }
        );
        Thread thread3 = new Thread(
                () -> {
                    while (flag3) {
                        System.out.println(CYAN + Thread.currentThread().getName());
                        try {
                            Thread.sleep(700);
                            System.out.println(inst != null);
                            instOf();
                            System.out.println(inst != null);

                        } catch (InterruptedException e) {
                            System.out.println(Thread.currentThread().getName()
                                    + " is interrupted (true or false): "
                                    + Thread.currentThread().isInterrupted());
                        }
                    }
                }
        );

        System.out.println(RED + Thread.currentThread().getName() + " started...");
        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(3000);
        flag1 = false;
        Thread.sleep(3000);
        flag2 = false;
        Thread.sleep(3000);
        flag3 = false;
        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println(RED + Thread.currentThread().getName() + " finished.");

    }

}
