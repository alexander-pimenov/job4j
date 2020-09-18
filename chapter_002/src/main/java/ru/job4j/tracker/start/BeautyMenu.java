package ru.job4j.tracker.start;

import java.util.function.Consumer;

/**
 * Класс BeautyMenu показывает использование принципа ООД - OCP
 * "Принцип Открытости - Закрытости".
 * Наследуемся от класса MenuTracker и перегружаем метод отвечающий
 * за вид меню showItemMenu().
 * Предварительно private List<UserAction> actions = new ArrayList<>();
 * меняем на protected List<UserAction> actions = new ArrayList<>();
 * иначе нет доступа из BeautyMenu к полю UserAction action.
 * <p>
 * Так же в классе StartUI в методе public void init(), чтобы работал
 * класс по красивому виду меню заменили MenuTracker на BeautyMenu.
 */

public class BeautyMenu extends MenuTracker {
    /**
     * Конструктор.
     *
     * @param input   объект типа Input
     * @param tracker объект типа Tracker
     * @param output  output
     */
    public BeautyMenu(Input input, Tracker tracker, Consumer<String> output) {
        super(input, tracker, output);
    }

    @Override
    public void showItemMenu(UserAction action) {
        System.out.println("-= " + action.info() + " =-");
    }
}
