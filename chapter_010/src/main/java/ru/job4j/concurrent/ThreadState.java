package ru.job4j.concurrent;

/**
 * Программа, которая запускает нить и выводит ее состояние.
 * За управление нитями в Java отвечает планировщик задач.
 * Он решает, сколько времени отвести на выполнение одной задачи.
 * Это время зависит от текущей ситуации.
 * Если задач много, то переключение между нитями будет частое.
 * По состояниям нити можно произвести диагностику, что происходит в нашей программе.
 */
public class ThreadState {
    public static void main(String[] args) throws InterruptedException {

        //Создем объект нити first.
        Thread first = new Thread(
                () -> {
                    System.out.println("Нить " + Thread.currentThread().getName() + ", ёё  - состояние " + Thread.currentThread().getState());
                }
        );

        //Создем объект нити second.
        Thread second = new Thread(
                () -> {
                    System.out.println("Нить " + Thread.currentThread().getName() + ", ёё - состояние " + Thread.currentThread().getState());
                }
        );

        System.out.println("Вызов из " + Thread.currentThread().getName() + " нити -> " + first.getName() + " - " + first.getState());
        System.out.println("Вызов из " + Thread.currentThread().getName() + " нити -> " + second.getName() + " - " + second.getState());

        //У объекта нити вызываем метод start.
        first.start();
        second.start();

        System.out.println("Вызов из " + Thread.currentThread().getName() + " нити -> " + first.getName() + " - " + first.getState());
        System.out.println("Вызов из " + Thread.currentThread().getName() + " нити -> " + second.getName() + " - " + second.getState());

        //В цикле мы проверяем состояние запущенной нити, используем нить first.
        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            System.out.println(" вызов из " + Thread.currentThread().getName() + " -> " + first.getName() + " " + first.getState());
        }
        first.join();
        second.join();
        //Нить переходит в состояние TERMINATED, когда все операторы в методе run выполнены.
        //Выводим состояние нити.
        System.out.println("Нить " + first.getName() + ", состояние - " + first.getState());
        System.out.println("Нить " + second.getName() + ", состояние - " + second.getState());
        System.out.println("Работа завершена.");
    }
}
