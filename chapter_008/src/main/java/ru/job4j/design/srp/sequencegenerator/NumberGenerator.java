package ru.job4j.design.srp.sequencegenerator;

/*
 * Генератор чисел.
 */
public interface NumberGenerator<T> {
    T generate();
}
