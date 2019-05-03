package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.start.Input;
import ru.job4j.tracker.start.StartUI;
import ru.job4j.tracker.start.StubInput;
import ru.job4j.tracker.start.Tracker;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();     // создаём Tracker
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.findAll()[0].getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявку
        Item item = tracker.add(new Item("test name", "desc", 123L));
        //создаём StubInput с последовательностью действий(производим замену заявки)
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что элемент массива в трекере содержит измененное имя, введённое при эмуляции.
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    @Test
    public void whenUserDeleteItemThenTrackerDontHaveThisItem() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Добавляем 1 заявку напрямую
        Item item1 = tracker.add(new Item("name1", "desc1", 123L));
        //Добавляем 2 заявку напрямую
        Item item2 = tracker.add(new Item("name2", "desc2", 124L));
        //Добавляем 3 заявку напрямую
        Item item3 = tracker.add(new Item("name3", "desc3", 125L));

        //создаём StubInput с последовательностью действий(производим удаление заявки)
        Input input = new StubInput(new String[]{"3", item1.getId(), "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();

        // проверяем, что количество элементов в трекере уменьшилось.
        assertThat(tracker.findAll().length, is(2));

        // проверяем, что массив в трекере содержит 2 элемента, введённые под номером item2 и item3.
        assertThat(tracker.findAll()[0].getId(), is(item2.getId()));
        assertThat(tracker.findAll()[1].getId(), is(item3.getId()));
    }

    @Test
    public void whenUserFindItemById() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Добавляем 1-ю заявку (напрямую)
        Item item = tracker.add(new Item("name1", "desc1", 123L));
        //создаём StubInput с последовательностью действий(производим удаление заявки)
        Input input = new StubInput(new String[]{"4", item.getId(), "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findById(item.getId()).getName(), is("name1"));
    }


}
