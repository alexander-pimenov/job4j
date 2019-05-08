package ru.job4j.tracker.start;

import ru.job4j.tracker.models.Item;

public class UpdateItem implements UserAction {
    @Override
    public int key() {
        return 2;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------- Updating item ---------------");
        String id = input.ask("Please, enter the item's Id that you want to change:");
        String name = input.ask("Please, enter item's name change:");
        String desc = input.ask("Please, enter item's description change:");
        Item item = new Item(name, desc, 123L);
        if (tracker.replace(id, item)) {
            System.out.println("----- Item witn Id : " + item.getId() + " changed -----");
            System.out.println("------------ Updating complete ------------");
        } else {
            System.out.println(String.format("There is no items with this Id %s", id));
        }
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Edit item.");
    }
}
