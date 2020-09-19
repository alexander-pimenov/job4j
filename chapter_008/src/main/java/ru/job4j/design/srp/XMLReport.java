package ru.job4j.design.srp;

import java.util.function.Predicate;

public class XMLReport implements Report {
    private Store store;
    private static final String LN = System.lineSeparator();

    public XMLReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return null;
    }
}
