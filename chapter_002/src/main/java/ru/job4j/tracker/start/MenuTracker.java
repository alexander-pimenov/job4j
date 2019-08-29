package ru.job4j.tracker.start;

import java.util.ArrayList;
import java.util.List;

/**
 * MenuTracker отвечает за выбор меню, за показ этого меню,
 * за то что пользователь выбирает из меню
 */
public class MenuTracker {
    /**
     * Поле ввода вывода
     *
     * хранит ссылку на объект .
     */
    private Input input;
    /**
     * хранит ссылку на объект
     * из хранилища Tracker
     */
    private Tracker tracker;
    /**
     * хранит ссылку на список типа UserAction.
     * это те действия, которые описаны в нашей системе
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
     * Метод для получения списка меню.
     *
     * @return длину массива
     */
    public int getActionsLength() {
        return this.actions.size();
    }

    /**
     * Метод заполняет массив. Регистрация событий.
     */
    public void fillActions(StartUI ui) {
        this.actions.add(new AddItem(0, "Add new Item."));
        this.actions.add(new ShowItems(1,"Show all items."));
        this.actions.add(new UpdateItem(2,"Edit item."));
        this.actions.add(new DeleteItem(3, "Delete item."));
        this.actions.add(new FindItemById(4, "Find item by Id."));
        this.actions.add(new FindItemsByName(5, "Find items by name."));
        this.actions.add(new ExitProgram(6,"Exit Program.", ui));
    }

    /**
     * Метод в зависимости от указанного ключа, выполняет соответствующее действие.
     * key это индекс в меню, совершенно любым он не может быть,
     * поэтому были введены исключения
     *
     * @param key ключ операции
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
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
