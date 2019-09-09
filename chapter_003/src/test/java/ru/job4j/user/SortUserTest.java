package ru.job4j.user;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {

    /**
     * Тест проверяет конвертацию списка User в TreeSet
     */
    @Test
    public void whenUserSortThenSortedTreeSet() {
        SortUser sortUser = new SortUser();
        User user1 = new User("Ivan", 25, "Moscow");
        User user2 = new User("Roman", 25, "Kursk");
        User user3 = new User("Fillip", 35, "Omsk");
        User user4 = new User("Vladislav", 45, "Belgorod");
        User user5 = new User("Anna", 55, "Riga");
        User user6 = new User("Svetlana", 55, "Kaluga");
        User user7 = new User("Roman", 35, "Omsk");
        User user8 = new User("Svetlana", 55, "Voronezh");
        List<User> users = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8);

        Set<User> result = sortUser.sort(users);
        Set<User> expect = new TreeSet<>();
        expect.add(user1);
        expect.add(user2);
        expect.add(user3);
        expect.add(user7);
        expect.add(user4);
        expect.add(user5);
        expect.add(user6);

        assertThat(result, is(expect));
    }

    /**
     * Тест проверяет сравнение пользователей по длине имени
     */
    @Test
    public void whenUserSortByLengthNameThenSorted() {
        SortUser sortUser = new SortUser();
        User user1 = new User("Ivan", 25, "Moscow");
        User user2 = new User("Roman", 25, "Kursk");
        User user3 = new User("Fillip", 35, "Omsk");
        User user4 = new User("Vladislav", 45, "Belgorod");
        User user5 = new User("Anna", 55, "Riga");
        User user6 = new User("Svetlana", 55, "Kaluga");
        User user7 = new User("Roman", 35, "Omsk");
        User user8 = new User("Svetlana", 55, "Voronezh");
        List<User> users = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8);
        sortUser.sortNameLength(users);
        List<User> expect = new ArrayList<>();
        expect.add(user1);
        expect.add(user5);
        expect.add(user2);
        expect.add(user7);
        expect.add(user3);
        expect.add(user6);
        expect.add(user8);
        expect.add(user4);

        assertThat(users, is(expect));
    }

    /**
     * Тест проверяет сравнение пользователей по всем полям:
     * name, age, city
     */
    @Test
    public void whenUserSortByAllFieldThenSorted() {
        SortUser sortUser = new SortUser();
        User user1 = new User("Ivan", 25, "Moscow");
        User user2 = new User("Roman", 25, "Kursk");
        User user3 = new User("Fillip", 35, "Omsk");
        User user4 = new User("Vladislav", 45, "Belgorod");
        User user5 = new User("Anna", 55, "Riga");
        User user6 = new User("Svetlana", 55, "Kaluga");
        User user7 = new User("Roman", 35, "Omsk");
        User user8 = new User("Svetlana", 55, "Voronezh");
        List<User> users = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8);
        sortUser.sortByAllField(users);
        List<User> expect = new ArrayList<>();
        expect.add(user5);
        expect.add(user3);
        expect.add(user1);
        expect.add(user2);
        expect.add(user7);
        expect.add(user6);
        expect.add(user8);
        expect.add(user4);

        assertThat(users, is(expect));
    }
}
