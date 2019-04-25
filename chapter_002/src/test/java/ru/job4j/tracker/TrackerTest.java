package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.start.Tracker;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {

    /**
     * Test whenAddNewItemThenTrackerHasSameItem.
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        long created = System.currentTimeMillis();
        Item item = new Item("test1", "testDescription", created);
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    /**
     * Test whenReplaceNameThenReturnNewName.
     */
    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2", "testDescription2", 1234L);
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.replace(previous.getId(), next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    /**
     * Test whenDeleteItemInTracker.
     */
    @Test
    public void whenDeleteItemInTracker() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("test1", "testDescription1", 121L));
        tracker.add(new Item("test2", "testDescription2", 122L));
        tracker.add(new Item("test3", "testDescription3", 123L));
        tracker.add(new Item("test3", "testDescription4", 124L));
        Item[] items = tracker.findAll();
        boolean b = tracker.delete(items[0].getId());

        assertThat(tracker.findAll().length, is(3));
        assertThat(b, is(true));

        assertThat(tracker.findAll()[0].getId(), is(items[1].getId()));
        assertThat(tracker.findAll()[1].getId(), is(items[2].getId()));
        assertThat(tracker.findAll()[2].getId(), is(items[3].getId()));
    }

    /**
     * Test whenFindByNameItemInTracker.
     */
    @Test
    public void whenfindByNameItemInTracker() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("test1", "testDescription1", 121L));
        tracker.add(new Item("test2", "testDescription2", 122L));
        tracker.add(new Item("test3", "testDescription3", 123L));
        tracker.add(new Item("test4", "testDescription4", 124L));
        Item[] resalt = tracker.findByName("test2");
        assertThat(tracker.findAll()[1].getName(), is("test2"));
        /*т.к. разультат сводится в массив resalt, то искомое значение одно и оно на 0 позиции*/
        assertThat(resalt[0].getName(), is("test2"));
    }

    /**
     * Test whenFindByNameItemInTracker and we have 3 same names
     */
    @Test
    public void whenfindByNameItemInTracker3SameName() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("test1", "testDescription1", 121L));
        tracker.add(new Item("test3", "testDescription2", 122L));
        tracker.add(new Item("test3", "testDescription3", 123L));
        tracker.add(new Item("test4", "testDescription4", 124L));
        tracker.add(new Item("test3", "testDescription5", 125L));
        Item[] resalt = tracker.findByName("test3");

        /*т.к. разультат сводится в массив resalt, в результате имеем три ячейки с одинаковыми Name*/
        assertThat(resalt[0].getName(), is("test3"));
        assertThat(resalt[1].getName(), is("test3"));
        assertThat(resalt[2].getName(), is("test3"));

        /*в самом трекере ячейки с одинаковым Name остаются на своих местах, и имеют свои индексы ячеек*/
        assertThat(tracker.findAll()[1].getName(), is("test3"));
        assertThat(tracker.findAll()[2].getName(), is("test3"));
        assertThat(tracker.findAll()[4].getName(), is("test3"));
    }
}
