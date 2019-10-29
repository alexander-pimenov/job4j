package ru.job4j.stream;


import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class School {

    //метод для фильтрации списка студентов по их балам. С выбором условия по predict
    List<Student> collect(List<Student> students, Predicate<Student> predict) {
        return students.stream().filter(predict).collect(Collectors.toList());
    }

    //метод для преобразования списка студентов в Map студентов, где ключ - это фамилия, а значение - объект студент
    Map<String, Student> convertListStudentToMapBySurname(List<Student> students) {
        return students.stream().distinct().collect(Collectors.toMap(
                student -> student.getSurname(),
                student -> student
        ));
    }
}
