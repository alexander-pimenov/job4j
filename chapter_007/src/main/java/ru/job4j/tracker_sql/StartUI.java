package ru.job4j.tracker_sql;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Alexander Pimenov
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Store tracker;
//    private final MemTracker tracker;

    private final Consumer<String> output;

    /**
     * Конструктор инициализирующий поля.
     *
     * @param input   ввод данных.
     * @param tracker хранилище заявок.
     * @param output  с помощью Consumer выбираем метод вывода информации
     */
    public StartUI(Input input, Store tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

// Старый вариант
//    public StartUI(Input input, MemTracker tracker) {
//        this.input = input;
//        this.tracker = tracker;
//    }

    /**
     * Основой цикл программы.
     */
    private boolean working = true;

    public void init() {

        MenuTracker menu = new MenuTracker(this.input, this.tracker, this.output);
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
    public static void main(String[] args) {

        new StartUI(new ValidateInput(
                new ConsoleInput()),
                new MemTracker(), System.out::println).init();
    }
}