package ru.job4j.basepatterns.behavioral.strategy.example1;

public class Sleeping implements Activity {
    @Override
    public void justDoIt() {
        System.out.println("Sleeping...");
    }
}
