package ru.job4j.lambda;

public interface Wrapper<T> {
    T get();

    void set(T value);

    boolean isEmpty();
}
