package ru.job4j.design.isp.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Класс реализующий элемент меню.
 */
public class Item implements Action, Information {
    /**
     * Имя элемента.
     */
    private String keyName;
    /**
     * Описание элемента.
     */
    private String description;
    /**
     * Список дочерних элементов.
     */
    private List<Item> subItems = new ArrayList<>();

    public Item(String name, String description) {
        this.keyName = name;
        this.description = description;
    }

    /**
     * Метод добавляет дочерний элемент
     * в список.
     *
     * @param item дочерний элемент.
     */
    public void addSubItem(Item item) {
        if (!subItems.contains(item)) {
            this.subItems.add(item);
        }
    }

    /**
     * Метод вызывает некоторое действие
     * у элемента найденного по его ключу.
     *
     * @param key имя ключа элемента.
     * @return true or false.
     */
    @Override
    public boolean execute(String key) {
        boolean result = false;
        if (this.keyName.equals(key)) {
            result = true;
            System.out.println("Item " + this.getKeyName() + " do something.");
        } else if (!this.subItems.isEmpty()) {
            for (Item subItem : subItems) {
                //рекурсивно спускаемся ниже на уровень
                result = subItem.execute(key);
                if (result) {
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Метод вызывает некоторое действие
     * у вызванного элемента.
     * Немного отличается от метода boolean execute(String key)
     */
    @Override
    public void act() {
        System.out.printf("Item %s - %s", this.getKeyName(), "Do some action!");
        if (!subItems.isEmpty()) {
            for (Item subItem : subItems) {
                //рекурсивно спускаемся ниже на уровень
                subItem.act();
            }
        }
    }

    /**
     * Метод для изменения описания элемента.
     *
     * @param item элемент.
     * @return boolean result.
     */
    public boolean updateItem(Item item) {
        boolean result = false;
        for (Item elm : subItems) {
            if (elm.getKeyName().equals(item.getKeyName())) {
                elm.setDescription(item.getDescription());
                result = true;
            } else {
                //рекурсивно спускаемся ниже на уровень
                elm.updateItem(item);
            }
        }
        return result;
    }

    /**
     * Метод выводит информацию об элементе.
     *
     * @return строковая информация.
     */
    @Override
    public String itemInfo() {
        StringBuilder builder = new StringBuilder();
        int keyLength = this.getKeyName().length();
        for (int i = 0; i != keyLength; i++) {
            builder.append("-");
        }
        builder.append(String.format("%s %s%s", this.keyName, this.description, System.lineSeparator()));
        if (!this.subItems.isEmpty()) {
            for (Item subItem : subItems) {
                builder.append(subItem.itemInfo());
            }
        }
        return builder.toString();
    }

    public String getKeyName() {
        return keyName;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Item> getSubItems() {
        return subItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(keyName, item.keyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyName);
    }

    @Override
    public String toString() {
        return "Item{"
                + "keyName='" + keyName + '\''
                + ", description='" + description + '\''
                + '}';
    }
}
