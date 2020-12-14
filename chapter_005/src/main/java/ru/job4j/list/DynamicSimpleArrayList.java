package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicSimpleArrayList<E> implements Iterable<E> {

    //наш контейнер базируется на массиве объектов Object[]
    private Object[] container;
    //поле, которое хранит текущий размер контейнера
    private int size = 0;
    //счетчик структурных изменений нашего контейнера
    private int modCount = 0;

    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Конструктор по умолчанию.
     */
    public DynamicSimpleArrayList() {
        this.container = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Конструктор с заданной емкостью
     *
     * @param capacity нужная емкость контейнера.
     */
    public DynamicSimpleArrayList(int capacity) {
        this.container = new Object[capacity];
    }

    /**
     * Метод добавляющий новый элемент в конец массива
     *
     * @param value новый добавляемый элемент.
     */
    public void add(E value) {
        grow();
        this.container[size] = value;
        this.size++;
        modCount++;
    }

    /**
     * Метод возвращающий элемент из нашего контейнера
     * по указанному индексу.
     *
     * @param index индекс, возвращаемого элемента.
     * @return возвращаемый элемент.
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {

        if (index >= this.size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (E) this.container[index];
    }

    /**
     * Метод увеличивает наш массив, если необходимо,
     * в 1,5 раза.
     */
    public void grow() {
        if (size == this.container.length) {
            this.container = Arrays.copyOf(this.container, this.container.length * 3 / 2);
        }

    }

    /**
     * Метод говорящий о размере нашего контенера,
     * т.е. о количестве элементов в нем.
     *
     * @return количество элементов.
     */
    public int size() {
        return this.size;
    }


    /**
     * Метод, возвращающий Iterator.
     * Также в этом методе производится проверка
     * структурной модификации коллекции при обходе.
     *
     * @return итератор.
     */

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int position = 0;
            private int expectedModCount = DynamicSimpleArrayList.this.modCount;

            @Override
            public boolean hasNext() {
                if (this.expectedModCount != DynamicSimpleArrayList.this.modCount) {
                    throw new ConcurrentModificationException();
                }
                return DynamicSimpleArrayList.this.size > this.position;
            }

            @SuppressWarnings("unchecked")
            @Override
            public E next() {
                if (this.expectedModCount != DynamicSimpleArrayList.this.modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return (E) DynamicSimpleArrayList.this.container[this.position++];
            }
        };
    }

    @Override
    public String toString() {
        return "DynamicSimpleArrayList{"
                + "container=" + Arrays.toString(container)
                + '}';
    }
}
