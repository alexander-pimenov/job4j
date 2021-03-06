package ru.job4j.tracker.start;

import ru.job4j.tracker.models.Item;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Alexander Pimenov
 * @version $Id$
 * @since 0.1
 * <p>
 * Чтобы выводить некую информацию о GC в VM options прописал команду: -verbose:gc
 * Включим расширенный вывод информации о сборках мусора: -XX:+PrintGCDetails
 * Но есть предупреждение:
 * -XX:+PrintGCDetails is deprecated. Will use -Xlog:gc* instead.
 * -Xlog:gc* Это лучшая практика для отладки GC или проблем с памятью.
 * <p>
 * Вызовем Serial Collector с параматрами для heap:
 * -XX:+UseSerialGC -Xmx12m -Xms3m -Xmn1m
 * <p>
 * Вызовем Parallel Collector с параматрами для heap:
 * -XX:+UseParallelGC -Xmx12m -Xms3m -Xmn1m
 * <p>
 * Вызовем Parallel Compacting Collector с параматрами для heap:
 * -XX:+UseParallelOldGC -Xmx12m -Xms3m -Xmn1m
 * <p>
 * Вызовем Concurrent Mark-Sweep (CMS) Collector с параматрами для heap:
 * -XX:+UseConcMarkSweepGC -Xmx12m -Xms3m -Xmn1m
 * <p>
 * Мы можем использовать такие настройки VM с записью в лог некоторых данных:
 * -XX:+UseSerialGC -Xms2m -Xmx2m -Xlog:gc*::time -Xlog:gc:C:\projects\job4j\chapter_002\gcTracker.txt
 * <p>
 * //C:\projects\job4j\chapter_002\gcTracker.txt - абсолютный путь к логу.
 */
public class StartUI {
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    private final Consumer<String> output;

    /**
     * Конструктор инициализирующий поля.
     *
     * @param input   ввод данных.
     * @param tracker хранилище заявок.
     * @param output  с помощью Consumer выбираем метод вывода информации
     */
    public StartUI(Input input, Tracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    /**
     * Основой цикл программы.
     */
    private boolean working = true;

    public void init() {

        /*MenuTracker заменили на наследника BeautyMenu чтобы выводить красивое меню.*/
        MenuTracker menu = new BeautyMenu(this.input, this.tracker, this.output);
//        MenuTracker menu = new MenuTracker(this.input, this.tracker, this.output);
        menu.fillActions(this);
        List<Integer> range = menu.getRangeOfMenu();

        do {
            menu.show();
            menu.select(input.ask("Please select number:", range));
        } while (this.working);

    }

    public void stop() {
//        if ("y".equals(this.input.ask("Do you want exit the program?(y): "))) {
//            System.out.println("Goodbye!");
//        }
        this.working = false; //основная строка для выхода из программы!
    }

    /**
     * Запускт программы.
     *
     * @param args массив строковых аргументов
     */
    public static void main(String[] args) throws InterruptedException {

        /*----------------------------------------------------------
         *
         * Этот участок кода нужен для того чтобы продемонстрировать
         * работу GC, переполнение памяти с выбросом исключения
         * java.lang.OutOfMemoryError: Java heap space
         * Нужно его разблокировать и установить для VM options такие настройки:
         * -XX:+UseSerialGC -Xms256m -Xmx256m -Xlog:gc*::time -Xlog:gc*:C:\projects\job4j\chapter_002\gcTracker.txt
         * или чтоб было меньше данных в лог файле:
         * -XX:+UseSerialGC -Xms256m -Xmx256m -Xlog:gc*::time -Xlog:gc:C:\projects\job4j\chapter_002\gcTracker.txt
         *
         * Абсолютный путь к лог файлу: C:\projects\job4j\chapter_002\gcTracker.txt
         * (может быть изменен).
         *
         * Исключение выбрасывается при 1_800_000 item.
         * При количестве item 1_750_000 исключение еще не выбрасывается.
         */
//        Thread.sleep(3000);
//        Tracker tracker = new Tracker();
//        for (int i = 0; i < 1_750_000; i++) {
//            Item item = new Item("item", String.valueOf(i));
//            tracker.add(item);
//        }
//        System.out.println(tracker.findAll().size());
//        Thread.sleep(3000);
//        final List<Item> all = tracker.findAll();
//        all.clear();
//        System.out.println(all.size());
//        System.gc();


        /* Раскомментировать и запустить для тестирования работы сборщика мусора (GC)*/
//        new StartUI(new ValidateInput(new ConsoleInput()), tracker, System.out::println).init();
        /*----------------------------------------------------------*/

        /*Для работы приложения без учета специальных настроек сборщика мусора (GC)*/
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker(), System.out::println).init();
    }
}


