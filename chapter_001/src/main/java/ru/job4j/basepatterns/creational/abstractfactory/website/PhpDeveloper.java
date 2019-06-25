package ru.job4j.basepatterns.creational.abstractfactory.website;

import ru.job4j.basepatterns.creational.abstractfactory.Developer;

public class PhpDeveloper implements Developer {
    @Override
    public void writeCode() {
        System.out.println("Php developer writes php code...");
    }
}
