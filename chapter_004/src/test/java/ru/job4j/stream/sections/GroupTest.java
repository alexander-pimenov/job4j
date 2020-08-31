package ru.job4j.stream.sections;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GroupTest {
    @Test
    public void groupStudentsBySections() {
        //Наши студенты
        Student ivanov = new Student("Ivanov", Set.of("Biology", "Aeromodelling", "Tennis"));
        Student petrov = new Student("Petrov", Set.of("Tennis", "Aeromodelling", "Badminton"));
        Student sidorov = new Student("Sidorov", Set.of("Badminton", "Robotics", "Biology"));
        Student nikolaev = new Student("Nikolaev", Set.of("Biology", "Fencing", "Philosophy"));
        Student orlova = new Student("Orlova", Set.of("Philosophy", "Tennis", "Robotics"));
        Student peterson = new Student("Peterson", Set.of("Philosophy", "Badminton"));
        Student proctor = new Student("Proctor", Set.of("Tennis", "Biology", "Fencing"));

        //Заполним список студентов
        List<Student> students = Arrays.asList(ivanov, petrov, sidorov, nikolaev, orlova, peterson, proctor);

        Group group = new Group();

        Map<String, Set<String>> resultGrouping = group.sections(students);

        Map<String, Set<String>> expected = new HashMap<>();
        expected.put("Tennis", Set.of("Petrov, Proctor, Orlova, Ivanov"));
        expected.put("Aeromodelling", Set.of("Petrov, Ivanov"));
        expected.put("Fencing", Set.of("Proctor, Nikolaev"));
        expected.put("Badminton", Set.of("Petrov, Peterson, Sidorov"));
        expected.put("Biology", Set.of("Proctor, Nikolaev, Ivanov, Sidorov"));
        expected.put("Philosophy", Set.of("Orlova, Nikolaev, Peterson"));
        expected.put("Robotics", Set.of("Orlova, Sidorov"));

        assertThat(resultGrouping.toString(), is(expected.toString()));
    }
}