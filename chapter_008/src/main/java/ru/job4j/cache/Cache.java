package ru.job4j.cache;

public interface Cache<K, V> {

    V getData(K key);
}
