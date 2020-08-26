package ru.job4j.tracker_sql;

import java.util.List;

public interface Input {
    String ask(String question);

    int ask(String question, List<Integer> range);
}
