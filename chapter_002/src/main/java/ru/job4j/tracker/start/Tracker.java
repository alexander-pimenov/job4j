package ru.job4j.tracker.start;

import ru.job4j.tracker.models.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * @version $Id$
 * @since 0.1
 */
public class Tracker {

    /**
     * Поле список (ArrayList) для хранения заявок
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Указатель ячейки для новой заявки
     */
    private int position = 0;

    /**
     * Используем генерацию случайных чисел для получения уникального id
     */
    private static final Random RN = new Random();


    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     *
     * @return Уникальный ключ.
     */
    private String generateId() {
        //Реализовать метод генерации
        return String.valueOf(System.currentTimeMillis() + RN.nextInt(100));
    }

    /**
     * Метод реализующий добавление заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    //public Item add(Item item) {
//        item.setId(this.generateId());
//        this.items.add(this.position++, item);
//        return item;
//    }


    /**
     * Метод реализующий редактирование заявок.
     *
     * @param id,   id items элемента
     * @param item, измененный
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
        for (int index = 0; index < items.size(); index++) {
            if (this.items.get(index).getId() != null && this.items.get(index).getId().equals(id)) {
                item.setId(id);
                this.items.set(index, item);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод реализующий удаление заявок.
     * Для этого необходимо найти ячейку в списке по id.
     *
     * @param id, id items элемента
     */


    public boolean delete(String id) {
        boolean result = false;
        ListIterator<Item> current = this.items.listIterator();
        while (current.hasNext()) {
            if (current.next().getId().equals(id)) {
                current.remove();
                result = true;
                break;
            }
        }
        return result;
    }
//    public boolean delete(String id) {
//        boolean result = false;
//        for (int index = 0; index < items.size(); index++) {
//            if (this.items.get(index) != null && this.items.get(index).getId().equals(id)) {
//                this.items.remove(index);
//                position--;
//                result = true;
//                break;
//            }
//        }
//        return result;
//    }


    /**
     * Метод реализующий получение списка всех заявок.
     *
     * @return items
     */
    public List<Item> findAll() {
        return new ArrayList<>(items);
    }

    /**
     * Метод реализующий получения списка по имени.
     * Собирает все items, соответсвующие ключу key, в отдельный список
     *
     * @param key, имя items элемента
     * @return list, список найденных элементов
     */
    public List<Item> findByName(String key) {
        List<Item> list = new ArrayList<>();
        for (Item item : items) {
            if (item != null && item.getName().equals(key)) {
                list.add(item);
            }
            //обычный цикл for
//        for (int index = 0; index < items.size(); index++) {
//            if (this.items.get(index) != null && this.items.get(index).getName().equals(key)) {
//                list.add(this.items.get(index));
//            }
        }
        return list;
    }

    /**
     * Метод реализующий поиск заявки по id.
     * Получает id, перебирает весь список items, в каждом берет id  и сравнивает
     * его с тем, который зашел в метод. Если находит такой id, то возвращает этот item.
     *
     * @return item
     */

    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }
}
