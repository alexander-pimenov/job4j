package ru.job4j.user;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {

    //List.of() создает неизменяемый список. И если мы передаем его в метод, который хочет его изменить и выдать
    //изменённый на выходе, то получаем UnsupportedOperationException. Поэтому закоментировал код ниже.
    //    private List<User> userList = List.of(
//            new User("Ivan", 25, "Moscow"),
//            new User("Roman", 25, "Kursk"),
//            new User("Fillip", 35, "Omsk"),
//            new User("Vladislav", 45, "Belgorod"),
//            new User("Anna", 55, "Riga"),
//            new User("Svetlana", 55, "Kaluga"),
//            new User("Roman", 35, "Omsk"),
//            new User("Svetlana", 55, "Voronezh")
//    );

    private SortUser sortUser = new SortUser();

    private User user1 = new User("Ivan", 25, "Moscow");
    private User user2 = new User("Roman", 25, "Kursk");
    private User user3 = new User("Fillip", 35, "Omsk");
    private User user4 = new User("Vladislav", 45, "Belgorod");
    private User user5 = new User("Anna", 55, "Riga");
    private User user6 = new User("Svetlana", 55, "Kaluga");
    private User user7 = new User("Roman", 35, "Omsk");
    private User user8 = new User("Svetlana", 55, "Voronezh");
    private List<User> users = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8);


    /**
     * Тест проверяет конвертацию списка User в TreeSet,
     * сортировка в TreeSet идет сперва по возрасту.
     */
    @Test
    public void whenUserSortThenSortedTreeSet() {

        Set<User> result = sortUser.sort(users);
        Set<User> expect = Set.of(
                new User("Ivan", 25, "Moscow"),
                new User("Roman", 25, "Kursk"),
                new User("Fillip", 35, "Omsk"),
                new User("Roman", 35, "Omsk"),
                new User("Vladislav", 45, "Belgorod"),
                new User("Anna", 55, "Riga"),
                new User("Svetlana", 55, "Kaluga")
        );

        assertThat(result, is(expect));
    }

    /**
     * Тест проверяет сравнение пользователей по длине имени
     */
    @Test
    public void whenUserSortByLengthNameThenSorted() {

        List<User> result = sortUser.sortNameLength(users);
        List<User> expect = List.of(
                new User("Ivan", 25, "Moscow"),
                new User("Anna", 55, "Riga"),
                new User("Roman", 25, "Kursk"),
                new User("Roman", 35, "Omsk"),
                new User("Fillip", 35, "Omsk"),
                new User("Svetlana", 55, "Kaluga"),
                new User("Svetlana", 55, "Voronezh"),
                new User("Vladislav", 45, "Belgorod")

        );
        assertThat(result.toString(), is(expect.toString()));
    }

    /**
     * Тест проверяет сравнение пользователей по всем полям:
     * name, age, city
     */
    @Test
    public void whenUserSortByAllFieldThenSorted() {

        List<User> result = sortUser.sortByAllField(this.users);
        List<User> expect = List.of(
                new User("Anna", 55, "Riga"),
                new User("Fillip", 35, "Omsk"),
                new User("Ivan", 25, "Moscow"),
                new User("Roman", 25, "Kursk"),
                new User("Roman", 35, "Omsk"),
                new User("Svetlana", 55, "Kaluga"),
                new User("Svetlana", 55, "Voronezh"),
                new User("Vladislav", 45, "Belgorod")
        );
        assertThat(result.toString(), is(expect.toString()));
    }
}
