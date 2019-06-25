package ru.job4j.basepatterns.creational.factory;
/**
 * CppDeveloperFactory реализует интерфейс DeveloperFactory
 * реализует метод createDeveloper и возвращает CppDeveloper
 */

public class CppDeveloperFactory implements DeveloperFactory {
    @Override
    public Developer createDeveloper() {
        return new CppDeveloper();
    }
}
