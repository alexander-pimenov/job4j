package ru.job4j.tracker.start;

import ru.job4j.tracker.models.Item;

/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";
    /**
     * Константа меню для показа всех заявок.
     */
    private static final String SHOW_ALL_ITEMS = "1";
    /**
     * Константа меню для редактирования заявки.
     */
    private static final String EDIT = "2";
    /**
     * Константа меню для удаления заявки.
     */
    private static final String DELETE = "3";
    /**
     * Константа меню для поиска заявки по Id.
     */
    private static final String FIND_BY_ID = "4";
    /**
     * Константа меню для поиска заявок по имени.
     */
    private static final String FIND_BY_NAME = "5";
    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструктор инициализирующий поля.
     *
     * @param input   ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {

        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        int[] range = new int[menu.getActionsLentgh()];
        for (int i = 0; i < menu.getActionsLentgh(); i++) {
            range[i] = i;
        }
//        List<Integer> range = new ArrayList<>();
//        for (int i=0; i<menu.getActionsLentgh(); i++){
//            range.add(i);
//        }
        do {
            menu.show();
            menu.select(input.ask("select:", range));
        } while (!"y".equals(this.input.ask("Exit?(y): ")));

//        boolean exit = false;
//        while (!exit) {
//            this.showMenu();
//            String answer = this.input.ask("Введите пункт меню : ");
//            if (ADD.equals(answer)) {
//                //добавление заявки вынесено в отдельный метод.
//                this.createItem();
//            } else if (SHOW_ALL_ITEMS.equals(answer)) {
//                this.showAllItems();
//            } else if (EDIT.equals(answer)) {
//                this.editItem();
//
//            } else if (DELETE.equals(answer)) {
//                this.deleteItem();
//
//            } else if (FIND_BY_ID.equals(answer)) {
//                this.findItemById();
//
//            } else if (FIND_BY_NAME.equals(answer)) {
//                this.findItemsByName();
//
//            } else if (EXIT.equals(answer)) {
//                exit = true;
//            }
//        }
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc, 123L);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
    }

    /**
     * Метод реализует показ всех заявок в хранилище.
     */
    private void showAllItems() {
        System.out.println("------------ Показать все заявки --------------");
        Item[] items = this.tracker.findAll();
        for (Item item : items) {
            System.out.println(String.format("Name: %s Description: %s Id: %s",
                    item.getName(), item.getDesc(), item.getId()));
        }
        System.out.println("------------");
    }

    /**
     * Метод реализует показ редактирования заявки.
     */
    private void editItem() {
        System.out.println("------------ Редактирование заявки --------------");
        System.out.println("------------ Поиск заявки --------------");
        String id = this.input.ask("Введите Id заявки :");
        String name = this.input.ask("Введите изменение имени заявки :");
        String desc = this.input.ask("Введите изменение описания заявки :");
        Item item = new Item(name, desc, 123L);
        if (tracker.replace(id, item)) {
            System.out.println("-------- Заявка с Id : " + item.getId() + " изменена -------");
        } else {
            System.out.println("Нет заявки с таким Id.");
        }
        System.out.println("------------");
    }

    /**
     * Метод реализует удаление заявки.
     */
    private void deleteItem() {
        System.out.println("------------ Удаление заявки --------------");
        System.out.println("------------ Поиск заявки --------------");
        String id = this.input.ask("Введите Id заявки :");
        Item item = tracker.findById(id);
        if (this.tracker.delete(id)) {
            System.out.println("------------ Ваша удаляемая заявка ------------");
            System.out.println(String.format("Name: %s Description: %s Id: %s",
                    item.getName(), item.getDesc(), item.getId()));
            System.out.println("------------ Заявка удалена ------------");
        } else {
            System.out.println("Нет заявки с таким Id.");
        }
        System.out.println("------------");
    }

    /**
     * Метод реализует поиск заявки по её Id.
     */
    private void findItemById() {
        System.out.println("------------ Поиск заявки по её Id --------------");
        String id = this.input.ask("Введите Id заявки :");
        Item item = tracker.findById(id);
        if (item != null && id.equals(item.getId())) {
            System.out.println("------------ Ваша заявка ------------");
            System.out.println(String.format("Name: %s Description: %s Id: %s",
                    item.getName(), item.getDesc(), item.getId()));
        } else {
            System.out.println("Нет заявки с таким Id.");
        }
        System.out.println("------------ Конец поиска ------------");
    }


    /**
     * Метод реализует поиск списка заявок по их именам.
     */
    private void findItemsByName() {
        System.out.println("------------ Поиск заявки по её имени --------------");
        String name = this.input.ask("Введите имя заявки :");
        Item[] items = this.tracker.findByName(name);
        System.out.println("------------ Ваша(и) заявка(и) ------------");
        for (Item item : items) {
            System.out.println(String.format("Name: %s Description: %s Id: %s \n",
                    item.getName(), item.getDesc(), item.getId()));
        }
        System.out.println("------------ Конец поиска ------------");
    }


    private void showMenu() {
        System.out.println("Меню.");
        System.out.println("0. Добавление новой заявки.");
        System.out.println("1. Показать все заявки.");
        System.out.println("2. Редактировать заявку.");
        System.out.println("3. Удалить заявку.");
        System.out.println("4. Найти заявку по Id.");
        System.out.println("5. Найти заявки по имени.");
        System.out.println("6. Выход из меню.");
        // добавить остальные пункты меню.
    }

    /**
     * Запускт программы.
     *
     * @param args массив строковых аргументов
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(), new Tracker()).init();
    }
}