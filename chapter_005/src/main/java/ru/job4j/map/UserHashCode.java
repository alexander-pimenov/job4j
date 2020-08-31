package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

/**
 * Класс, в котором переопределен только hashCode()
 */

public class UserHashCode {
    private String name;
    private int children;
    private Calendar birthday;

    public UserHashCode() {
    }

    public UserHashCode(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    /**
     * Переопределяем метод hashCode(),
     * используя класс Objects и поля нашего
     * класса User: name, children, birthday
     * @return числовое соответствие нашего объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}
