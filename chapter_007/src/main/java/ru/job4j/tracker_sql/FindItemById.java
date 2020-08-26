package ru.job4j.tracker_sql;

import java.util.function.Consumer;

public class FindItemById extends BaseAction {
    protected FindItemById(int key, String name) {
        super(key, name);
    }

    @Override
    public void execute(Input input, Store tracker, Consumer<String> output) {
        System.out.println("------------ Find item by Id --------------");
        String id = input.ask("Please, enter the item's Id that you want to find:");
        Item found = tracker.findById(id);
        if (found != null && id.equals(found.getId())) {
            System.out.println("------------ Your item ------------");
            //System.out.println(String.format("Id: %s Name: %s Description: %s",
            output.accept(String.format("Id: %s Name: %s Description: %s",
                    found.getId(), found.getName(), found.getDesc()));
        } else {
            System.out.println("There is no item with this Id.");
        }
        System.out.println("-------------- End of search --------------");
    }
}
