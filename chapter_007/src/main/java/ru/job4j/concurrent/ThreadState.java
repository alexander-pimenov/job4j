package ru.job4j.concurrent;

import javax.swing.table.TableCellRenderer;

/**
 * Программа, которая запускает нить и выводит ее состояние.
 * За управление нитями в Java отвечает планировщик задач.
 * Он решает, сколько времени отвести на выполнение одной задачи.
 * Это время зависит от текущей ситуации.
 * Если задач много, то переключение между нитями будет частое.
 */

public class ThreadState {
    public static void main(String[] args) {

        //Создем объект нити first.
        Thread first = new Thread(
                () -> {
                    System.out.println("Нить " + Thread.currentThread().getName() + ", ёё  - состояние " + Thread.currentThread().getState());
//                    System.out.println(Thread.currentThread().getState());
                }
        );

        //Создем объект нити second.
        Thread second = new Thread(
                () -> {
                    System.out.println("Нить " + Thread.currentThread().getName() + ", ёё - состояние " + Thread.currentThread().getState());
//                    System.out.println(Thread.currentThread().getState());
                }
        );

        System.out.println("Вызов из " + Thread.currentThread().getName() + " нити -> " + first.getName() + " - " + first.getState());
        System.out.println("Вызов из " + Thread.currentThread().getName() + " нити -> " + second.getName() + " - " + second.getState());
        System.out.println("==================================");

        //У объекта нити вызываем метод start.
        first.start();
        second.start();

        System.out.println("Вызов из " + Thread.currentThread().getName() + " нити -> " + first.getName() + " - " + first.getState());
        System.out.println("Вызов из " + Thread.currentThread().getName() + " нити -> " + second.getName() + " - " + second.getState());
        System.out.println("==================================");

        int a = 0;
        //В цикле мы проверяем состояние запущенной нити.
        while (first.getState() != Thread.State.TERMINATED) {
            System.out.println("итерация a" + a + " " + " вызов из " + Thread.currentThread().getName() + " -> " + first.getName() + " " + first.getState());
            a++;
        }

        int b = 0;
        //В цикле мы проверяем состояние запущенной нити.
        while (second.getState() != Thread.State.TERMINATED) {
            System.out.println("итерация b" + b + " " + " вызов из " + Thread.currentThread().getName() + " -> " + second.getName() + " " + second.getState());
            b++;
        }

        System.out.println("==================================");

        //Нить переходит в состояние TERMINATED, когда все операторы в методе run выполнены.
        //Выводим состояние нити.
        System.out.println("Вызов из " + Thread.currentThread().getName() + " нити -> " + first.getName() + " - " + first.getState());
        System.out.println("Вызов из " + Thread.currentThread().getName() + " нити -> " + second.getName() + " - " + second.getState());

        System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getState());
    }
}
