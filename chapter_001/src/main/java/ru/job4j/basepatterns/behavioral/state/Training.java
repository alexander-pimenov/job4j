package ru.job4j.basepatterns.behavioral.state;

public class Training implements Activity {
    @Override
    public void justDoIt() {
        System.out.println("Training...");
    }
}
