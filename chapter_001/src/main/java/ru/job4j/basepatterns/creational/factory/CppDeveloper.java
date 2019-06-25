package ru.job4j.basepatterns.creational.factory;

/**
 * создаем класс CppDeveloper для реализации интерфеса Developer
 */

public class CppDeveloper implements Developer {
    @Override
    public void writeCode() {
        System.out.println("C++ developer writes C++ code...");
    }
}
