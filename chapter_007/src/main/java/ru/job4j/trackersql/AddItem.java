package ru.job4j.trackersql;

import java.util.function.Consumer;

public class AddItem extends BaseAction {
    public AddItem(int key, String name) {
        super(key, name);
    }

    @Override
    public void execute(Input input, Store tracker, Consumer<String> output) {
        System.out.println("------------ Adding new item --------------");
        String name = input.ask("Please, provide item name:");
        String desc = input.ask("Please, provide item description:");
        Item item = new Item(name, desc, 123L);
        tracker.add(item);
        output.accept("------------ New Item with Id : " + item.getId());
        output.accept("------------ New Item with Name : " + item.getName());
        output.accept("------------ New Item with Description : " + item.getDesc());
//        System.out.println("------------ New Item with Id : " + item.getId());
//        System.out.println("------------ New Item with Name : " + item.getName());
//        System.out.println("------------ New Item with Description : " + item.getDesc());

    }
}
