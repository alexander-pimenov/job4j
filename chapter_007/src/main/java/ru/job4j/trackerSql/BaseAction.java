package ru.job4j.trackerSql;

public abstract class BaseAction implements UserAction {
    /**
     * Поле номер меню
     */
    private final int key;

    /**
     * Поле строка названия меню
     */
    private final String name;

    /**
     * Конструктор абстактного класса при старте запрашивает
     * номер меню (key) и название меню (name)
     */
    protected BaseAction(final int key, final String name) {
        this.key = key;
        this.name = name;
    }


    /**
     * Метод возвращает ключ опции.
     */
    @Override
    public int key() {
        return this.key;
    }


    /**
     * Метод возвращает информацию о данном пункте меню.
     */
    @Override
    public String info() {
        return String.format("%s : %s", this.key, this.name);
    }

}
