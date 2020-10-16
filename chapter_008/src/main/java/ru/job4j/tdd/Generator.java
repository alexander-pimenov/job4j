package ru.job4j.tdd;

import java.util.Map;

/**
 * Интерфейс описывающий генератор
 * строк, согласно принимаемому шаблону
 * и паре ключ-значение.
 */
public interface Generator {
    /**
     * Hello world, ${name}
     * @param template -starting String where we replace template ${...}
     * @param args Map, where key equals template, value replaced template
     */
    String produce(String template, Map<String, String> args);
}
