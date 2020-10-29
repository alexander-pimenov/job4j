package ru.job4j.gc;

public class User {
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(int id) {
        this.id = id;
    }

    public User() {
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.printf("Deleting object %s%n", this);
    }

    @Override
    public String toString() {
        return String.format("User: id=%d, name=%s", this.id, this.name);
    }
}
