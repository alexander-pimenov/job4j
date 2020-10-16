package ru.job4j.tdd;

import java.util.Map;

/**
 * Интерфейс описывающий генератор
 * строк, согласно принимаемому шаблону
 * и паре ключ-значение.
 */
public interface Generator {
    String produce(String template, Map<String, String> args);
}
