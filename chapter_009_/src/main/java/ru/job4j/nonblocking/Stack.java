package ru.job4j.nonblocking;

import net.jcip.annotations.NotThreadSafe;

/*Не потокобезопасный Stack*/
@NotThreadSafe
public class Stack<T> {

    //Голова списка, нужна чтобы мы могли добавлять элементы.
    private Node<T> head;

    /*Вставляем данные в голову Стека.*/
    public void push(T value) {
        Node<T> temp = new Node<>(value); //создаем новый элемент
        if (head == null) {
            this.head = temp;
            return;
        }
        temp.next = this.head;
        this.head = temp;
    }

    /*Извлекаем данные из головы Стека.*/
    public T poll() {
        Node<T> temp = this.head;
        if (temp == null) {
            throw new IllegalStateException("Stack is empty");
        }
        this.head = temp.next;
        temp.next = null;
        return temp.value;
    }

    /**
     * Класс предназначенный для хранения данных.
     * Приватный статический вложенный класс.
     * Он хранит в себе указатель на следующий узел
     * и само значение объекта, которое
     * подставляется при создании узла в конструкторе.
     */
    private static final class Node<T> {
        final T value; // данные хранящиеся в текущем узле
        Node<T> next; // указатель (ссылка) на следующий элемент

        public Node(final T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("Value is %s", value);
        }
    }
}
