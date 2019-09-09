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
    // поле ссылки на дефолтный вывод в консоль, сохраним дефолный вывод в консоль,
    // чтобы потом к нему вернуться
    private final PrintStream stdout = System.out;

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
        System.setOut(new PrintStream(this.mem));
    }

    //Метод реализует обратный вывод в консоль, возврат к стандартному выводу в консоль
    @After
    public void backOutputAfter() {
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
        new StartUI(input, tracker).init();
        assertThat(
                this.mem.toString(),
                is(
                        new StringBuilder()
                                .append(menu)
                                .append("-------------- Show all items --------------")
                                .append(LS)
                                .append("Name: name1 Description: desc1 Id: " + item1.getId())
                                .append(LS)
                                .append("Name: name2 Description: desc2 Id: " + item2.getId())
                                .append(LS)
                                .append("Name: name3 Description: desc3 Id: " + item3.getId())
                                .append(LS)
                                .append("------------")
                                .append(LS)
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
        Input input = new StubInput(Arrays.asList("5", "name1", "6"));
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        assertThat(
                this.mem.toString(),
                is(
                        new StringBuilder()
                                .append(menu)
                                .append("------------ Find task by Name --------------")
                                .append(LS)
                                .append("------------ Your items ------------")
                                .append(LS)
                                .append("Name: name1 Description: desc1 Id: " + item1.getId() + " \n")
                                .append(LS)
                                .append("Name: name1 Description: desc2 Id: " + item2.getId() + " \n")
                                .append(LS)
                                .append("Name: name1 Description: desc4 Id: " + item4.getId() + " \n")
                                .append(LS)
                                .append("------------- End of search ---------------")
                                .append(LS)
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
        Input input = new StubInput(Arrays.asList("0", "test name", "desc", "6"));
        //   создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init(); //создаём StartUI и вызываем метод init()
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
        new StartUI(input, tracker).init(); //создаём StartUI и вызываем метод init()
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
        new StartUI(input, tracker).init();

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
        new StartUI(input, tracker).init();
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findById(item.getId()).getName(), is("name1"));
    }
}





//-------------- Menu --------------
//0 : Add new Item.
//1 : Show all items.
//2 : Edit item.
//3 : Delete item.
//4 : Find item by Id.
//5 : Find items by name.
//6 : Exit Program.
//Please select number:
//0
//------------ Adding new item --------------
//Please, provide item name:
//qqq
//Please, provide item description:
//a
//------------ New Item with Id : 1568973085690
//------------ New Item with Name : qqq
//------------ New Item with Description : a
//-------------- Menu --------------
//0 : Add new Item.
//1 : Show all items.
//2 : Edit item.
//3 : Delete item.
//4 : Find item by Id.
//5 : Find items by name.
//6 : Exit Program.
//Please select number:
//6
//The selected menu item is Exit.
//Do you want exit the program?(y):
//y
//Goodbye!

//"C:\Program Files\Java\jdk1.8.0_201\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2018.3.4\lib\idea_rt.jar=64597:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2018.3.4\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_201\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\rt.jar;C:\projects\job4j\chapter_002\target\classes" ru.job4j.tracker.start.StartUI
//-------------- Menu --------------
//0 : Add new Item.
//1 : Show all items.
//2 : Edit item.
//3 : Delete item.
//4 : Find item by Id.
//5 : Find items by name.
//6 : Exit Program.
//Please select number:
//0
//------------ Adding new item --------------
//Please, provide item name:
//qqq
//Please, provide item description:
//q
//------------ New Item with Id : 1567506544952
//------------ New Item with Name : qqq
//------------ New Item with Description : q
//-------------- Menu --------------
//0 : Add new Item.
//1 : Show all items.
//2 : Edit item.
//3 : Delete item.
//4 : Find item by Id.
//5 : Find items by name.
//6 : Exit Program.
//Please select number:
//0
//------------ Adding new item --------------
//Please, provide item name:
//aaa
//Please, provide item description:
//a
//------------ New Item with Id : 1565855892686
//------------ New Item with Name : aaa
//------------ New Item with Description : a
//-------------- Menu --------------
//0 : Add new Item.
//1 : Show all items.
//2 : Edit item.
//3 : Delete item.
//4 : Find item by Id.
//5 : Find items by name.
//6 : Exit Program.
//Please select number:
//0
//------------ Adding new item --------------
//Please, provide item name:
//zzz
//Please, provide item description:
//z
//------------ New Item with Id : 1569310568653
//------------ New Item with Name : zzz
//------------ New Item with Description : z
//-------------- Menu --------------
//0 : Add new Item.
//1 : Show all items.
//2 : Edit item.
//3 : Delete item.
//4 : Find item by Id.
//5 : Find items by name.
//6 : Exit Program.
//Please select number:
//1
//-------------- Show all items --------------
//Id: 1567506544952 Name: qqq Description: q
//Id: 1565855892686 Name: aaa Description: a
//Id: 1569310568653 Name: zzz Description: z
//--------------- End of list ---------------
//-------------- Menu --------------
//0 : Add new Item.
//1 : Show all items.
//2 : Edit item.
//3 : Delete item.
//4 : Find item by Id.
//5 : Find items by name.
//6 : Exit Program.
//Please select number:
//5
//------------ Find task by Name --------------
//Please, enter the item's name that you want to find :
//
//qqq
//There is no items with this name .
//------------- End of search ---------------
//-------------- Menu --------------
//0 : Add new Item.
//1 : Show all items.
//2 : Edit item.
//3 : Delete item.
//4 : Find item by Id.
//5 : Find items by name.
//6 : Exit Program.
//Please select number:
//Please enter correct data again.
//Please select number:
//1
//-------------- Show all items --------------
//Id: 1567506544952 Name: qqq Description: q
//Id: 1565855892686 Name: aaa Description: a
//Id: 1569310568653 Name: zzz Description: z
//--------------- End of list ---------------
//-------------- Menu --------------
//0 : Add new Item.
//1 : Show all items.
//2 : Edit item.
//3 : Delete item.
//4 : Find item by Id.
//5 : Find items by name.
//6 : Exit Program.
//Please select number:
//5
//------------ Find task by Name --------------
//Please, enter the item's name that you want to find :
//qqq
//------------ Your items ------------
//Id: 1567506544952 Name: qqq Description: q
//------------- End of search ---------------
//-------------- Menu --------------
//0 : Add new Item.
//1 : Show all items.
//2 : Edit item.
//3 : Delete item.
//4 : Find item by Id.
//5 : Find items by name.
//6 : Exit Program.
//Please select number:
//6
//The selected menu item is Exit.
//Do you want exit the program?(y):
//y
//Goodbye!
//
//Process finished with exit code 0

//    //"Меню" для проверки вывода в консоль, чтобы уменьшить код в is()
//    private final StringBuilder menu = new StringBuilder()
//            .append("-------------- Menu --------------")
//            .append(System.lineSeparator())
//            .append("0 : Add new Item.")
//            .append(System.lineSeparator())
//            .append("1 : Show all items.")
//            .append(System.lineSeparator())
//            .append("2 : Edit item.")
//            .append(System.lineSeparator())
//            .append("3 : Delete item.")
//            .append(System.lineSeparator())
//            .append("4 : Find item by Id.")
//            .append(System.lineSeparator())
//            .append("5 : Find items by name.")
//            .append(System.lineSeparator())
//            .append("6 : Exit Program.")
//            .append(System.lineSeparator());