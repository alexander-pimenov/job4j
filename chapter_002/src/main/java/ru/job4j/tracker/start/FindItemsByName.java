package ru.job4j.tracker.start;

import ru.job4j.tracker.models.Item;

public class FindItemsByName implements UserAction {
    @Override
    public int key() {
        return 5;
    }

    @Override
    public void execute(Input input, Tracker tracker) {

        System.out.println("------------ Find task by Name --------------");
        String name = input.ask("Please, enter the item's name that you want to find :");
        Item[] items = tracker.findByName(name);
        if (items.length > 0) {
            System.out.println("------------ Your items ------------");
            for (Item item : items) {
                System.out.println(String.format("Id: %s Name: %s Description: %s",
                        item.getId(), item.getName(), item.getDesc()));
            }
        } else {
            System.out.println(String.format("There is no items with this name %s.", name));
        }
        System.out.println("------------- End of search ---------------");
    }


    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Find items by name.");
    }
}
