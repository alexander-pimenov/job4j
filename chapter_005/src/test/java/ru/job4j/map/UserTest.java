package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void whenEqualsAndHashCodeAreNotOverride() {
        //создаем два объекта с одинаковыми полями
        User user1 = new User("Alex", 2, new GregorianCalendar(1990, Calendar.APRIL, 15));
        User user2 = new User("Alex", 2, new GregorianCalendar(1990, Calendar.APRIL, 15));

        //Посмотрим на хэшкоды наших объектов
        //Т.к. алгоритм генерации хешкода для объекта работает с участием генератора случайных чисел,
        //то при каждом выполнении программы хеш-коды объектов будут разными.
        //Реализация по умолчанию hashCode() (идентификационного хеша) не имеет отношения к адресу
        //памяти объекта, как минимум в OpenJDK. До этого это было целочисленное отображение
        //области памяти. В версиях 6 и 7 это случайно генерируемое число.
        //В версиях 8 и 9 это число, полученное на основании состояния потока.
        System.out.println("hash-code user1: " + user1.hashCode());
        System.out.println("hash-code user2: " + user2.hashCode());

        //добавить объекты в карту
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());

        //в результате увидим две записи в карте, у которых буду различные ключи и различные значения.
        //не смотря на одинаковые со стороны бизнес логики объекты User1 и User2 все же различные,
        //т.к. у них не переопределены методы equals() и hashCode()
        System.out.println(map);

        System.out.println("достали объект по ключу user1 -> " + map.get(user1));
        System.out.println("достали объект по ключу user2 -> " + map.get(user2));
    }

}