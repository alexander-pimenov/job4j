package ru.job4j.tracker.start;

import ru.job4j.tracker.models.Item;

public class ShowItems implements UserAction {


    @Override
    public int key() {
        return 1;
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

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Show all items.");
    }
}
