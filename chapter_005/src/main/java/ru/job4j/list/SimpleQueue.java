package ru.job4j.list;

/**
 * Простая очередь SimpleQueue на двух стеках SimpleStack,
 * реализованных ранее.
 */

public class SimpleQueue<E> extends SimpleStack<E> {

    /**
     * Это два стека, на основе которых создаем SimpleQueue.
     */
    private SimpleStack<E> firstStack;
    private SimpleStack<E> secondStack;
    //размер очереди
    private int size;

    SimpleQueue() {
        this.firstStack = new SimpleStack<>();
        this.secondStack = new SimpleStack<>();
    }

    /**
     * Метод добавляет элемент в очередь
     * (Используется первый стек)
     *
     * @param value добавляемый элемент
     */
    public void push(E value) {
        firstStack.push(value);
        size++;
    }

    /**
     * Метод берет элемент из очереди по FIFO
     *
     * @return первый элемент из очереди
     */
    public E poll() {
        if (secondStack.size() == 0) {
            while (firstStack.size() != 0) {
                secondStack.push(firstStack.poll());
            }
        }
        E result = secondStack.poll();
        size--;
        return result;
    }

    /**
     * Метод возвращает размер очереди
     *
     * @return размер очереди
     */
    public int size() {
        return this.size;
    }
}
