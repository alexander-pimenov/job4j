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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEquals that = (UserEquals) o;
        return children == that.children &&
                Objects.equals(name, that.name) &&
                Objects.equals(birthday, that.birthday);
    }
}
