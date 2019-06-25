package ru.job4j.basepatterns.creational.factory;

/**
 * создаем класс JavaDeveloper для реализации интерфеса Developer
 */

public class JavaDeveloper implements Developer {
    @Override
    public void writeCode() {
        System.out.println("Java developer writes Java code...");
    }
}
