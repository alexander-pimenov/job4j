package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleDoublyLinkedList<E> implements Iterable<E> {

    //В этом поле храним размер нашего списка.
    private int size;

    //Голова списка, нужна чтобы мы могли добавлять элементы.
    //Указатель на первый элемент (узел). В самом начале он пуст.
    private Node<E> first;

    //Хвост списка, нужен чтобы мы могли добавлять элементы.
    //Указатель на последний элемент. В самом начале он пуст.
    private Node<E> last;

    //Счетчик структурных изменений нашего связного листа.
    private int modCount;

    /**
     * Конструктор по умолчанию.
     */
    public SimpleDoublyLinkedList() {
    }

    /**
     * Метод вставляет в НАЧАЛО списка данные.
     * (очень похоже на Stack - добавляем в начало.)
     */
    public void addFirst(E data) {
        Node<E> newLink = new Node<>(data); //создаем новый элемент
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
        modCount++;
    }

    /**
     * Метод вставляет в КОНЕЦ списка данные.
     */
    public void addLast(E data) {
        Node<E> prev = last;
        Node<E> newLink = new Node<>(prev, data, null); //создаем новый элемент
        last = newLink;
        if (prev == null) {
            first = newLink;
        } else {
            prev.next = newLink;
        }
        this.size++;
        modCount++;
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
        modCount++;
        return result.data;
    }

    /**
     * Метод реализующий удаления ПОСЛЕДНЕГО элемента в списке
     * и возвращающий его значение.
     */
    public E deleteLast() {
        Node<E> result = this.last;
        if (result == null) {
            throw new NoSuchElementException();
        }
        final E element = result.data;
        Node<E> prev = result.prev;
        result.data = null;
        result.prev = null;
        last = prev;
        if (prev == null) first = null;
        else prev.next = null;

        this.size--;
        modCount++;
        return element;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        Node<E> result = this.first;
        if (result == null) {
            throw new NoSuchElementException();
        }
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
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
            //Счетчик изменений в нашем связном списке.
            private int expectedModCount = SimpleDoublyLinkedList.this.modCount;

            Node<E> temp = first;

            @Override
            public boolean hasNext() {
                if (this.expectedModCount != SimpleDoublyLinkedList.this.modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size;
            }

            @Override
            public E next() {
                if (this.expectedModCount != SimpleDoublyLinkedList.this.modCount) {
                    throw new ConcurrentModificationException();
                }
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
     * Приватный статический вложенный класс.
     * Он хранит в себе указатель на следующий узел и само значение объекта,
     * которое подставляется при создании узла в конструкторе.
     */
    private static class Node<E> {

        E data; // данные хранящиеся в текущем узле
        Node<E> next; // указатель (ссылка) на следующий элемент
        Node<E> prev; // указатель (ссылка) на предыдущий элемент

        /**
         * Создадим конструктор, который будет приниматьна на вход значение.
         * Т.к. при создании нового узла мы будем подавать на вход ему значение.
         *
         * @param data значение хранимого элемента в SimpleDoublyLinkedList<E>.
         */

        Node(E data) {
            this.data = data;
        }

        public Node(Node<E> prev, E data, Node<E> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

        @Override
        public String toString() {
            return String.format("Value is %s", data);
        }
    }
}
