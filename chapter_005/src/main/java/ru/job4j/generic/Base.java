package ru.job4j.generic;

/**
 * Base - это абстрактный класс для моделей c методами String getId().
 */

public abstract class Base {

    private final String id;

    protected Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
