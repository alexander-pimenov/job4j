package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class DynamicSimpleArrayListTest {

    private DynamicSimpleArrayList<Integer> list;


    @Before
    public void beforeTest() {
        list = new DynamicSimpleArrayList<>();
    }

    @Test
    public void whenCreateNewArrayListThenSizeZero() {
        assertThat(list.size(), is(0));
    }

    @Test
    public void whenCreateNewArrayListWithCapacity20ThenSizeZero() {
        DynamicSimpleArrayList<Object> list2 = new DynamicSimpleArrayList<>(20);
        assertThat(list2.size(), is(0));
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTen() {
        list.add(5);
        list.add(10);
        list.add(15);

        assertThat(list.get(1), is(10));
    }

    @Test
    public void whenAddThreeElementsAndNullShouldSeeAllOfThem() {
        list.add(5);
        list.add(10);
        list.add(15);
        list.add(null);
        list.add(null);

        assertThat(list.get(1), is(10));
        assertThat(list.get(2), is(15));
        assertNull(list.get(3));
        assertNull(list.get(4));
    }


    @Test
    public void whenCreateEmptyListThenSizeZero() {
        assertThat(list.size(), is(0));
    }

    @Test
    public void whenAddThreeElementsThenSizeThree() {
        list.add(5);
        list.add(10);
        list.add(15);
        assertThat(list.size(), is(3));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenGetWhenEmptyList() {
        list.get(0);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddThreeAndGetFourIndexThenException() {
        list.add(5);
        list.add(15);
        list.add(25);

        list.get(4);
    }

    @Test
    public void whenCallIterator() {
        list.add(5);
        list.add(15);
        list.add(25);

        Iterator<Integer> itr = list.iterator();

        //Вызвали итератор 1-й раз
        Integer next1 = itr.next();
        assertThat(next1, is(5));

        //Вызвали итератор 2-й раз
        Integer next2 = itr.next();
        assertThat(next2, is(15));

        //Вызвали итератор 3-й раз
        Integer next3 = itr.next();
        assertThat(next3, is(25));

        //Проверяем размер списка.
        assertThat(list.size(), is(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenCallIteratorMoreThenHaveElements() {
        list.add(5);
        list.add(15);
        list.add(25);

        Iterator<Integer> itr = list.iterator();
        itr.next();
        itr.next();
        itr.next();
        itr.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCallIteratorNextAndStructuralModifyThenException() {
        list.add(5);
        list.add(15);
        Iterator<Integer> itr = list.iterator();
        assertThat(itr.next(), is(5));
        assertThat(itr.next(), is(15));
        list.add(3);
        itr.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCallIteratorHasNextAndStructuralModifyThenException() {
        list.add(5);
        list.add(15);

        Iterator<Integer> itr = list.iterator();
        assertThat(itr.next(), is(5));
        assertThat(itr.next(), is(15));
        list.add(3);
        itr.hasNext();
    }

    @Test
    public void whenCallIteratorTwiceAndCallHasNextThenFalse() {
        list.add(5);
        list.add(15);

        Iterator<Integer> itr = list.iterator();
        assertThat(itr.next(), is(5));
        assertThat(itr.next(), is(15));
        assertFalse(itr.hasNext());
    }

    @Test
    public void whenEmptyListAndHasNextShouldFalse() {
        Iterator<Integer> iterator = list.iterator();

        assertFalse(iterator.hasNext());
    }
}
