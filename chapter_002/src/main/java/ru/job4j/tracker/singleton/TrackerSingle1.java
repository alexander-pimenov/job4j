package ru.job4j.tracker.singleton;

import ru.job4j.tracker.models.Item;

public enum TrackerSingle1 {
     INSTANCE; // здесь мы указываем перечисления.

    // Конструкторы и методы.
    public Item add(Item model) {
        return model;
    }
}