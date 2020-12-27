package ru.job4j.synch;

/*
 * Разбираем модель памяти при работе с Thread.
 * С помощью ключевого слова volatile решаем проблему видимости (share visibility problem).
 * Когерентность кешей. Это когда переменная должна быть видна всем потокам, т.е.
 * будет помещена в RAM, а не закешированна одним из ядер процессора.
 * Приведен код синглтона - double check locking (двойная проверка блокировки).
 * DCLSingleton.
 */
public class DCLSingleton {
    private static volatile DCLSingleton inst;

    public static DCLSingleton instOf() {
        if (inst == null) {
            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                }
            }
        }
        return inst;
    }

    /*Приватный конструктор*/
    private DCLSingleton() {
    }
}
