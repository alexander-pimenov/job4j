package ru.job4j.tracker.start;

public interface Input {
    String ask(String question);

    int ask(String question, int[] range);
}
