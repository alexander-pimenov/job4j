package ru.job4j.stream;

import java.util.Objects;

public class Student implements Comparable<Student> {

    private int score;

    Student(int score) {
        this.score = score;
    }

    int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "score=" + score +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return score == student.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(this.score, o.score);
    }
}
