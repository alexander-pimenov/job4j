package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

public class JSONReport implements Report {
    private Store store;
    private static final String LN = System.lineSeparator();

    public JSONReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        final List<Employee> listEmployees = store.findBy(filter);
        if (listEmployees.size() == 0) {
            text.append("{}").append(LN);
        } else if (listEmployees.size() == 1) {
            text.append("{").append(LN)
                    .append("\"name\": ").append("\"").append(listEmployees.get(0).getName()).append("\",").append(LN)
                    .append("\"hired\": ").append("\"").append(DateConverter.convert(listEmployees.get(0).getHired())).append("\",").append(LN)
                    .append("\"fired\": ").append("\"").append(DateConverter.convert(listEmployees.get(0).getFired())).append("\",").append(LN)
                    .append("\"salary\": ").append(listEmployees.get(0).getSalary()).append(LN)
                    .append("}").append(LN);
        } else {
            text.append("[").append(LN);
            for (int i = 0; i < listEmployees.size(); i++) {
                text.append("{").append(LN)
                        .append("\"name\": ").append("\"").append(listEmployees.get(i).getName()).append("\",").append(LN)
                        .append("\"hired\": ").append("\"").append(DateConverter.convert(listEmployees.get(i).getHired())).append("\",").append(LN)
                        .append("\"fired\": ").append("\"").append(DateConverter.convert(listEmployees.get(i).getFired())).append("\",").append(LN)
                        .append("\"salary\": ").append(listEmployees.get(i).getSalary()).append(LN);
                if (i == (listEmployees.size() - 1)) {
                    text.append("}").append(LN);
                } else {
                    text.append("},").append(LN);
                }
            }
            text.append("]").append(LN);
        }
        return text.toString();
    }
}
