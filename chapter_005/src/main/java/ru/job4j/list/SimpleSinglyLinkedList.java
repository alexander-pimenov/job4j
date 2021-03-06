package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Контейнер для данных, базирующийся на односвязном списке.
 * Класс SimpleSinglyLinkedList (было имя SimpleArrayList).
 */

public class SimpleSinglyLinkedList<E> implements Iterable<E> {
    //В этом поле храним размер нашего списка.
    private int size;

    //Голова списка, нужна чтобы мы могли добавлять элементы.
    private Node<E> first;

    /**
     * Метод вставляет в НАЧАЛО списка данные.
     * (очень похоже на Stack - добавляем в начало.)
     */
    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Метод вставляет в КОНЕЦ списка данные.
     */
    public void addLast(E data) {
        Node<E> newLink = new Node<>(data, null);
        if (first == null) {
            first = newLink;
            this.size++;
            return;
        }
        Node<E> tail = first;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = newLink;
        this.size++;
    }

    /**
     * Метод для перевертывания Односвязного списка.
     */
    public void revert() {
        Node<E> temp = first;
        Node<E> previous = null;
        Node<E> current = null;
        while (temp != null) {
            current = temp;
            temp = temp.next;
            current.next = previous;
            previous = current;
            first = current;
        }
    }


    /**
     * Метод реализующий удаления ПЕРВОГО элемента в списке
     * и возвращающий его значение.
     * (очень похоже на Stack - удаляем из начала.)
     */
    public E delete() {
        Node<E> result = this.first;
        if (result == null) {
            throw new NoSuchElementException();
        }
        this.first = first.next;
        this.size--;
        return result.data;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        Node<E> result = this.first;
        if (result == null) {
            throw new NoSuchElementException();
        }
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Метод получения Итератора.
     */

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int cursor = 0;
            Node<E> temp = first;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException();
                }
                E value = temp.getData();
                temp = temp.next;

                cursor++;
                return value;
            }
        };
    }

    /**
     * Класс предназначенный для хранения данных.
     */
    private static class Node<E> {

        E data; //значение хранящееся в текущем узле
        Node<E> next; //ссылка на следующий элемент

        /**
         * Создадим конструктор, который будет приниматьна на вход значение.
         * Т.к. при создании нового узла мы будем подавать на вход ему значение.
         *
         * @param data значение хранимого элемента в SimpleArrayList<E>.
         */

        Node(E data) {
            this.data = data;
        }

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }
    }
}
