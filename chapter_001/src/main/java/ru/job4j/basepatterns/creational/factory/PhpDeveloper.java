package ru.job4j.basepatterns.creational.factory;
/**
 * создаем класс PhpDeveloper для реализации интерфеса Developer
 * этот класс сделали уже позже, когда нам потребовался Php-разработчик
 */

public class PhpDeveloper implements Developer {
    @Override
    public void writeCode() {
        System.out.println("Php developer writes Php code ...");
    }
}
