package ru.job4j.stream;


import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    //метод возвращает список студентов у которых балл аттестата больше bound.
    //в методе использовали takeWhile()
    List<Student> levelOf(List<Student> students, int bound){
        return students.stream()
                .sorted(Comparator.comparing(Student::getScore).reversed())//reversed чтобы верхушка была больше низа
                .flatMap(Stream::ofNullable)
                .takeWhile(student->student.getScore()>=bound)
                .collect(Collectors.toList());
    }

    //метод возвращает список студентов у которых балл аттестата больше bound.
    //в методе использовали dropWhile()
    List<Student> levelOfWithDrop(List<Student> students, int bound) {

        return students.stream()
                .sorted()
                .flatMap(Stream::ofNullable)
                .dropWhile(v -> v.getScore() < bound)
                .collect(Collectors.toList());
    }
    //метод возвращает список студентов у которых балл аттестата больше bound.
    //в методе использовали filter()
    List<Student> levelOfWithFilter(List<Student> students, int bound) {
        return students.stream()
                .sorted()
                .flatMap(Stream::ofNullable)
                .filter(student -> student.getScore() >= bound)
                .collect(Collectors.toList());
    }

}
