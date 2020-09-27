package ru.job4j.tracker.models;

public class Task extends Item {

    public Task(String name, String desc) {
        super(name, desc);
    }

    public String calculatePrice() {
        return "100%";
    }

    @Override
    public String toString() {
        return "Task{}" + super.toString();
    }
}
