package ru.job4j.design.isp.menu;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertTrue;

public class MenuTest {

    @Test
    public void whenAddMenuItemsThenShowAllItems() {
        Menu menu = new Menu("Задачи");
        Item item1 = new Item("1", "Задача 1");
        Item item11 = new Item("1.1", "Задача 1.1");
        Item item111 = new Item("1.1.1", "Задача 1.1.1");
        Item item2 = new Item("2", "Задача 2");
        Item item22 = new Item("2.2", "Задача 2.2");
        Item item222 = new Item("2.2.2", "Задача 2.2.2");
        item11.addSubItem(item111);
        item1.addSubItem(item11);
        item22.addSubItem(item222);
        item2.addSubItem(item22);
        menu.addItem(item1);
        menu.addItem(item2);
        menu.showMenu();
    }

    @Test
    public void whenAddMenuItemsThenUpdateItem() {
        Menu menu = new Menu("Задачи");
        Item item1 = new Item("1", "Задача 1");
        Item item11 = new Item("1.1", "Задача 1.1");
        Item item111 = new Item("1.1.1", "Задача 1.1.1");
        Item item1111 = new Item("1.1.1.1", "Задача 1.1.1.1");
        item111.addSubItem(item1111);
        item11.addSubItem(item111);
        item1.addSubItem(item11);
        item1.updateItem(new Item("1.1", "Задача Обновлена"));

        menu.addItem(item1);
        menu.showMenu();

        assertTrue(item1.updateItem(new Item("1.1", "Задача Обновлена")));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenAddMenuItemsAndCallMethodSelectItemAndExecuteButHaveError() {
        Menu menu = new Menu("Задачи");
        Item item1 = new Item("1", "Задача 1");
        Item item11 = new Item("1.1", "Задача 1.1");
        item1.addSubItem(item11);
        menu.addItem(item1);
        menu.showMenu();
        menu.selectItemAndExecute("2.2");
    }

    @Test(expected = NoSuchElementException.class)
    public void whenAddMenuItemsAndCallMethodSelectItemByNameButHaveError() {
        Menu menu = new Menu("Задачи");
        Item item1 = new Item("1", "Задача 1");
        Item item11 = new Item("1.1", "Задача 1.1");
        item1.addSubItem(item11);
        menu.addItem(item1);
        menu.showMenu();
        menu.selectItemByName("2.2");
    }

    @Test
    public void whenAddMenuItemsAndCallMethodSelectItemAndExecute() {
        Menu menu = new Menu("Задачи");
        Item item1 = new Item("1", "Задача 1");
        Item item11 = new Item("1.1", "Задача 1.1");
        Item item111 = new Item("1.1.1", "Задача 1.1.1");
        Item item112 = new Item("1.1.2", "Задача 1.1.2");
        Item item12 = new Item("1.2", "Задача 1.2");
        Item item122 = new Item("1.2.2", "Задача 1.2.2");
        Item item1221 = new Item("1.2.2.1", "Задача 1.2.2.1");
        Item item2 = new Item("2", "Задача 2");
        Item item21 = new Item("2.1", "Задача 2.1");
        Item item211 = new Item("2.1.1", "Задача 2.1.1");

        item122.addSubItem(item1221);
        item12.addSubItem(item122);
        item11.addSubItem(item112);
        item11.addSubItem(item111);
        item1.addSubItem(item11);
        item1.addSubItem(item12);
        menu.addItem(item1);

        item21.addSubItem(item211);
        item2.addSubItem(item21);
        menu.addItem(item2);

        menu.showMenu();

        menu.selectItemAndExecute("2.1");
    }

    @Test
    public void whenAddMenuItemsAndCallMethodSelectItemByNameAndThenAct() {
        Menu menu = new Menu("Задачи");
        Item item1 = new Item("1", "Задача 1");
        Item item11 = new Item("1.1", "Задача 1.1");
        Item item111 = new Item("1.1.1", "Задача 1.1.1");
        Item item112 = new Item("1.1.2", "Задача 1.1.2");
        Item item12 = new Item("1.2", "Задача 1.2");
        Item item122 = new Item("1.2.2", "Задача 1.2.2");
        Item item1221 = new Item("1.2.2.1", "Задача 1.2.2.1");
        Item item2 = new Item("2", "Задача 2");
        Item item21 = new Item("2.1", "Задача 2.1");
        Item item211 = new Item("2.1.1", "Задача 2.1.1");

        item122.addSubItem(item1221);
        item12.addSubItem(item122);
        item11.addSubItem(item112);
        item11.addSubItem(item111);
        item1.addSubItem(item11);
        item1.addSubItem(item12);
        menu.addItem(item1);

        item21.addSubItem(item211);
        item2.addSubItem(item21);
        menu.addItem(item2);

        menu.showMenu();

        Item item = menu.selectItemByName("2.1.1");
        System.out.println(item);
        item.act();
    }
}