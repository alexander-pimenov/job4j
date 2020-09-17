package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * The class responsible for the report for the HR-department.
 */

public class HRReport implements Report {

    private Store store;
    private static final String LN = System.lineSeparator();


    public HRReport(Store store) {
        this.store = store;
    }

    /**
     * The method displays employees in descending order of salary
     * and removes the hired and fired date fields.
     *
     * @param filter filter.
     * @return report.
     */
    @Override
    public String generate(Predicate<Employee> filter) {
        final List<Employee> employees = sortEmployees(filter);
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;");
        text.append(LN);
        for (Employee employee : employees) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(LN);
        }
        return text.toString();
    }

    /**
     * The method sorts the list of employees in descending order of salary.
     *
     * @param filter filter.
     * @return list of employees.
     */
    private List<Employee> sortEmployees(Predicate<Employee> filter) {
        return store.findBy(filter)
                .stream()
                .sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()))
                .collect(Collectors.toList());
    }
}
