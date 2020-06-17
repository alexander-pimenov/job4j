package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    public Tree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * Метод добавляет дочерний элемент к родительскому.
     * Родительский элемент может содержать список дочерних элементов.
     * Если элемент с таким значением уже существует, он вернет false.
     * Если родитель не найден, возвращается false.
     *
     * @param parent родительский узел
     * @param child  потомок родительского узла
     * @return булево значение выполнения операции
     */
    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        if (findBy(child).isEmpty()) {
            Optional<Node<E>> node = findBy(parent);
            if (node.isPresent()) {
                node.get().add(new Node<>(child));
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
