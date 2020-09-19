package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

public class XMLReport implements Report {
    private Store store;
    private static final String LN = System.lineSeparator();

    public XMLReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        final List<Employee> listEmployees = store.findBy(filter);
        text.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(LN);
        if (listEmployees.size() == 0) {
            text.append(LN);
        } else if (listEmployees.size() == 1) {
            text.append("<employee>").append(LN)
                    .append("\t<name>").append(listEmployees.get(0).getName()).append("</name>").append(LN)
                    .append("\t<hired>").append(DateConverter.convert(listEmployees.get(0).getHired())).append("</hired").append(LN)
                    .append("\t<fired>").append(DateConverter.convert(listEmployees.get(0).getFired())).append("</fired>").append(LN)
                    .append("\t<salary>").append(listEmployees.get(0).getSalary()).append("</salary>").append(LN)
                    .append("</employee>").append(LN);
        } else {
            text.append("<employees>").append(LN);
            for (int i = 0; i < listEmployees.size(); i++) {
                text.append("\t<employee>").append(LN)
                        .append("\t\t<name>").append(listEmployees.get(i).getName()).append("</name>").append(LN)
                        .append("\t\t<hired>").append(DateConverter.convert(listEmployees.get(i).getHired())).append("</hired>").append(LN)
                        .append("\t\t<fired>").append(DateConverter.convert(listEmployees.get(i).getFired())).append("</fired>").append(LN)
                        .append("\t\t<salary>").append(listEmployees.get(i).getSalary()).append("</salary>").append(LN)
                        .append("\t</employee>").append(LN);
            }
        }
        text.append("</employees>").append(LN);
        return text.toString();
    }
}
