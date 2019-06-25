package ru.job4j.basepatterns.creational.factory;

/**
 * DeveloperFactory - это сущность создающая разработчиков
 */

public interface DeveloperFactory {
    //имеет один метод для создания Developer
    Developer createDeveloper();
}
