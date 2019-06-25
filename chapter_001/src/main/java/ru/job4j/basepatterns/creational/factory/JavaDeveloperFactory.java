package ru.job4j.basepatterns.creational.factory;

/**
 * JavaDeveloperFactory реализует интерфейс DeveloperFactory
 * реализует метод createDeveloper и возвращает JavaDeveloper
 */

public class JavaDeveloperFactory implements DeveloperFactory {
    @Override
    public Developer createDeveloper() {
        return new JavaDeveloper();
    }
}
