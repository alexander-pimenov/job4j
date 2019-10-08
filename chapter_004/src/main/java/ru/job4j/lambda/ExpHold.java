package ru.job4j.lambda;

public class ExpHold<T> implements Wrapper<T> {

    private T value;

    @Override
    public T get() {
        return this.value;
    }

    @Override
    public void set(T value) {
        this.value = value;
    }

    @Override
    public boolean isEmpty() {
        return this.value == null;
    }
}
