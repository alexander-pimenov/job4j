package ru.job4j.generic;

/**
 * Класс Role наследует абстрактный класс для моделей.
 */

public class Role extends Base {

    protected Role(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return String.format("Role[ %s ]", getId());
    }
}
