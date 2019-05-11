package ru.job4j.tracker.start;

import java.util.ArrayList;
import java.util.List;

public class MenuTracker {
    /**
     * Поле ввода вывода
     *
     * @param хранит ссылку на объект .
     */
    private Input input;
    /**
     * @param хранит ссылку на объект .
     */
    private Tracker tracker;
    /**
     * @param хранит ссылку на массив типа UserAction.
     */
    private List<UserAction> actions = new ArrayList<>();

    /**
     * Конструктор.
     *
     * @param input   объект типа Input
     * @param tracker объект типа Tracker
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Метод для получения массива меню.
     *
     * @return длину массива
     */
    public int getActionsLentgh() {
        return this.actions.size();
    }

    /**
     * Метод заполняет массив.
     */
    public void fillActions() {
        this.actions.add(new AddItem());     //(new AddItem(0, "Add program"))
        this.actions.add(new ShowItems());   //(new ShowItems(1, "Show all items"))
        this.actions.add(new UpdateItem());  //(new UpdateItem(2, "Edit item"))
        this.actions.add(new DeleteItem());  //(new DeleteItem(3, "Delete item")
        this.actions.add(new FindItemById());  //(new FindItemById(4, "Find item by Id"))
        this.actions.add(new FindItemsByName()); //(new FindItemsByName(5, "Find items by name"))
        this.actions.add(new ExitProgram());  //(new ExitProgram(6, "Exit Program"))
    }

    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     *
     * @param key ключ операции
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);

/*Т.к. добавили exceptions, то эту проверку закомментировал*/
//        if (key < this.actions.size()) {
//            this.actions.get(key).execute(this.input, this.tracker);
//        } else {
//            System.out.println("This menu item is not present."); //Такого пункта меню нет.
//            System.out.println("Select an existing menu item"); //Выберете нужный пункт.
//        }
    }

    /**
     * Метод выводит на экран меню.
     */
    public void show() {
        System.out.println("-------------- Menu --------------");
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }
}
