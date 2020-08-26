package ru.job4j.tracker_sql;

import java.util.List;

/**
 * Store - интерфейс нашего хранилища.
 * Наследуется от интерфейса AutoCloseable.
 * Т.к. нам нужно обеспечить закрытие ресурса.
 */
public interface Store extends AutoCloseable {
    //void init();

    Item add(Item item);

    boolean replace(String id, Item item);

    boolean delete(String id);

    List<Item> findAll();

    List<Item> findByName(String key);

    Item findById(String id);
}
