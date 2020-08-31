package ru.job4j.trackerSql;

import java.util.List;

public class ValidateInput implements Input{

    private final Input input;

    public ValidateInput(final Input input) {
        this.input = input;
    }

    /**
     * Метод запрашивает пункт меню.
     */
    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    /**
     * Метод обрабатывает ошибки при выборе пункта меню.
     */
    @Override
    public int ask(String question, List<Integer> range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please select key from menu.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter correct data again.");
            }
        } while (invalid);
        return value;
    }
}
