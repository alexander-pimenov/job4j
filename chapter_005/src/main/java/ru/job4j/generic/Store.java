package ru.job4j.generic;

/**
 * Интерфейс для описания контейнеров.
 * Контейнеры реализуем далее в классах
 * имплементирующих данный интерфейс.
 * Контейнеры нужны для хранения объектов.
 *
 * @param <T> обобщение по хранимому объекту в контейнере.
 */

public interface Store<T extends Base> {
    void add(T model);

    boolean replace(String id, T model);

    boolean delete(String id);

    T findById(String id);
}
