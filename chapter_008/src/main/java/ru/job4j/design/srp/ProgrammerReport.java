package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * The class responsible for reporting to the programmer department.
 */

public class ProgrammerReport implements Report {
    private Store store;
    private static final String LN = System.lineSeparator();

    public ProgrammerReport(Store store) {
        this.store = store;
    }

    /**
     * A method that generates an HTML report for the programmers department.
     *
     * @param filter filter.
     * @return report.
     */
    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<!DOCTYPE html>").append(LN)
                .append("<html>").append(LN)
                .append("<head>").append(LN)
                .append("<meta charset=\"utf-8\">").append(LN)
                .append("<title>Employee report for programmers</title>").append(LN)
                .append("</head>").append(LN)
                .append("<body>").append(LN)
                .append("<h1>Employee report</h1>").append(LN)
                .append("<table>").append(LN)
                .append("<tr>")
                .append("<th>Name</th>").append("<th>Hired</th>").append("<th>Fired</th>").append("<th>Salary</th>")
                .append("</tr>").append(LN);
        for (Employee employee : store.findBy(filter)) {
            text.append("<tr>").append(String.format("<td>%s</td>", employee.getName()))
                    .append(String.format("<td>%s</td>", DateConverter.convert(employee.getHired())))
                    .append(String.format("<td>%s</td>", DateConverter.convert(employee.getFired())))
                    .append(String.format("<td>%s руб.</td>", employee.getSalary()))
                    .append("</tr>").append(LN);
        }
        text.append("</table>").append(LN)
                .append("</body>").append(LN)
                .append("</html>").append(LN);
        return text.toString();
    }
}