package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserEqualsTest {
    @Test
    public void whenOnlyEqualsOverrided() {
        //создаем два объекта с одинаковыми полями
        UserEquals user1 = new UserEquals("Alex", 2, new GregorianCalendar(1990, Calendar.APRIL, 15));
        UserEquals user2 = new UserEquals("Alex", 2, new GregorianCalendar(1990, Calendar.APRIL, 15));

        System.out.println("Сравним два объекта: они равны? - " + user1.equals(user2));

        System.out.println("hash-code user1: " + user1.hashCode());
        System.out.println("hash-code user2: " + user2.hashCode());

        Map<UserEquals, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());

        System.out.println(map);

        System.out.println("достали объект по ключу user1 -> " + map.get(user1));
        System.out.println("достали объект по ключу user2 -> " + map.get(user2));

        //Результат вывода: - хэш-коды каждый раз будут различны
        //Сравним два объекта: они равны? - true
        //hash-code user1: 434091818
        //hash-code user2: 398887205
        //{ru.job4j.map.UserEquals@17c68925=java.lang.Object@7e0ea639, ru.job4j.map.UserEquals@19dfb72a=java.lang.Object@3d24753a}
        //достали объект по ключу user1 -> java.lang.Object@3d24753a
        //достали объект по ключу user2 -> java.lang.Object@7e0ea639
    }

}