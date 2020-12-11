package ru.job4j.synch;

/*
 * Объект монитор - это объект, по которому виртуальная машина понимает,
 * что блок кода выполняется нитью.
 * Объект монитора может быть в двух значениях - блок занят или блок
 * свободный. Проверка состояния монитора атомарна.
 * Блок synchronized() {} называется критической секцией. В ней
 * одновременно может находиться только одна нить.
 */
public class Count {
    private int value;

    public void increment() {
        synchronized (this) {
            value++;
        }
    }

    //Эквивалентная запись: с ключевым словом synchronized
//    public synchronized void increment() {
//        value++;
//    }

    public int get() {
        synchronized (this) {
            return value;
        }
    }

    //Эквивалентная запись: с ключевым словом synchronized
//    public synchronized int get() {
//        return value;
//    }
}