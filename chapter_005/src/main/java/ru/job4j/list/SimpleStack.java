package ru.job4j.list;

public class SimpleStack<E> extends SimpleDoublyLinkedList<E> {

    /**
     * Это связанный список, на основе которого создаем SimpleStack.
     */
    private SimpleDoublyLinkedList<E> linked = new SimpleDoublyLinkedList<>();

    public SimpleStack() {
    }

    public SimpleStack(SimpleDoublyLinkedList<E> linked) {
        this.linked = linked;
    }

    /**
     * Метод возвращает значение и удаляет его из коллекции.
     *
     * @return значение первого элемента в списке.
     */
    public E poll() {
        return linked.deleteFirst();
    }

    /**
     * Метод помещает значение в коллекцию.
     *
     * @param value помещаемое значение.
     */
    public void push(E value) {
        linked.addFirst(value);
    }

    /**
     * Метод возвращает размер стека
     *
     * @return размер стека
     */
    public int size() {
        return linked.getSize();
    }
}
