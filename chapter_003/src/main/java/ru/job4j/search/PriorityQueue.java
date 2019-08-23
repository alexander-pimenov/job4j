package ru.job4j.search;

import java.util.LinkedList;

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
        //добавить вставку в связный список
        int index = tasks.size();
        for (int i = 0; i < tasks.size(); i++) {
            if (task.getPriority() < tasks.get(i).getPriority()) {
                index = i;
                //index = tasks.indexOf(tasks.get(i));
                break;
            }
        }
        tasks.add(index, task);
    }

    //метод извлекающий элемент из очереди, с последующим удалением
    public Task take() {
        return this.tasks.poll();
    }
}

