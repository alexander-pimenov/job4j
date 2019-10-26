package ru.job4j.stream;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.List;

public class SchoolTest {

    /*Создаем список учеников Student*/
    private final List<Student> students = List.of(
            new Student(25), new Student(35),
            new Student(45), new Student(55),
            new Student(65), new Student(75),
            new Student(85), new Student(95));

    private final School school = new School();

    /*Метод для получения списка учеников для 10А, диапазон балов [70 : 100]*/
    @Test
    public void whenCollectStudentsToClassA() {
        List<Student> classA = school.collect(students, predict -> predict.getScore() >= 70 && predict.getScore() <= 100);
        List<Student> expected = List.of(new Student(75), new Student(85), new Student(95));
        assertThat(classA, is(expected));
    }

    /*Метод для получения списка учеников для 10Б, диапазон балов [50 : 70]*/
    @Test
    public void whenCollectStudentsToClassB() {
        List<Student> classB = school.collect(students, predict -> predict.getScore() >= 50 && predict.getScore() < 70);
        List<Student> expected = List.of(new Student(55), new Student(65));
        assertThat(classB, is(expected));
    }

    /*Метод для получения списка учеников для 10В, диапазон балов [0 : 50]*/
    @Test
    public void whenCollectStudentsToClassC() {
        List<Student> classC = school.collect(students, predict -> predict.getScore() >= 0 && predict.getScore() < 50);
        List<Student> expected = List.of(new Student(25), new Student(35), new Student(45));
        assertThat(classC, is(expected));
    }
}
