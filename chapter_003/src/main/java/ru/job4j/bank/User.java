package ru.job4j.bank;

import java.util.Objects;

public class User implements Comparable<User> {
    private String name;
    private String passport;

    public User() {

    }

    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name)
                && Objects.equals(passport, user.passport);

        /*или такой return:
        return name.equals(user.name) && passport.equals(user.passport);*/
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, passport);
    }

    /*
    * возможна и такая реализация hashCode()
    *
    * @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + passport.hashCode();
        return result;
    }*/

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", passport='" + passport + '\''
                + '}';
    }

    @Override
    public int compareTo(User user) {
        int result = this.name.compareTo(user.name);

        return result == 0 ? this.passport.compareTo(user.passport) : result;
    }
}
