package ru.job4j.design.srp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class storage for employees.
 */

public class MemStore implements Store {
    private final List<Employee> employees = new ArrayList<>();

    /**
     * Method for adding employees to the list.
     *
     * @param em employee.
     */
    public void add(Employee em) {
        employees.add(em);
    }

    /**
     * Method that searches for employees by condition.
     *
     * @param filter filter.
     * @return list of employees.
     */
    @Override
    public List<Employee> findBy(Predicate<Employee> filter) {
        return employees.stream().filter(filter)
                .collect(Collectors.toList());
    }
}
