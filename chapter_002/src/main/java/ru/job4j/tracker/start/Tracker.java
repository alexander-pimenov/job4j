package ru.job4j.tracker.start;

import ru.job4j.tracker.models.Item;

import java.util.Arrays;
import java.util.Random;

/**
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    /**
     * Массив для хранения заявок
     */
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки
     */
    private int position = 0;
    /**
     * Используем генерацию случайных чисел для получения уникального id
     */
    private static final Random RN = new Random();

    /**
     * Метод реализующий добавление заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     *
     * @return Уникальный ключ.
     */
    private String generateId() {
        //Реализовать метод генерации
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * Метод реализующий редактирование заявок.
     *
     * @param id, id items элемента
     * @param item, измененный
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
        for(int i=0; i != this.position; i++){
            if(this.items[i].getId() != null && this.items[i].getId().equals(id)){
                this.items[i]=item;
                this.items[i].setId(id);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод реализующий удаление заявок.
     * Для этого необходимо найти ячейку в массиве по id.
     * Далее сместить все значения справа от удаляемого элемента - на одну ячейку влево с помощью System.arrayCopy().
     * Метод должен вернуть boolean результат - удалось ли провести операцию.
     *
     * @param id, id items элемента
     */


    public boolean delete(String id) {
        boolean result = false;
        for (int index = 0; index != this.position; index++) {
            if (this.items[index] != null && this.items[index].getId().equals(id)) {
                this.items[index] = null;
                position--;
                System.arraycopy(items, index + 1, items, index, this.position - index);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод реализующий получение списка всех заявок.
     *
     * @return items
     */
    public Item[] findAll() {
        Item[] result = new Item[this.position];
        for (int index = 0; index != this.position; index++) {
            result[index] = this.items[index];
        }
        return result;
    }

    /**
     * Метод реализующий получения списка по имени.
     * Собирает все items в отдельный массив
     *
     * @param key, имя items элемента
     * @return items, массив найденных элементов
     */
    public Item[] findByName(String key) {
        int index = 0;
        Item[] result = new Item[this.position];
        for (int i = 0; i < result.length; i++) {
            if (this.items[i].getName().equals(key)) {
                result[index++] = this.items[i];
            }
        }
        return Arrays.copyOf(result, index);
    }

    /**
     * Метод реализующий заявки по id.
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
