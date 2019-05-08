package ru.job4j.tracker.start;

import ru.job4j.tracker.models.Item;

public class FindItemById implements UserAction {
    @Override
    public int key() {
        return 4;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Find item by Id --------------");
        String id = input.ask("Please, enter the item's Id that you want to find:");
        Item found = tracker.findById(id);
        if (found != null && id.equals(found.getId())) {
            System.out.println("------------ Your item ------------");
            System.out.println(String.format("Id: %s Name: %s Description: %s",
                    found.getId(), found.getName(), found.getDesc()));
        } else {
            System.out.println("There is no item with this Id.");
        }
        System.out.println("-------------- End of search --------------");
    }


    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Find item by Id.");
    }
}
