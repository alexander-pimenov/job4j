package ru.job4j.basepatterns.behavioral.strategy.example1;

public class Reading implements Activity {
    @Override
    public void justDoIt() {
        System.out.println("Reading...");
    }
}
