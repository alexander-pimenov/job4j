package ru.job4j.tracker.start;

import java.util.function.Consumer;

public class ExitProgram extends BaseAction {

    private final StartUI ui;

    public ExitProgram(int key, String name, StartUI ui) {
        super(key, name);
        this.ui = ui;
    }

    @Override
    public void execute(Input input, Tracker tracker, Consumer<String> output) {

        System.out.println("The selected menu item is Exit.");
        this.ui.stop();
    }
}