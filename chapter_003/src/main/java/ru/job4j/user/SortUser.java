package ru.job4j.user;

import java.util.*;
import java.util.stream.Collectors;

public class SortUser {

    /**
     * Метод конвертирует список объектов User в TreeSet
     *
     * @return TreeSet<User>
     */
    public Set<User> sort(List<User> users) {

        return new TreeSet<>(users);
    }

    /**
     * Метод sortNameLength.
     * Сортировка по длине имени.
     *
     * @param users список пользователей.
     * @return отсортированный список.
     */
    public List<User> sortNameLength(List<User> users) {
//        users.sort(
//                new Comparator<User>() {
//                    @Override
//                    public int compare(User o1, User o2) {
//                        return Integer.compare(o1.getName().length(), o2.getName().length());
//                    }
//                }
//        );
//        return users;

        return users.stream()
                .sorted(Comparator.comparing(u -> u.getName().length()))
                .collect(Collectors.toList());
    }

    /**
     * Метод sortByAllFields.
     * Сортировка по трем полям (name, age, city).
     * Сначала сортировка по имени в лексикографическом порядке,
     * потом по возрасту, потом по городу.
     *
     * @param users список пользвателей.
     * @return отсортированный список.
     */
    public List<User> sortByAllField(List<User> users) {
//        users.sort(
//                new Comparator<User>() {
//                    @Override
//                    public int compare(User o1, User o2) {
//                        int result = o1.getName().compareTo(o2.getName());
//                        if (result == 0) {
//                            result = Integer.compare(o1.getAge(), o2.getAge());
//                        }
//                        if (result == 0) {
//                            result = o1.getCity().compareTo(o2.getCity());
//                        }
//                        return result;
//                    }
//                }
//        );
//        return users;

        return users.stream()
                .sorted(Comparator.comparing(User::getName)
                        .thenComparingInt(User::getAge).thenComparing(User::getCity))
                .collect(Collectors.toList());
    }
}
