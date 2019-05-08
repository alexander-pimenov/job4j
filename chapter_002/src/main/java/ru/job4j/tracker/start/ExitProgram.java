package ru.job4j.tracker.start;

public class ExitProgram implements UserAction {
    @Override
    public int key() {
        return 6;
    }

    @Override
    public void execute(Input input, Tracker tracker) {

    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Exit Program.");
    }
}
