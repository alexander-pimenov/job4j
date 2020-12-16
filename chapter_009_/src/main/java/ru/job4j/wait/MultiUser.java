package ru.job4j.wait;

import java.util.Scanner;

/*Пример использования класса Barrier*/
public class MultiUser {
    public static void main(String[] args) {
        Barrier barrier = new Barrier();
        Thread master = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    barrier.on(); //командуем проснуться здесь!!!
                },
                "Master"
        );
        Thread slave = new Thread(
                () -> {
                    barrier.check(); //засыпаем в этом месте!!!
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "Slave"
        );
        Thread th3 = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    barrier.off();
                },
                "SomeThread"
        );
        Scanner scanner = new Scanner(System.in);
        try {

            slave.start();
            scanner.nextLine();
            Thread.sleep(1000);
            master.start();
            scanner.nextLine();
            Thread.sleep(1000);
            th3.start();

//        System.out.println("master state: " + master.getState());
//        System.out.println("slave state: " + slave.getState());
//        System.out.println("th3 state: " + th3.getState());


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
