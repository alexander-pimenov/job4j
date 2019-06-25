package ru.job4j.basepatterns.creational.abstractfactory.banking;

import ru.job4j.basepatterns.creational.abstractfactory.Developer;

public class JavaDeveloper implements Developer {
    @Override
    public void writeCode() {
        System.out.println("Java developer writes Java code...");
    }
}
