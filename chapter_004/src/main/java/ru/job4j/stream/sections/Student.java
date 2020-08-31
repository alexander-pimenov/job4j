package ru.job4j.stream.sections;

import java.util.Objects;
import java.util.Set;

class Student implements Comparable<Student> {

    private String name;
    private Set<String> units;

    Student(String name, Set<String> units) {
        this.name = name;
        this.units = units;
    }

    String getName() {
        return name;
    }

    Set<String> getUnits() {
        return units;
    }

    @Override
    public int compareTo(Student o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\''
                + ", units=" + units + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(name, student.name)
                && Objects.equals(units, student.units);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, units);
    }
}
