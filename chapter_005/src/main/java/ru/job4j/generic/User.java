package ru.job4j.generic;

/**
 * Класс User наследует абстрактный класс для моделей.
 */

public class User extends Base {

    protected User(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return String.format("User[ %s ]", getId());
    }
}
