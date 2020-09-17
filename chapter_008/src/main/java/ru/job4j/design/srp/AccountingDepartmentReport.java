package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * Reporting class for the Accounting Department.
 * Rubles are converted into dollars.
 */

public class AccountingDepartmentReport implements Report {

    private Store store;
    private static final String LN = System.lineSeparator();

    public AccountingDepartmentReport(Store store) {
        this.store = store;
    }

    /**
     * Method that generates a report in which rubles are converted into dollars.
     *
     * @param filter filter.
     * @return report.
     */
    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;").append(LN);
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(DateConverter.convert(employee.getHired())).append(";")
                    .append(DateConverter.convert(employee.getFired())).append(";")
                    .append(MoneyConverter.inDollar(employee.getSalary())).append(";")
                    .append(LN);
        }
        return text.toString();
    }
}
