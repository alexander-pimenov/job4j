package ru.job4j.user;

public class User implements Comparable<User> {

    private int id;
    private String name;
    private int age;
    private String city;

    public User(int id, String name, int age, String city) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public User(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{"
                + "id="
                + id + ", name='"
                + name + '\''
                + ", age=" + age
                + ", city='" + city + '\''
                + '}' + "\n";
    }

    //для сравнения User по возрасту, если возраст одинаков, то сравниваем по имени
    @Override
    public int compareTo(User o) {
        int result = Integer.compare(this.age, o.age);
        if (result == 0) {
            result = this.name.compareTo(o.name);
        }
        return result;
    }
}
