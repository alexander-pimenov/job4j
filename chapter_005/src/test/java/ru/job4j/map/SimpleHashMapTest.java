package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {
    private SimpleHashMap<String, String> simpleHashMap = new SimpleHashMap<>();

    @Before
    public void setUp() {
        simpleHashMap.insert("1", "value1");
        simpleHashMap.insert("2", "value2");
        simpleHashMap.insert("3", "value3");
        simpleHashMap.insert("4", "value4");
        simpleHashMap.insert("5", "value5");
    }

    @Test
    public void whenCheckSizeTableAndAmountElements() {
        assertThat(simpleHashMap.size(), is(5));
        assertThat(simpleHashMap.capacity(), is(16));
    }

    @Test
    public void whenInsertSameKeyThenShouldFalseAndOldSize() {
        assertThat(simpleHashMap.size(), is(5));
        assertFalse(simpleHashMap.insert("1", "newValue1"));

        assertThat(simpleHashMap.get("1"), is("value1"));
        assertFalse(simpleHashMap.insert("2", "newValue2"));

        assertThat(simpleHashMap.size(), is(5));
    }

    @Test
    public void whenGetNonExistentKeyThenNull() {
        assertThat(simpleHashMap.get("3"), is("value3"));
        assertNull(simpleHashMap.get("6"));
    }

    @Test
    public void whenRemoveExistentElementThenRemovedOrFalse() {
        assertThat(simpleHashMap.size(), is(5));
        simpleHashMap.delete("2");
        assertThat(simpleHashMap.size(), is(4));
        assertThat(simpleHashMap.get("2"), nullValue());
        assertFalse(simpleHashMap.delete("qqq"));
    }

    @Test
    public void whenCallIterator() {
        Iterator<SimpleHashMap.Node<String, String>> iterator = simpleHashMap.iterator();
        System.out.println(simpleHashMap.size());
        System.out.println(simpleHashMap);
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
    }

    @Test
    public void whenUseAnotherType() {
        SimpleHashMap<Integer, String> simpHashMap = new SimpleHashMap<>();
        simpHashMap.insert(10, "val-10");
        simpHashMap.insert(20, "val-20");
        simpHashMap.insert(30, "val-30");
        simpHashMap.insert(40, "val-40");

        assertThat(simpHashMap.size(), is(4));
        assertFalse(simpHashMap.delete(5));
        assertThat(simpHashMap.get(20), is("val-20"));
        assertNull(simpHashMap.get(60));
    }

    @Test
    public void whenUseAnotherType2() {
        SimpleHashMap<UserHashAndEquals, String> simpHashMap2 = new SimpleHashMap<>();
        UserHashAndEquals user1 = new UserHashAndEquals("Alex", 1, new GregorianCalendar(1990, Calendar.APRIL, 15));
        UserHashAndEquals user2 = new UserHashAndEquals("Petr", 2, new GregorianCalendar(1991, Calendar.APRIL, 15));
        UserHashAndEquals user3 = new UserHashAndEquals("Nick", 3, new GregorianCalendar(1992, Calendar.APRIL, 15));
        UserHashAndEquals user4 = new UserHashAndEquals("Bin", 4, new GregorianCalendar(1993, Calendar.APRIL, 15));

        simpHashMap2.insert(user1, "val-10");
        simpHashMap2.insert(user2, "val-20");
        simpHashMap2.insert(user3, "val-30");
        System.out.println(simpHashMap2.size());
        System.out.println(simpHashMap2);

        assertThat(simpHashMap2.size(), is(3));
        assertTrue(simpHashMap2.delete(user2));
        System.out.println(simpHashMap2.size());
        System.out.println(simpHashMap2);

        assertThat(simpHashMap2.get(user1), is("val-10"));
        assertNull(simpHashMap2.get(user4));
    }
}

