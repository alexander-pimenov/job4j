package ru.job4j.design.srp.sequencegenerator;

import java.util.List;

public interface SequenceGenerator<T> {
    List<T> generate(int size);

//    void print(List<T> numbers);
}
