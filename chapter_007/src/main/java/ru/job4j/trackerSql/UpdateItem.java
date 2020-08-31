package ru.job4j.trackerSql;

import java.util.function.Consumer;

public class UpdateItem extends BaseAction {
    protected UpdateItem(int key, String name) {
        super(key, name);
    }

    @Override
    public void execute(Input input, Store tracker, Consumer<String> output) {
        System.out.println("------------- Updating item ---------------");
        String id = input.ask("Please, enter the item's Id that you want to change:");
        String name = input.ask("Please, enter item's name change:");
        String desc = input.ask("Please, enter item's description change:");
        Item item = new Item(name, desc, 123L);
        if (tracker.replace(id, item)) {
            output.accept("----- Item with Id : " + item.getId() + " changed -----");
            //System.out.println("----- Item with Id : " + item.getId() + " changed -----");
            System.out.println("------------ Updating complete ------------");
        } else {
            System.out.println(String.format("There is no items with this Id %s", id));
        }
    }
}
