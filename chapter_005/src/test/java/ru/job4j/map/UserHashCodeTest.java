package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserHashCodeTest {

    @Test
    public void whenOnlyHashCodeOverrided() {
        //создаем два объекта с одинаковыми полями
        User user1 = new User("Alex", 2, new GregorianCalendar(1990, Calendar.APRIL, 15));
        User user2 = new User("Alex", 2, new GregorianCalendar(1990, Calendar.APRIL, 15));

        System.out.println("Сравним два объекта: они равны? - " + user1.equals(user2));

        System.out.println("hash-code user1: " + user1.hashCode());
        System.out.println("hash-code user2: " + user2.hashCode());

        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());

        System.out.println(map);

        System.out.println("достали объект по ключу user1 -> " + map.get(user1));
        System.out.println("достали объект по ключу user2 -> " + map.get(user2));

        //Результат вывода:
        //Сравним два объекта: они равны? - false
        //hash-code user1: -1887340557
        //hash-code user2: -1887340557
        //{ru.job4j.map.User@8f8177f3=java.lang.Object@7f63425a, ru.job4j.map.User@8f8177f3=java.lang.Object@36d64342}
        //достали объект по ключу user1 -> java.lang.Object@7f63425a
        //достали объект по ключу user2 -> java.lang.Object@36d64342
    }

}