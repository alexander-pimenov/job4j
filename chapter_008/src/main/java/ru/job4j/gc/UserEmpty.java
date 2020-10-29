package ru.job4j.gc;

public class UserEmpty {
    public UserEmpty() {
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.printf("Deleting object %s%n", this);
    }

    @Override
    public String toString() {
        return "UserEmpty{}";
    }
}