// Варианты предыдущие. Устаревшие.
//    /**
//     * Константа меню для добавления новой заявки.
//     */
//    private static final String ADD = "0";
//    /**
//     * Константа меню для показа всех заявок.
//     */
//    private static final String SHOW_ALL_ITEMS = "1";
//    /**
//     * Константа меню для редактирования заявки.
//     */
//    private static final String EDIT = "2";
//    /**
//     * Константа меню для удаления заявки.
//     */
//    private static final String DELETE = "3";
//    /**
//     * Константа меню для поиска заявки по Id.
//     */
//    private static final String FIND_BY_ID = "4";
//    /**
//     * Константа меню для поиска заявок по имени.
//     */
//    private static final String FIND_BY_NAME = "5";
//    /**
//     * Константа для выхода из цикла.
//     */
//    private static final String EXIT = "6";


//    /**
//     * Метод реализует добавленяи новый заявки в хранилище.
//     */
//    private void createItem() {
//        System.out.println("------------ Добавление новой заявки --------------");
//        String name = this.input.ask("Введите имя заявки :");
//        String desc = this.input.ask("Введите описание заявки :");
//        Item item = new Item(name, desc, 123L);
//        this.tracker.add(item);
//        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
//    }

//    /**
//     * Метод реализует показ всех заявок в хранилище.
//     */
//    private void showAllItems() {
//        System.out.println("------------ Показать все заявки --------------");
//        List<Item> items = this.tracker.findAll();
//        for (Item item : items) {
//            System.out.println(String.format("Name: %s Description: %s Id: %s",
//                    item.getName(), item.getDesc(), item.getId()));
//        }
//        System.out.println("------------");
//    }

//    /**
//     * Метод реализует показ редактирования заявки.
//     */
//    private void editItem() {
//        System.out.println("------------ Редактирование заявки --------------");
//        System.out.println("------------ Поиск заявки --------------");
//        String id = this.input.ask("Введите Id заявки :");
//        String name = this.input.ask("Введите изменение имени заявки :");
//        String desc = this.input.ask("Введите изменение описания заявки :");
//        Item item = new Item(name, desc, 123L);
//        if (tracker.replace(id, item)) {
//            System.out.println("-------- Заявка с Id : " + item.getId() + " изменена -------");
//        } else {
//            System.out.println("Нет заявки с таким Id.");
//        }
//        System.out.println("------------");
//    }

//    /**
//     * Метод реализует удаление заявки.
//     */
//    private void deleteItem() {
//        System.out.println("------------ Удаление заявки --------------");
//        System.out.println("------------ Поиск заявки --------------");
//        String id = this.input.ask("Введите Id заявки :");
//        Item item = tracker.findById(id);
//        if (this.tracker.delete(id)) {
//            System.out.println("------------ Ваша удаляемая заявка ------------");
//            System.out.println(String.format("Name: %s Description: %s Id: %s",
//                    item.getName(), item.getDesc(), item.getId()));
//            System.out.println("------------ Заявка удалена ------------");
//        } else {
//            System.out.println("Нет заявки с таким Id.");
//        }
//        System.out.println("------------");
//    }

//    /**
//     * Метод реализует поиск заявки по её Id.
//     */
//    private void findItemById() {
//        System.out.println("------------ Поиск заявки по её Id --------------");
//        String id = this.input.ask("Введите Id заявки :");
//        Item item = tracker.findById(id);
//        if (item != null && id.equals(item.getId())) {
//            System.out.println("------------ Ваша заявка ------------");
//            System.out.println(String.format("Name: %s Description: %s Id: %s",
//                    item.getName(), item.getDesc(), item.getId()));
//        } else {
//            System.out.println("Нет заявки с таким Id.");
//        }
//        System.out.println("------------ Конец поиска ------------");
//    }


//    /**
//     * Метод реализует поиск списка заявок по их именам.
//     */
//    private void findItemsByName() {
//        System.out.println("------------ Поиск заявки по её имени --------------");
//        String name = this.input.ask("Введите имя заявки :");
//        List<Item> items = this.tracker.findByName(name);
//        System.out.println("------------ Ваша(и) заявка(и) ------------");
//        for (Item item : items) {
//            System.out.println(String.format("Name: %s Description: %s Id: %s \n",
//                    item.getName(), item.getDesc(), item.getId()));
//        }
//        System.out.println("------------ Конец поиска ------------");
//    }

//    private void showMenu() {
//        System.out.println("Меню.");
//        System.out.println("0. Добавление новой заявки.");
//        System.out.println("1. Показать все заявки.");
//        System.out.println("2. Редактировать заявку.");
//        System.out.println("3. Удалить заявку.");
//        System.out.println("4. Найти заявку по Id.");
//        System.out.println("5. Найти заявки по имени.");
//        System.out.println("6. Выход из меню.");
//        // добавить остальные пункты меню.
//    }