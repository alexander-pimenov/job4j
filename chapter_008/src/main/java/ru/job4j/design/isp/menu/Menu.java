package ru.job4j.design.isp.menu;

import java.util.*;

/**
 * Меню.
 */
public class Menu {

    /**
     * Имя нашего меню.
     */
    private String nameMenu;

    /**
     * Карта наших элементов в меню.
     */
    private Map<String, Item> items = new TreeMap<>();

    public Menu() {
    }

    public Menu(String nameMenu) {
        this.nameMenu = nameMenu;
    }

    /**
     * Метод добавляет элемент в меню.
     *
     * @param item элемент.
     */
    public void addItem(Item item) {
        if (!items.containsKey(item.getKeyName())) {
            this.items.put(item.getKeyName(), item);
        }
    }

    /**
     * Метод рисует меню.
     */
    public void showMenu() {
        System.out.println(this.nameMenu);
        for (String strKey : items.keySet()) {
            System.out.print(items.get(strKey).itemInfo());
        }
    }

    /**
     * Метод вызывает некоторый элемент по ключу
     * чтобы он выполнил действие.
     *
     * @param nameItem имя элемента.
     */
    public void selectItemAndExecute(String nameItem) {
        String subKey;
        if (nameItem.length() > 1) {
            subKey = nameItem.substring(0, 1);
        } else {
            subKey = nameItem;
        }
        boolean result = false;
        if (this.items.containsKey(subKey)) {
            result = this.items.get(subKey).execute(nameItem);
        }
        if (!result) {
//            System.out.println("Error, no such item in this menu.");
            throw new NoSuchElementException("Error, no such item in this menu.");
        }
    }

    /**
     * Метод получает пункт по имени.
     * Уже из него можно вытащить действие,
     * которое можно будет вызвать.
     *
     * @param nameItem имя пункта меню.
     * @return объект пункта меню.
     */
    Item selectItemByName(String nameItem) {
        Optional<Item> rsl = Optional.empty();
        Queue<Item> data = new LinkedList<>();
        final Collection<Item> values = items.values();
        for (Item elm : values) {
            data.offer(elm);
        }
        while (!data.isEmpty()) {
            Item element = data.poll();
            if (element.getKeyName().equals(nameItem)) {
                rsl = Optional.of(element);
                break;
            }
            data.addAll(element.getSubItems());
        }
        return rsl.orElseThrow(() -> new NoSuchElementException("No such item " + nameItem + " in this menu."));
    }
}


//#######################################################
// ПАМЯТКА:
// Вариант метода selectItemByName(), но с Optional.
// Подразумевается, что Optional будет обработан
// в клиентском коде:
//
//    Optional<Item> selectItemByName(String nameItem) {
//        Optional<Item> rsl = Optional.empty();
//        Queue<Item> data = new LinkedList<>();
//        final Collection<Item> values = items.values();
//        for (Item elm : values) {
//            data.offer(elm);
//        }
//        while (!data.isEmpty()) {
//            Item element = data.poll();
//            if (element.getKeyName().equals(nameItem)) {
//                rsl = Optional.of(element);
//                break;
//            }
//            data.addAll(element.getSubItems());
//        }
//        return rsl;
//    }
//#######################################################
