package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleSetTest {

    private SimpleSet<Integer> set;

    @Before
    public void setup(){
        set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
    }

    @Test
    public void whenAddThreeDifferentElementThenSizeTgree(){
        assertThat(set.size(), is(3));
    }

    @Test
    public void whenHaveThreeDifferentAndAddTwoSameElementsThenHaveThreeAgain(){
        set.add(2);
        set.add(2);
        assertThat(set.size(), is(3));
    }

    @Test
    public void whenCallIterator(){
        Iterator<Integer> iterator = set.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void whenSetIsEmptyAndCallIteratorHasNext(){
        SimpleSet<Integer> set = new SimpleSet<>();
        Iterator<Integer> iterator = set.iterator();
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenSetIsEmptyAndCallIteratorNext(){
        SimpleSet<Integer> set = new SimpleSet<>();
        Iterator<Integer> iterator = set.iterator();
        iterator.next();
    }

    @Test
    public void whenSetHasNullElementAndCallIterator(){
        set.add(null);
        Iterator<Integer> iterator = set.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(3));
        assertNull(iterator.next());
        assertThat(iterator.hasNext(), is(false));

        assertThat(set.size(), is(4));
    }
}