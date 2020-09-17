package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

/**
 * The database is accessed through the interface.
 */
public interface Store {
    List<Employee> findBy(Predicate<Employee> filter);
}
