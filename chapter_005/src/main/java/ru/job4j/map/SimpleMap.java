package ru.job4j.map;

public interface SimpleMap<K, V> {
    boolean insert(K key, V value);

    V get(K key);

    boolean delete(K key);

    int size();
}
