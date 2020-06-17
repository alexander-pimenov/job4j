package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface SimpleTree<E> {
    boolean add(E parent, E child);

    Optional<Node<E>> findBy(E value);

    /**
     * Класс Node описывает узел дерева.
     * Узел содержит хранимое значение value и ссылки на дочерние узлы.
     *
     * @param <E> тип хранимого значения
     */
    class Node<E> {
        final E value;
        final List<Node<E>> children = new ArrayList<>();

        public Node(E value) {
            this.value = value;
        }

        //Метод добавления child в список children
        public void add(Node<E> child) {
            this.children.add(child);
        }
    }
}
