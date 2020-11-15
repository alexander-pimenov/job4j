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

    public User(String name) {
        this.name = name;
    }

    public User() {
    }

    /**
     * Переопредилили метод, чтобы видеть когда объект
     * будет удален из кучи.
     *
     * @throws Throwable исключение.
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.printf("Deleting (finalize) object %s%n", this); //закомментировал чтоб не выводилась лишняя информация
    }

    @Override
    public String toString() {
        return String.format("User: id=%d, name=%s", this.id, this.name);
    }
}
