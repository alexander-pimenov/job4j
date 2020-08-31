package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

/**
 * Класс, в котором переопределен только equals()
 */

public class UserEquals {
    private String name;
    private int children;
    private Calendar birthday;

    public UserEquals(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserEquals that = (UserEquals) o;
        return children == that.children
                && Objects.equals(name, that.name)
                && Objects.equals(birthday, that.birthday);
    }

    /**
     * Метод берем из нашего супер класса, т.е. класса Object,
     * не преопределяем его, например, как в этом примере:
     * Objects.hash(name, children, birthday);
     * а просто вызываем нативный метод из супер-класса.
     * @return int числовое соответствие объекта
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
