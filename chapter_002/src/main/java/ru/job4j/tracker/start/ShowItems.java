package ru.job4j.tracker.start;

import ru.job4j.tracker.models.Item;

public class ShowItems extends BaseAction {

    protected ShowItems(int key, String name) {
        super(key, name);
    }

    @Override
    public void execute(Input input, Tracker tracker) {

        System.out.println("-------------- Show all items --------------");
        for (Item item : tracker.findAll()) { //(Item item : items)
            System.out.println(String.format("Id: %s Name: %s Description: %s",
                    item.getId(), item.getName(), item.getDesc()));
        }
        System.out.println("--------------- End of list ---------------");
    }
}
