package ru.job4j.map;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleHashMap<K, V> implements SimpleMap<K, V> {

    private Node<K, V>[] hashTable;
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    private int size = 0;

    public SimpleHashMap() {
        this.hashTable = new Node[INITIAL_CAPACITY];
    }

    public SimpleHashMap(int capacity) {
        this.hashTable = new Node[capacity];
    }

    public int capacity() {
        return this.hashTable.length;
    }

    /**
     * Метод вычисляющий hash ключа.
     * ^ и >>>16 дают хорошее распределение объектов-ключ по бакетам
     * 16 - количество бакетов в начале использования HashMap.
     *
     * @param key ключ-объект
     * @return числовое соответствие объекта
     */
    private int hash(K key) {
        int hash;
        hash = key.hashCode() ^ key.hashCode() >>> 16;
        return (key == null) ? 0 : (hash);
//        Эту часть кода закомментировал, т.к. верхние три строчки лучще подходят
//        для вычисления хэша ключа-объекта.
//        int hash = 31;
//        hash = hash * 17 + key.hashCode();
        //checkstyle указывает на то чтобы избегал внутренних назначений, поэтому
        // переписал этот участок кода
//        return (key == null) ? 0 : (hash = key.hashCode()) ^ (hash >>> 16);
    }

    /**
     * Определяет индекс в массиве по хэшу ключа
     * (другими словами - номер корзины в массиве)
     *
     * @param hash значение хэша ключа
     * @return индекс в массиве
     */
    private int indexFor(int hash) {
        return hash & (hashTable.length - 1);
    }

    /**
     * Вставка объекта в массив хэш-таблицы
     *
     * @param key   ключ объекта
     * @param value значение объекта
     * @return true если объект вставлен или false если там уже есть объект
     */
    @Override
    public boolean insert(K key, V value) {
        boolean result = false;
        if (size + 1 >= (this.hashTable.length * LOAD_FACTOR)) {
            arrayDoubling();
        }
        int hash = hash(key);
        int index = indexFor(hash);
        if (hashTable[index] == null) {
            hashTable[index] = new Node<>(hash, key, value);
            result = true;
            size++;
        }
        return result;
    }

    /**
     * Метод изменения размера массива, если порог превышен.
     * Массив увеличивается вдвое.
     */
    private void arrayDoubling() {
        Node<K, V>[] oldHashTable = hashTable;
        int oldCapacity = oldHashTable.length;
        hashTable = new Node[oldCapacity * 2];
        int newCapacity = hashTable.length;
        int i = 0;

        for (int index = 0; index < oldCapacity; index++) {
            if (oldHashTable[index] != null) {
                K key = oldHashTable[index].getKey();
                int hash = hash(key);
                int indexNew = indexFor(hash);
                hashTable[indexNew] = oldHashTable[index];
            }
        }
    }

    /**
     * Метод получения объекта из карты по ключу
     *
     * @param key значение ключа
     * @return получаемый объект
     */
    @Override
    public V get(K key) {
        int hash = hash(key);
        int index = indexFor(hash);
        if (this.hashTable[index] == null) {
            return null;
        } else {
            V value = null;
            Node<K, V> kvNode = this.hashTable[index];
            if (kvNode.key.equals(key)) {
                value = kvNode.value;
            }
            return value;
        }
    }

    /**
     * Метод удаления объекта из карты по ключу
     *
     * @param key значение ключа
     * @return булево значение
     */
    @Override
    public boolean delete(K key) {
        boolean result = false;
        int hash = hash(key);
        int index = indexFor(hash);
        if (this.hashTable[index] != null) {
            hashTable[index] = null;
            result = true;
            size--;
        }
        return result;
    }

    /**
     * Метод получения размера карты по количеству элементов в ней.
     *
     * @return целочисленное число разера карты
     */
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        return "SimpleHashMap{"
                + "hashTable=" + Arrays.toString(hashTable)
                + '}';
    }

    public Iterator<Node<K, V>> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Node<K, V>> {

        private int index = 0;
        private int position = 0;

        @Override
        public boolean hasNext() {
            return SimpleHashMap.this.size > index;
        }

        @Override
        public Node<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            while (hashTable[position] == null) {
                position++;
            }
            Node<K, V> node = hashTable[position];
            position++;
            index++;
            return node;
        }
    }

    static class Node<K, V> {
        final int hash;
        final K key;
        V value;

        public Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public int getHash() {
            return hash;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return key + " = " + value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?, ?> node = (Node<?, ?>) o;
            return hash == node.hash
                    && Objects.equals(key, node.key)
                    && Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(hash, key, value);
        }
    }
}
