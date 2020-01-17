package ru.job4j.list;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.google.common.collect.ImmutableList.copyOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleArrayListTest {
    private SimpleArrayList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
    }

    @Test
    public void whenAddThreeElementsAndCheckFirstElement() {
        assertThat(list.get(0), is(3));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    @Test(expected = NullPointerException.class)
    public void whenDeleteThanDeleteFirstElement() {
        assertThat(list.delete(), is(3));
        assertThat(list.getSize(), is(2));
        assertThat(list.get(0), is(2));
        assertThat(list.get(1), is(1));
        list.get(2);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteWhenEmptyList() {
        assertThat(list.delete(), is(3));
        assertThat(list.delete(), is(2));
        assertThat(list.delete(), is(1));
        assertThat(list.getSize(), is(0));
        list.delete();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetWhenEmptyList() {
        list.delete();
        list.delete();
        list.delete();
        list.get(0);
    }

    @Test
    public void whenCallIterator() {
        Iterator<Integer> itr = list.iterator();

        //Вызвали итератор 1-й раз
        Integer next1 = itr.next();
        assertThat(next1, is(3));

        //Вызвали итератор 2-й раз
        Integer next2 = itr.next();
        assertThat(next2, is(2));

        //Вызвали итератор 3-й раз
        Integer next3 = itr.next();
        assertThat(next3, is(1));

        //Проверяем размер списка.
        assertThat(list.getSize(), is(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenCallIteratorMoreThenHaveElements() {
        Iterator<Integer> itr = list.iterator();
        Integer next1 = itr.next();
        assertThat(next1, is(3));
        Integer next2 = itr.next();
        assertThat(next2, is(2));
        Integer next3 = itr.next();
        assertThat(next3, is(1));
        itr.next();
    }
}