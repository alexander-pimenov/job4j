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
import java.util.Arrays;
import java.util.function.Consumer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Pimenov
 * @version $Id$
 * @since 0.1
 */

public class StartUITest {
    //создаем поле трекер, чтобы не указывать его в каждом разделе @Test
    private final Tracker tracker = new Tracker();
    // буфер для хранения данных вывода, буфер для хранения результата
    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();

    // поле ссылки на дефолтный (стандартный) вывод в консоль, сохраним
    // дефолный вывод в консоль, чтобы потом к нему вернуться.
    private final PrintStream original = System.out;

    //private final Consumer<String> output = System.out::println;
    private final Consumer<String> output = new Consumer<>() {
        private final PrintStream stdout = new PrintStream(mem);

        @Override
        public void accept(String s) {
            stdout.println(s);
        }

        @Override
        public String toString() {
            return mem.toString();
        }
    };

    // поле ссылки на дефолтный вывод в консоль, сохраним дефолный вывод в консоль,
    // чтобы потом к нему вернуться
    //private final PrintStream stdout = System.out;

    private final static String LS = System.lineSeparator();

    //"Меню" для проверки вывода в консоль, чтобы уменьшить код в is()
    private final StringBuilder menu = new StringBuilder()
            .append("-------------- Menu --------------")
            .append(LS)
            .append("0 : Add new Item.")
            .append(LS)
            .append("1 : Show all items.")
            .append(LS)
            .append("2 : Edit item.")
            .append(LS)
            .append("3 : Delete item.")
            .append(LS)
            .append("4 : Find item by Id.")
            .append(LS)
            .append("5 : Find items by name.")
            .append(LS)
            .append("6 : Exit Program.")
            .append(LS);


    // Метод реализует замену стандартного вывода в консоль на вывод в память
    @Before
    public void loadOutputBefore() {
        System.out.println("execute before method");
        //Заменяем стандартный вывод на вывод в пямять для тестирования.
        System.setOut(new PrintStream(this.mem));
    }

    //Метод реализует обратный вывод в консоль, возврат к стандартному выводу в консоль.
    @After
    public void backOutputAfter() {
        //System.setOut(this.stdout);
        System.setOut(original);
        System.out.println("execute after method");
    }

    /**
     * Тест проверяющий вывод в консоль всех итемов трекера,
     * т.е работу метода showAllItems
     */

    @Test
    public void whenUserShowAllItems() {
        //Добавляем 1-ю заявку (напрямую)
        //Item item1 = tracker.add(new Item("name1", "desc1", 123L));
        Item item1 = new Item("name1", "desc1", 123L);
        tracker.add(item1);
        //Добавляем 2 заявку напрямую
        Item item2 = new Item("name2", "desc2", 123L);
        tracker.add(item2);
        //Добавляем 3 заявку напрямую
        Item item3 = new Item("name3", "desc3", 123L);
        tracker.add(item3);

        //создаём StubInput с последовательностью действий(выводим все имеющиеся заявки в консоль)
        Input input = new StubInput(Arrays.asList("1", "6"));
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker, output).init();
        assertThat(
                //this.mem.toString(),
                this.output.toString(),
                is(
                        String.valueOf(menu)
                                + "-------------- Show all items --------------"
                                + LS
                                + "Id: " + item1.getId() + " Name: name1 Description: desc1"
                                + LS
                                + "Id: " + item2.getId() + " Name: name2 Description: desc2"
                                + LS
                                + "Id: " + item3.getId() + " Name: name3 Description: desc3"
                                + LS
                                + "--------------- End of list ---------------"
                                + LS
                                + menu
                                + "The selected menu item is Exit."
                                + LS
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
        Input input = new StubInput(Arrays.asList("5", "name1", "6"));
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker, output).init();
        assertThat(
                this.output.toString(),
                is(
                        String.valueOf(menu)
                                + "------------ Find task by Name --------------"
                                + LS
                                + "------------ Your items ------------"
                                + LS
                                + "Id: " + item1.getId() + " Name: name1 Description: desc1"
                                + LS
                                + "Id: " + item2.getId() + " Name: name1 Description: desc2"
                                + LS
                                + "Id: " + item4.getId() + " Name: name1 Description: desc4"
                                + LS
                                + "------------- End of search ---------------"
                                + LS
                                + menu
                                + "The selected menu item is Exit."
                                + LS
                )
        );
    }

    /**
     * Тест проверяющий работу метода createItem
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        //создаём StubInput с последовательностью действий
        Input input = new StubInput(Arrays.asList("0", "test name", "desc", "6"));
        //   создаём StartUI и вызываем метод init()
        new StartUI(input, tracker, output).init(); //создаём StartUI и вызываем метод init()
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findAll().get(0).getName(), is("test name"));
    }

    /**
     * Тест проверяющий работу метода editItem
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        //Напрямую добавляем заявку
        Item item = tracker.add(new Item("test name", "desc", 123L));
        //создаём StubInput с последовательностью действий(производим замену заявки)
        Input input = new StubInput(Arrays.asList("2", item.getId(), "test replace", "заменили заявку", "6"));
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker, output).init(); //создаём StartUI и вызываем метод init()
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
        Input input = new StubInput(Arrays.asList("3", item1.getId(), "6"));
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker, output).init();

        // проверяем, что количество элементов в трекере уменьшилось.
        assertThat(tracker.findAll().size(), is(2));

        // проверяем, что массив в трекере содержит 2 элемента, введённые под номером item2 и item3.
        assertThat(tracker.findAll().get(0).getId(), is(item2.getId()));
        assertThat(tracker.findAll().get(1).getId(), is(item3.getId()));
    }

    /**
     * Тест проверяющий работу метода findItemById
     */
    @Test
    public void whenUserFindItemById() {
        //Добавляем 1-ю заявку (напрямую)
        Item item = tracker.add(new Item("name1", "desc1", 123L));
        //создаём StubInput с последовательностью действий(производим удаление заявки)
        Input input = new StubInput(Arrays.asList("4", item.getId(), "6"));
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker, output).init();
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findById(item.getId()).getName(), is("name1"));
    }
}

//До преобразования в String, так предложила IDEA, использовал StringBuilder:
//                is(
//                        new StringBuilder()
//                                .append(menu)
//                                .append("------------ Find task by Name --------------")
//                                .append(LS)
//                                .append("------------ Your items ------------")
//                                .append(LS)
//                                .append("Id: " + item1.getId() + " Name: name1 Description: desc1")
//                                .append(LS)
//                                .append("Id: " + item2.getId() + " Name: name1 Description: desc2")
//                                .append(LS)
//                                .append("Id: " + item4.getId() + " Name: name1 Description: desc4")
//                                .append(LS)
//                                .append("------------- End of search ---------------")
//                                .append(LS)
//                                .append(menu)
//                                .append("The selected menu item is Exit.")
//                                .append(LS)
//                                .toString()
//                )
//        );


