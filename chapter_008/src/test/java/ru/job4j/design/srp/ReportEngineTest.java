package ru.job4j.design.srp;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Alex", now, now, 200);
        Employee worker3 = new Employee("Petr", now, now, 300);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        ReportEngine engine = new ReportEngine(store);
//        System.out.println(engine.generate(em -> true));
        StringBuilder expect = new StringBuilder();
        expect.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getHired()).append(";")
                .append(worker1.getFired()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getHired()).append(";")
                .append(worker2.getFired()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker3.getName()).append(";")
                .append(worker3.getHired()).append(";")
                .append(worker3.getFired()).append(";")
                .append(worker3.getSalary()).append(";")
                .append(System.lineSeparator());


        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHRGenerated() {
        final String LN = System.lineSeparator();
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Alex", now, now, 200);
        Employee worker3 = new Employee("Petr", now, now, 300);
        Employee worker4 = new Employee("Don", now, now, 350);
        Employee worker5 = new Employee("Felix", now, now, 250);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        store.add(worker4);
        store.add(worker5);
        final HRReport hrReport = new HRReport(store);
//        System.out.println(hrReport.generate(em -> true));
        StringBuilder expect = new StringBuilder();
        expect.append("Name; Salary;")
                .append(LN)
                .append(worker4.getName()).append(";")
                .append(worker4.getSalary()).append(";")
                .append(LN)
                .append(worker3.getName()).append(";")
                .append(worker3.getSalary()).append(";")
                .append(LN)
                .append(worker5.getName()).append(";")
                .append(worker5.getSalary()).append(";")
                .append(LN)
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(LN)
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(LN);
        assertThat(hrReport.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenProgrammerGenerated() {
        final String LN = System.lineSeparator();
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Alex", now, now, 200);
        Employee worker3 = new Employee("Petr", now, now, 300);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        final ProgrammerReport programmerReport = new ProgrammerReport(store);
//        System.out.println(programmerReport.generate(em -> true));
        StringBuilder expect = new StringBuilder();
        expect.append("<!DOCTYPE html>").append(LN)
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
                .append("</tr>").append(LN)
                .append("<tr>").append(String.format("<td>%s</td>", worker1.getName()))
                .append(String.format("<td>%s</td>", DateConverter.convert(worker1.getHired())))
                .append(String.format("<td>%s</td>", DateConverter.convert(worker1.getFired())))
                .append(String.format("<td>%s руб.</td>", worker1.getSalary()))
                .append("</tr>").append(LN)
                .append("<tr>").append(String.format("<td>%s</td>", worker2.getName()))
                .append(String.format("<td>%s</td>", DateConverter.convert(worker2.getHired())))
                .append(String.format("<td>%s</td>", DateConverter.convert(worker2.getFired())))
                .append(String.format("<td>%s руб.</td>", worker2.getSalary()))
                .append("</tr>").append(LN)
                .append("<tr>").append(String.format("<td>%s</td>", worker3.getName()))
                .append(String.format("<td>%s</td>", DateConverter.convert(worker3.getHired())))
                .append(String.format("<td>%s</td>", DateConverter.convert(worker3.getFired())))
                .append(String.format("<td>%s руб.</td>", worker3.getSalary()))
                .append("</tr>").append(LN)
                .append("</table>").append(LN)
                .append("</body>").append(LN)
                .append("</html>").append(LN);
        assertThat(programmerReport.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenAccountingDepartmentGenerated() {
        final String LN = System.lineSeparator();
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Alex", now, now, 200);
        Employee worker3 = new Employee("Petr", now, now, 300);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        final AccountingDepartmentReport accountingDepartmentReport = new AccountingDepartmentReport(store);
//        System.out.println(accountingDepartmentReport.generate(em -> true));
        StringBuilder expect = new StringBuilder();
        expect.append("Name; Hired; Fired; Salary;")
                .append(LN)
                .append(worker1.getName()).append(";")
                .append(DateConverter.convert(worker1.getHired())).append(";")
                .append(DateConverter.convert(worker1.getFired())).append(";")
                .append("1,43 $").append(";")
                .append(LN)
                .append(worker2.getName()).append(";")
                .append(DateConverter.convert(worker2.getHired())).append(";")
                .append(DateConverter.convert(worker2.getFired())).append(";")
                .append("2,86 $").append(";")
                .append(LN)
                .append(worker3.getName()).append(";")
                .append(DateConverter.convert(worker3.getHired())).append(";")
                .append(DateConverter.convert(worker3.getFired())).append(";")
                .append("4,29 $").append(";")
                .append(LN);
        assertThat(accountingDepartmentReport.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenListEmployeesEmptyJSONGenerated() {
        final String LN = System.lineSeparator();
        MemStore store = new MemStore();
        final JSONReport jsonReport = new JSONReport(store);
//        System.out.println(jsonReport.generate(em -> true));
        StringBuilder expect = new StringBuilder();
        expect.append("{}").append(LN);
        assertThat(jsonReport.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenListEmployeesHasOneEmployeeJSONGenerated() {
        final String LN = System.lineSeparator();
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        store.add(worker1);
        final JSONReport jsonReport = new JSONReport(store);
//        System.out.println(jsonReport.generate(em -> true));
        StringBuilder expect = new StringBuilder();
        expect.append("{")
                .append(LN)
                .append("\"name\": ").append("\"").append(worker1.getName()).append("\"").append(",").append(LN)
                .append("\"hired\": ").append("\"").append(DateConverter.convert(worker1.getHired())).append("\"").append(",").append(LN)
                .append("\"fired\": ").append("\"").append(DateConverter.convert(worker1.getFired())).append("\"").append(",").append(LN)
                .append("\"salary\": ").append(worker1.getSalary()).append(LN)
                .append("}").append(LN);
        assertThat(jsonReport.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenListEmployeesHasManyEmployeesJSONGenerated() {
        final String LN = System.lineSeparator();
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Alex", now, now, 200);
        Employee worker3 = new Employee("Petr", now, now, 300);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        final JSONReport jsonReport = new JSONReport(store);
//        System.out.println(jsonReport.generate(em -> true));
        StringBuilder expect = new StringBuilder();
        expect.append("[").append(LN)
                .append("{").append(LN)
                .append("\"name\": ").append("\"").append(worker1.getName()).append("\"").append(",").append(LN)
                .append("\"hired\": ").append("\"").append(DateConverter.convert(worker1.getHired())).append("\"").append(",").append(LN)
                .append("\"fired\": ").append("\"").append(DateConverter.convert(worker1.getFired())).append("\"").append(",").append(LN)
                .append("\"salary\": ").append(worker1.getSalary()).append(LN)
                .append("},").append(LN)
                .append("{").append(LN)
                .append("\"name\": ").append("\"").append(worker2.getName()).append("\"").append(",").append(LN)
                .append("\"hired\": ").append("\"").append(DateConverter.convert(worker2.getHired())).append("\"").append(",").append(LN)
                .append("\"fired\": ").append("\"").append(DateConverter.convert(worker2.getFired())).append("\"").append(",").append(LN)
                .append("\"salary\": ").append(worker2.getSalary()).append(LN)
                .append("},").append(LN)
                .append("{").append(LN)
                .append("\"name\": ").append("\"").append(worker3.getName()).append("\"").append(",").append(LN)
                .append("\"hired\": ").append("\"").append(DateConverter.convert(worker3.getHired())).append("\"").append(",").append(LN)
                .append("\"fired\": ").append("\"").append(DateConverter.convert(worker3.getFired())).append("\"").append(",").append(LN)
                .append("\"salary\": ").append(worker3.getSalary()).append(LN)
                .append("}").append(LN)
                .append("]").append(LN);
        assertThat(jsonReport.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenListEmployeesIsEmptyXMLGenerated() {
        final String LN = System.lineSeparator();
        MemStore store = new MemStore();
        final XMLReport xmlReport = new XMLReport(store);
//        System.out.println(xmlReport.generate(em -> true));
        StringBuilder expect = new StringBuilder();
        expect.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(LN);
        assertThat(xmlReport.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenListEmployeesHasOneEmployeeXMLGenerated() {
        final String LN = System.lineSeparator();
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        store.add(worker1);
        final XMLReport xmlReport = new XMLReport(store);
//        System.out.println(xmlReport.generate(em -> true));
        StringBuilder expect = new StringBuilder();
        expect.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(LN)
                .append("<employee>").append(LN)
                .append("\t<name>").append(worker1.getName()).append("</name>").append(LN)
                .append("\t<hired>").append(DateConverter.convert(worker1.getHired())).append("</hired").append(LN)
                .append("\t<fired>").append(DateConverter.convert(worker1.getFired())).append("</fired>").append(LN)
                .append("\t<salary>").append(worker1.getSalary()).append("</salary>").append(LN)
                .append("</employee>").append(LN);
        assertThat(xmlReport.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenListEmployeesHasManyEmployeesXMLGenerated() {
        final String LN = System.lineSeparator();
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Alex", now, now, 200);
        Employee worker3 = new Employee("Petr", now, now, 300);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        final XMLReport xmlReport = new XMLReport(store);
//        System.out.println(xmlReport.generate(em -> true));
        StringBuilder expect = new StringBuilder();
        expect.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(LN)
                .append("<employees>").append(LN)
                .append("\t<employee>").append(LN)
                .append("\t\t<name>").append(worker1.getName()).append("</name>").append(LN)
                .append("\t\t<hired>").append(DateConverter.convert(worker1.getHired())).append("</hired>").append(LN)
                .append("\t\t<fired>").append(DateConverter.convert(worker1.getFired())).append("</fired>").append(LN)
                .append("\t\t<salary>").append(worker1.getSalary()).append("</salary>").append(LN)
                .append("\t</employee>").append(LN)
                .append("\t<employee>").append(LN)
                .append("\t\t<name>").append(worker2.getName()).append("</name>").append(LN)
                .append("\t\t<hired>").append(DateConverter.convert(worker2.getHired())).append("</hired>").append(LN)
                .append("\t\t<fired>").append(DateConverter.convert(worker2.getFired())).append("</fired>").append(LN)
                .append("\t\t<salary>").append(worker2.getSalary()).append("</salary>").append(LN)
                .append("\t</employee>").append(LN)
                .append("\t<employee>").append(LN)
                .append("\t\t<name>").append(worker3.getName()).append("</name>").append(LN)
                .append("\t\t<hired>").append(DateConverter.convert(worker3.getHired())).append("</hired>").append(LN)
                .append("\t\t<fired>").append(DateConverter.convert(worker3.getFired())).append("</fired>").append(LN)
                .append("\t\t<salary>").append(worker3.getSalary()).append("</salary>").append(LN)
                .append("\t</employee>").append(LN)
                .append("</employees>").append(LN);
        assertThat(xmlReport.generate(em -> true), is(expect.toString()));
    }
}