package ru.job4j.stream;

import java.util.Objects;

public class Student implements Comparable<Student> {

    private String surname;
    private int score;

    Student(int score) {
        this.score = score;
    }

    public Student(String surname, int score) {
        this.surname = surname;
        this.score = score;
    }

    public String getSurname() {
        return surname;
    }

    int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Student{" + "surname='" + surname + '\''
                + ", score=" + score + '}';
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
        return score == student.score
                && Objects.equals(surname, student.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, score);
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(this.score, o.score);
    }

    //    @Override
//    public String toString() {
//        return "Student{" +
//                "score=" + score +
//                '}';

//    }
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Student student = (Student) o;
//        return score == student.score;

//    }
//    @Override
//    public int hashCode() {
//        return Objects.hash(score);
//    }
}
