package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

/**
 * Класс, в котором переопределены оба метода и equals() и hashCode()
 */

public class UserHashAndEquals {
    private String name;
    private int children;
    private Calendar birthday;

    public UserHashAndEquals(String name, int children, Calendar birthday) {
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
        UserHashAndEquals that = (UserHashAndEquals) o;
        return children == that.children
                && Objects.equals(name, that.name)
                && Objects.equals(birthday, that.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}
