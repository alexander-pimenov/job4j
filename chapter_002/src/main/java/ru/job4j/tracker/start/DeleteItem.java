package ru.job4j.tracker.start;

import ru.job4j.tracker.models.Item;

public class DeleteItem implements UserAction {
    @Override
    public int key() {
        return 3;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Delete item by id --------------");
        String id = input.ask("Please, enter the item's Id that you want to delete:");
        Item item = tracker.findById(id);
        if (tracker.delete(id)) {
            System.out.println("------------ Your item to delete: ------------");
            System.out.println(String.format("Id: %s Name: %s Description: %s ",
                    item.getId(), item.getName(), item.getDesc()));
            System.out.println("------------ Item was deleted --------------");
        } else {
            System.out.println(String.format("There is no items with this Id %s", id));
        }

//        boolean deleted = tracker.delete(id);
//        if (deleted) {
//            System.out.println("------------ Item was deleted --------------");
//        } else {
//            System.out.println(String.format("There is no items with Id %s", id));
//        }
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Delete item.");
    }
}
