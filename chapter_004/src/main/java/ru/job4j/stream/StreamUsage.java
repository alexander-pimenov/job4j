package ru.job4j.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * В этом примере фильтруем задачи в имени, которых есть слово
 * BUG и сохраняем их в коллекцию List.
 */

public class StreamUsage {
    public static class Task {
        private final String name;
        private final long spent;

        Task(String name, long spent) {
            this.name = name;
            this.spent = spent;
        }

        @Override
        public String toString() {
            return "Task{"
                    + "name='" + name + '\''
                    + ", spent=" + spent + '}';
        }
    }

    public static void main(String[] args) {

        //Создаем список тасков
        List<Task> tasks = List.of(
                new Task("Bug#1", 100),
                new Task("Task#2", 100),
                new Task("Bug#3", 100),
                new Task("Bug#4", 100)
        );

        //Вариант фильтрации с помощью stream.
        List<Task> bugs = tasks.stream().
                filter(task -> task.name.contains("Bug"))
                .collect(Collectors.toList());
        bugs.forEach(System.out::println);

        //Вариант решения фильтрации for-each.
        List<Task> container = new ArrayList<>();

        for (Task task : tasks) {
            //if ("BUG".equalsIgnoreCase(task.name)){
            if (task.name.contains("Bug")) {
                container.add(task);
            }
        }
        System.out.println(container);

        //Вариант преобразования через stream. Получаем только name.
        List<String> names = tasks.stream()
                .map(task -> task.name)
                .collect(Collectors.toList());

        System.out.println("С помощью stream: " + names);

        //Вариант преобразования через for. Получаем только name.
        List<String> containerNames = new ArrayList<>();
        for (Task task : tasks) {
            containerNames.add(task.name);
        }
        System.out.println("С помощью for: " + containerNames);

        //Вариант упрощения с помощью stream. Считаем общую сумму, потраченную на все задачи.
        Long total = tasks.stream()
                .map(task -> task.spent)
                .reduce(0L, Long::sum);
        System.out.println("С помощью stream: " + total);

        //Вариант упрощения с помощью for. Считаем общую сумму, потраченную на все задачи.
        long totalSpent = 0L;
        for (Task task : tasks) {
            totalSpent += task.spent;
        }
        System.out.println("С помощью for: " + totalSpent);
    }
}
