package ru.job4j.tracker.start;

import java.util.function.Consumer;

public interface UserAction {
    /**
     * Метод возвращает ключ опции.
     *
     * @return ключ
     */
    int key();

    /**
     * Основной метод.
     *
     * @param input   объект типа Input
     * @param tracker объект типа Tracker
     * @param output  с помощью Consumer выбираем метод вывода информации
     */
    void execute(Input input, Tracker tracker, Consumer<String> output);

    /**
     * Метод возвращает информацию о данном пункте меню.
     *
     * @return Строка меню
     */
    String info();
}
