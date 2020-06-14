package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserHashAndEqualsTest {
    @Test
    public void whenHashCodeAndEqualsOverrided() {
        //создаем два объекта с одинаковыми полями
        UserHashAndEquals user1 = new UserHashAndEquals("Alex", 2, new GregorianCalendar(1990, Calendar.APRIL, 15));
        UserHashAndEquals user2 = new UserHashAndEquals("Alex", 2, new GregorianCalendar(1990, Calendar.APRIL, 15));

        System.out.println("Сравним два объекта: они равны? - " + user1.equals(user2));

        System.out.println("hash-code user1: " + user1.hashCode());
        System.out.println("hash-code user2: " + user2.hashCode());

        Map<UserHashAndEquals, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());

        System.out.println(map);

        System.out.println("достали объект по ключу user1 -> " + map.get(user1));
        System.out.println("достали объект по ключу user2 -> " + map.get(user2));

        //Результат вывода: имеем два одинаковых объекта поэтому в мапе при записи по одинаковому ключу
        //идет перезатирание данных. И в мапе будет только одна запись
        //Сравним два объекта: они равны? - true
        //hash-code user1: -1887340557
        //hash-code user2: -1887340557
        //{ru.job4j.map.UserHashAndEquals@8f8177f3=java.lang.Object@17c68925}
        //достали объект по ключу user1 -> java.lang.Object@17c68925
        //достали объект по ключу user2 -> java.lang.Object@17c68925
    }

    @Test
    public void whenKeyIsNull() {
        //создаем два объекта с одинаковыми полями
        UserHashAndEquals user1 = new UserHashAndEquals("Alex", 2, new GregorianCalendar(1990, Calendar.APRIL, 15));
        UserHashAndEquals user2 = new UserHashAndEquals("Alex", 2, new GregorianCalendar(1990, Calendar.APRIL, 15));
        UserHashAndEquals user3 = new UserHashAndEquals(null, 0, null);
        UserHashAndEquals user4 = new UserHashAndEquals("Felix", 3, new GregorianCalendar(1995, Calendar.AUGUST, 20));

        System.out.println("Сравним два объекта: они равны? - " + user1.equals(user2));

        System.out.println("hash-code user1: " + user1.hashCode());
        System.out.println("hash-code user2: " + user2.hashCode());
        System.out.println("hash-code user3: " + user3.hashCode());
        System.out.println("hash-code user4: " + user4.hashCode());

        Map<UserHashAndEquals, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object()); //это добавление перезатрет первую запись
        map.put(user3, new Object());
        map.put(user4, new Object());

        System.out.println(map);

        System.out.println("достали объект по ключу user1 -> " + map.get(user1));
        System.out.println("достали объект по ключу user2 -> " + map.get(user2));
        System.out.println("достали объект по ключу user3 -> " + map.get(user3));
        System.out.println("достали объект по ключу user4 -> " + map.get(user4));

        //Вычислим хэш-код по формуле. Эта формула хороша, чтобы было максимально
        //равномерное распределение ключей по бакетам мапы, чтобы было
        //меньше коллизий
        int hash1 = user1.hashCode() ^ user1.hashCode() >>> 16;
        int hash2 = user2.hashCode() ^ user2.hashCode() >>> 16;
        int hash3 = user3.hashCode() ^ user3.hashCode() >>> 16;
        int hash4 = user4.hashCode() ^ user4.hashCode() >>> 16;
        System.out.println("hash1 = " + hash1);
        System.out.println("hash2 = " + hash2);
        System.out.println("hash3 = " + hash3);
        System.out.println("hash4 = " + hash4);
        System.out.println("================================");

        /*
        * Поссмотрим на вычисление номера корзины в HashMap.
        * по формуле index = hash % n
        * или index = hash & (n-1)
        * где n=16 - начальное число ячеек массива у HashMap
        */
        System.out.println("index = " + (Math.abs(hash1) % 16));
        System.out.println("index = " + (Math.abs(hash3) % 16));
        System.out.println("index = " + (Math.abs(hash4) % 16));
        System.out.println("index = " + (hash1 & 15));
        System.out.println("index = " + (hash3 & 15));
        System.out.println("index = " + (hash4 & 15));


        System.out.println("================================");
        /*
         * Проверим, как работает hashCode() у объектов String
         * Как видим ниже, одинаковые объекты String имеют один и тот же хэш-код,
         * т.к. hashCode() у String переопределен, а вот у StringBuilder
         * метод hashCode() не переопределен, поэтому одинаковые по бизнес-логике
         * объекты имеют разные хэш-коды.
         */
        String s = "OK";
        StringBuilder sb = new StringBuilder(s);
        System.out.println(s.hashCode() + " " + sb.hashCode()); //2524 2114889273
        String t = new String("OK");
        StringBuilder tb = new StringBuilder(t);
        System.out.println(t.hashCode() + " " + tb.hashCode()); //2524 932607259

        //Результат вывода:
        //hash-code user1: -1887340557
        //hash-code user2: -1887340557
        //hash-code user3: 29791
        //{ru.job4j.map.UserHashAndEquals@8f8177f3=java.lang.Object@19dfb72a, ru.job4j.map.UserHashAndEquals@745f=java.lang.Object@17c68925}
        //достали объект по ключу user1 -> java.lang.Object@19dfb72a
        //достали объект по ключу user2 -> java.lang.Object@19dfb72a
        //достали объект по ключу user3 -> java.lang.Object@17c68925
        //hash1 = -1887307662
        //hash2 = -1887307662
        //hash3 = 29791
        //================================
        //2524 2114889273
        //2524 932607259


    }

}