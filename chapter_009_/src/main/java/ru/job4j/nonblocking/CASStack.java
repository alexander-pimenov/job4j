package ru.job4j.nonblocking;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/*Здесь нет синхронизации, но класс потокобезопасный.
* Процессоры на уровне ядра поддерживают операцию CAS - compare-and-swap (сравнивать и менять местами).
* Эта операция атомарная. В Java есть структуры данных, которые реализуют этот механизм.
* Например, Stack.
* Метод push и poll используют шаблон check-then-act.
* Метод compareAndSet атомарный!!!
* */
@ThreadSafe
public class CASStack<T> {
    private final AtomicReference<Node<T>> head = new AtomicReference<>();

    public void push(T value) {
        Node<T> temp = new Node<>(value); //создаем новый элемент
        Node<T> ref;
        do {
            ref = head.get(); //считываем указатель
            temp.next = ref;
        } while (!head.compareAndSet(ref, temp));
    }

    public T poll() {
        Node<T> ref;
        Node<T> temp;
        do {
            ref = head.get();
            if (ref == null) {
                throw new IllegalStateException("Stack is empty");
            }
            temp = ref.next;
        } while (!head.compareAndSet(ref, temp));
        ref.next = null;
        return ref.value;
    }

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
