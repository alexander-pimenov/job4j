package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.start.Input;
import ru.job4j.tracker.start.StartUI;
import ru.job4j.tracker.start.StubInput;
import ru.job4j.tracker.start.Tracker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StartUITest {
    //создаем поле трекер, чтобы не указывать его в каждом разделе @Test
    private final Tracker tracker = new Tracker();
    // поле ссылки на дефолтный вывод в консоль
    private final PrintStream stdout = System.out;
    // буфер для хранения данных вывода, результата
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    //"Меню" для проверки вывода в консоль, чтобы уменьшить код в is()
    private final StringBuilder menu = new StringBuilder()
            .append("Меню.")
            .append(System.lineSeparator())
            .append("0. Добавление новой заявки.")
            .append(System.lineSeparator())
            .append("1. Показать все заявки.")
            .append(System.lineSeparator())
            .append("2. Редактировать заявку.")
            .append(System.lineSeparator())
            .append("3. Удалить заявку.")
            .append(System.lineSeparator())
            .append("4. Найти заявку по Id.")
            .append(System.lineSeparator())
            .append("5. Найти заявки по имени.")
            .append(System.lineSeparator())
            .append("6. Выход из меню.")
            .append(System.lineSeparator());


    // Метод реализует замену стандартного вывода в консоль на вывод в память
    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    //Метод реализует обратный вывод в консоль, возврат к стандартному выводу в консоль
    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    /**
     * Тест проверяющий вывод в консоль всех итемов трекера,
     * т.е работу метода showAllItems
     */

    @Test
    public void whenUserShowAllItems() {
        //Добавляем 1-ю заявку (напрямую)
        Item item1 = tracker.add(new Item("name1", "desc1", 123L));
        //Добавляем 2 заявку напрямую
        Item item2 = tracker.add(new Item("name2", "desc2", 123L));
        //Добавляем 3 заявку напрямую
        Item item3 = tracker.add(new Item("name3", "desc3", 123L));

        //создаём StubInput с последовательностью действий(выводим все имеющиеся заявки в консоль)
        Input input = new StubInput(new String[]{"1", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        assertThat(
                this.out.toString(),
                is(
                        new StringBuilder()
                                .append(menu)
                                .append("------------ Показать все заявки --------------")
                                .append(System.lineSeparator())
                                .append("Name: name1 Description: desc1 Id: " + item1.getId())
                                .append(System.lineSeparator())
                                .append("Name: name2 Description: desc2 Id: " + item2.getId())
                                .append(System.lineSeparator())
                                .append("Name: name3 Description: desc3 Id: " + item3.getId())
                                .append(System.lineSeparator())
                                .append("------------")
                                .append(System.lineSeparator())
                                .append(menu)
                                .toString()
                )
        );
    }

    /**
     * Тест проверяющий вывод в консоль всех итемов с одинаковым именем,
     * т.е работу метода findItemsByName
     */
    @Test
    public void whenUserShowItemsWithSameNames() {
        //Добавляем 1-ю заявку (напрямую)
        Item item1 = tracker.add(new Item("name1", "desc1", 123L));
        //Добавляем 2 заявку напрямую
        Item item2 = tracker.add(new Item("name1", "desc2", 123L));
        //Добавляем 3 заявку напрямую
        Item item3 = tracker.add(new Item("name2", "desc3", 123L));
        //Добавляем 4 заявку напрямую
        Item item4 = tracker.add(new Item("name1", "desc4", 123L));
        //Добавляем 5 заявку напрямую
        Item item5 = tracker.add(new Item("name3", "desc5", 123L));

        //создаём StubInput с последовательностью действий(выводим все имеющиеся заявки в консоль)
        Input input = new StubInput(new String[]{"5", "name1", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        assertThat(
                this.out.toString(),
                is(
                        new StringBuilder()
                                .append(menu)
                                .append("------------ Поиск заявки по её имени --------------")
                                .append(System.lineSeparator())
                                .append("------------ Ваша(и) заявка(и) ------------")
                                .append(System.lineSeparator())
                                .append("Name: name1 Description: desc1 Id: " + item1.getId() + " \n")
                                .append(System.lineSeparator())
                                .append("Name: name1 Description: desc2 Id: " + item2.getId() + " \n")
                                .append(System.lineSeparator())
                                .append("Name: name1 Description: desc4 Id: " + item4.getId() + " \n")
                                .append(System.lineSeparator())
                                .append("------------ Конец поиска ------------")
                                .append(System.lineSeparator())
                                .append(menu)
                                .toString()
                )
        );
    }

    /**
     * Тест проверяющий работу метода createItem
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        //   создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }

    /**
     * Тест проверяющий работу метода editItem
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        //Напрямую добавляем заявку
        Item item = tracker.add(new Item("test name", "desc", 123L));
        //создаём StubInput с последовательностью действий(производим замену заявки)
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что элемент массива в трекере содержит измененное имя, введённое при эмуляции.
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    /**
     * Тест проверяющий работу метода deleteItem
     */
    @Test
    public void whenUserDeleteItemThenTrackerDontHaveThisItem() {
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

    /**
     * Тест проверяющий работу метода findItemById
     */
    @Test
    public void whenUserFindItemById() {
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
