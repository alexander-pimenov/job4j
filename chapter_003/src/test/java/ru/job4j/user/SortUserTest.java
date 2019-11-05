package ru.job4j.user;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {

    //List.of() создает неизменяемый список. И если мы передаем его в метод, который хочет его изменить и выдать
    //измененный на выходе, то получаем UnsupportedOperationException
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
     * сортировка в TreeSet идет по возрасту.
     */
    @Test
    public void whenUserSortThenSortedTreeSet() {
//        SortUser sortUser = new SortUser();
//        User user1 = new User("Ivan", 25, "Moscow");
//        User user2 = new User("Roman", 25, "Kursk");
//        User user3 = new User("Fillip", 35, "Omsk");
//        User user4 = new User("Vladislav", 45, "Belgorod");
//        User user5 = new User("Anna", 55, "Riga");
//        User user6 = new User("Svetlana", 55, "Kaluga");
//        User user7 = new User("Roman", 35, "Omsk");
//        User user8 = new User("Svetlana", 55, "Voronezh");
//        List<User> users = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8);

        Set<User> result = sortUser.sort(users);
        //Set<User> result1 = sortUser.sort(userList);
        Set<User> expect1 = Set.of(
                new User("Ivan", 25, "Moscow"),
                new User("Roman", 25, "Kursk"),
                new User("Fillip", 35, "Omsk"),
                new User("Roman", 35, "Omsk"),
                new User("Vladislav", 45, "Belgorod"),
                new User("Anna", 55, "Riga"),
                new User("Svetlana", 55, "Kaluga")
        );
//        Set<User> expect = new TreeSet<>();
//        expect.add(user1);
//        expect.add(user2);
//        expect.add(user3);
//        expect.add(user7);
//        expect.add(user4);
//        expect.add(user5);
//        expect.add(user6);

        assertThat(result, is(expect1));
    }

    /**
     * Тест проверяет сравнение пользователей по длине имени
     */
    @Test
    public void whenUserSortByLengthNameThenSorted() {
//        SortUser sortUser = new SortUser();
//        User user1 = new User("Ivan", 25, "Moscow");
//        User user2 = new User("Roman", 25, "Kursk");
//        User user3 = new User("Fillip", 35, "Omsk");
//        User user4 = new User("Vladislav", 45, "Belgorod");
//        User user5 = new User("Anna", 55, "Riga");
//        User user6 = new User("Svetlana", 55, "Kaluga");
//        User user7 = new User("Roman", 35, "Omsk");
//        User user8 = new User("Svetlana", 55, "Voronezh");
//        List<User> users = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8);

        //List<User> result1 = sortUser.sortNameLength(userList);
        List<User> result = sortUser.sortNameLength(users);
        List<User> expect1 = List.of(
                new User("Ivan", 25, "Moscow"),
                new User("Anna", 55, "Riga"),
                new User("Roman", 25, "Kursk"),
                new User("Roman", 35, "Omsk"),
                new User("Fillip", 35, "Omsk"),
                new User("Svetlana", 55, "Kaluga"),
                new User("Svetlana", 55, "Voronezh"),
                new User("Vladislav", 45, "Belgorod")

        );
//        List<User> expect = new ArrayList<>();
//        expect.add(user1);
//        expect.add(user5);
//        expect.add(user2);
//        expect.add(user7);
//        expect.add(user3);
//        expect.add(user6);
//        expect.add(user8);
//        expect.add(user4);
//
        //assertThat(users, is(expect));
        assertThat(result.toString(), is(expect1.toString()));
    }

    /**
     * Тест проверяет сравнение пользователей по всем полям:
     * name, age, city
     */
    @Test
    public void whenUserSortByAllFieldThenSorted() {
//        SortUser sortUser = new SortUser();
//        User user1 = new User("Ivan", 25, "Moscow");
//        User user2 = new User("Roman", 25, "Kursk");
//        User user3 = new User("Fillip", 35, "Omsk");
//        User user4 = new User("Vladislav", 45, "Belgorod");
//        User user5 = new User("Anna", 55, "Riga");
//        User user6 = new User("Svetlana", 55, "Kaluga");
//        User user7 = new User("Roman", 35, "Omsk");
//        User user8 = new User("Svetlana", 55, "Voronezh");
//        List<User> users = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8);

        //List<User> users1 = sortUser.sortByAllField(userList);
        List<User> result = sortUser.sortByAllField(this.users);
        List<User> expect1 = List.of(
                new User("Anna", 55, "Riga"),
                new User("Fillip", 35, "Omsk"),
                new User("Ivan", 25, "Moscow"),
                new User("Roman", 25, "Kursk"),
                new User("Roman", 35, "Omsk"),
                new User("Svetlana", 55, "Kaluga"),
                new User("Svetlana", 55, "Voronezh"),
                new User("Vladislav", 45, "Belgorod")
        );

//        List<User> expect = new ArrayList<>();
//        expect.add(user5);
//        expect.add(user3);
//        expect.add(user1);
//        expect.add(user2);
//        expect.add(user7);
//        expect.add(user6);
//        expect.add(user8);
//        expect.add(user4);

        //assertThat(users1.toString(), is(expect1.toString()));
        assertThat(result.toString(), is(expect1.toString()));
    }
}
