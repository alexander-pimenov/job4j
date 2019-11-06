package ru.job4j.search;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определяет по полю приоритет.
     * Для вставки использовать add(int index, E value)
     *
     * @param task задача
     */

    //метод располагающий внутри очереди элементы по приоритету, т.е.
    //сортируя по значению поля priority
    public void put(Task task) {
//        //добавить вставку в связный список
//        int index = tasks.size();
//        for (int i = 0; i < tasks.size(); i++) {
//            if (task.getPriority() < tasks.get(i).getPriority()) {
//                index = i;
//                //index = tasks.indexOf(tasks.get(i));
//                break;
//            }
//        }
//        tasks.add(index, task);


        // С помощью стримов:
        this.tasks.add(task);
        this.tasks = this.tasks.stream()
                .sorted(Comparator.comparing(Task::getPriority))
                .collect(Collectors.toCollection(LinkedList::new));

        //или так с помощью стримов:
//        var index = (int) this.tasks.stream()
//                .filter(t -> t.getPriority() < task.getPriority())
//                .count();
//        this.tasks.add(index,task);
    }

    //метод извлекающий элемент из очереди, с последующим удалением
    public Task take() {
        return this.tasks.poll();
    }

    @Override
    public String toString() {
        return "PriorityQueue{" +
                "tasks=" + tasks +
                '}';
    }
}

