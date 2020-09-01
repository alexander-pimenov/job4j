package ru.job4j.trackersql;

import java.util.function.Consumer;

public class DeleteItem extends BaseAction {

    public DeleteItem(int key, String name) {
        super(key, name);
    }

    @Override
    public void execute(Input input, Store tracker, Consumer<String> output) {
        System.out.println("------------ Delete item by id --------------");
        String id = input.ask("Please, enter the item's Id that you want to delete:");
        Item item = tracker.findById(id);
        if (tracker.delete(id)) {
            System.out.println("------------ Your item to delete: ------------");
            //System.out.println(String.format("Id: %s Name: %s Description: %s ",
            output.accept(String.format("Id: %s Name: %s Description: %s ",
                    item.getId(), item.getName(), item.getDesc()));
            System.out.println("------------ Item was deleted --------------");
        } else {
            System.out.println(String.format("There is no items with this Id %s", id));
        }
    }
}

//Можно добавить подтверждение удаления заявки:
// String youSure = input.ask("Удалить заявку " + item.getName() + " " + item.getDescription() + " ? (yes/no) ");
//            if (youSure.equals("yes")) {
//                if (tracker.delete(id)) {
//                  System.out.println("------------ Your item to delete: ------------");
//                  System.out.println(String.format("Id: %s Name: %s Description: %s ",
//                    item.getId(), item.getName(), item.getDesc()));
//                  System.out.println("------------ Item was deleted --------------");
//                } else {
//                    System.out.println(String.format("There is no items with this Id %s", id));
//                }
//            }