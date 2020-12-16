package ru.job4j.wait;

/*В Java есть механизм позволяющий разбудить нить,
 * если состояние программы поменялось.
 * У объекта монитора есть метод wait(), notifyAll().
 * Метод notifyAll() будит все нити, которые ждали изменения состояния.
 * Метод wait переводит нить в состояние ожидание, если программа не
 * может дальше выполнятся.
 * Метод on() и off() меняют флаг с true на false. После каждого изменения
 * программа будит нити, которые ждут изменений.
 * Переменная flag - это общий ресурс, поэтому мы с ней работаем  только
 * в критической секции.
 * Синхронизация и методы nofityAll() и wait() вызываются у объекта класса
 * Barrier!!!
 * Когда нить заходить в метод check(), то она проверяет flag.
 * Если флаг = false, то нить засыпает.
 * Когда другая нить выполни метод on() или off(), то первая нить
 * просыпается и проверяет флаг.
 */
public class Barrier {
    //это общий ресурс, поэтому работаем с ним только
    //в критической секции.
    private boolean flag = false;

    //Наглядно указываем монитор.
    private final Object monitor = this;

    public void on() {
        synchronized (monitor) {
            flag = true;
            monitor.notifyAll();
        }
    }

    public void off() {
        synchronized (monitor) {
            flag = false;
            monitor.notifyAll();
        }
    }

    public void check() {
        synchronized (monitor) {
            while (!flag) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
