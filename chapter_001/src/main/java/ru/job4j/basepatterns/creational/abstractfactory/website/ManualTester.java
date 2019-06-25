package ru.job4j.basepatterns.creational.abstractfactory.website;

import ru.job4j.basepatterns.creational.abstractfactory.Tester;

public class ManualTester implements Tester {
    @Override
    public void testCode() {
        System.out.println("Manual tester tests website...");
    }
}
