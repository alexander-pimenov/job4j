package ru.job4j.design.isp.menu;

/**
 * Интерфейс отвечайщий за действие.
 */
public interface Action {
    /**
     * Метод выполняющий некоторое действие.
     */
    void act();

    /**
     * Метод вызывающий действие
     * у определенного элемента найденного
     * по его ключу.
     *
     * @param key ключ элемента.
     * @return boolean result.
     */
    boolean execute(String key);
}
