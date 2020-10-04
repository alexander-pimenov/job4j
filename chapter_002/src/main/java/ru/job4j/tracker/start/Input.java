package ru.job4j.tracker.start;

/**
 * Этот интерфейс расширяет SimpleInput, InputRange
 * тем самым соблюдаем принцип ISP (из SOLID) -
 * разбиение толстых интерфейсов на более мелкие,
 * но нужно учитывать связанность методов в интерфейсе.
 */
public interface Input extends SimpleInput, InputRange {
//    String ask(String question);

//    int ask(String question, List<Integer> range);
}
