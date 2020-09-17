package ru.job4j.design.srp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 */

public class MemStore implements Store {
    private final List<Employee> employees = new ArrayList<>();

    /**
     *
     * @param em
     */
    public void add(Employee em) {
        employees.add(em);
    }

    /**
     *
     * @param filter
     * @return
     */
    @Override
    public List<Employee> findBy(Predicate<Employee> filter) {
        return employees.stream().filter(filter)
                .collect(Collectors.toList());
    }
}
