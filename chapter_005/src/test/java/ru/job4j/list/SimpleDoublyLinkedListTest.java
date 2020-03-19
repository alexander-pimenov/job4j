package ru.job4j.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleDoublyLinkedListTest {

    @Test
    public void whenAddLastFiveElementsThenUseGet() {
        SimpleDoublyLinkedList<Integer> list2 = new SimpleDoublyLinkedList<>();
        list2.addLast(1);
        list2.addLast(2);
        list2.addLast(3);
        list2.addLast(4);
        list2.addLast(5);

        assertThat(list2.get(0), is(1));
        assertThat(list2.get(4), is(5));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddFirstElementsThenUseGetBiggerThenSizeThenException() {
        SimpleDoublyLinkedList<Integer> list = new SimpleDoublyLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);

        list.get(5);
    }

    @Test
    public void whenAddFirstThreeElementsAndCheckFirstElement() {
        SimpleDoublyLinkedList<Integer> list = new SimpleDoublyLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);

        assertThat(list.get(0), is(3));
    }

    @Test
    public void whenAddFirstThreeElementsThenUseGetSizeResultThree() {
        SimpleDoublyLinkedList<Integer> list = new SimpleDoublyLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);

        assertThat(list.getSize(), is(3));
    }

    @Test
    public void whenAddFirstThreeElementsAndNullThenUseGetSizeResultFive() {
        SimpleDoublyLinkedList<Integer> list = new SimpleDoublyLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(null);
        list.addFirst(null);

        assertThat(list.getSize(), is(5));
        assertNull(list.get(0));
    }

    @Test
    public void whenCreateNewListThenSizeZero() {
        SimpleDoublyLinkedList<Integer> list = new SimpleDoublyLinkedList<>();
        assertThat(list.getSize(), is(0));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetWhenEmptyList() {
        SimpleDoublyLinkedList<Integer> list = new SimpleDoublyLinkedList<>();
        list.get(0);
    }

    @Test
    public void whenAddLastFiveElementsThenGetSizeResultFive() {
        SimpleDoublyLinkedList<Integer> list2 = new SimpleDoublyLinkedList<>();
        list2.addLast(1);
        list2.addLast(2);
        list2.addLast(3);
        list2.addLast(4);
        list2.addLast(5);

        assertThat(list2.getSize(), is(5));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenDeleteThanDeleteFirstElement() {
        SimpleDoublyLinkedList<Integer> list = new SimpleDoublyLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);

        //метод delete() - удаляет первый элемент
        assertThat(list.deleteFirst(), is(3));
        assertThat(list.getSize(), is(2));
        assertThat(list.get(0), is(2));
        assertThat(list.get(1), is(1));
        list.get(2); //элемента с таким индексом нет, вышли за пределы списка
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenDeleteLastThanDeleteLastElement() {
        SimpleDoublyLinkedList<Integer> list2 = new SimpleDoublyLinkedList<>();
        list2.addLast(1);
        list2.addLast(2);
        list2.addLast(3);
        list2.addLast(4);
        list2.addLast(5);

        //метод deleteLast - удаляет последний элемент
        assertThat(list2.deleteLast(), is(5));
        assertThat(list2.getSize(), is(4));
        assertThat(list2.get(0), is(1));
        assertThat(list2.get(3), is(4));
        assertThat(list2.deleteLast(), is(4));
        list2.get(5);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteWhenEmptyList() {
        SimpleDoublyLinkedList<Integer> list = new SimpleDoublyLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);

        assertThat(list.deleteFirst(), is(3));
        assertThat(list.deleteFirst(), is(2));
        assertThat(list.deleteFirst(), is(1));
        assertThat(list.getSize(), is(0));
        list.deleteFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteLastWhenEmptyList() {
        SimpleDoublyLinkedList<Integer> list2 = new SimpleDoublyLinkedList<>();
        list2.addLast(1);
        list2.addLast(2);
        list2.addLast(3);
        list2.addLast(4);
        list2.addLast(5);

        //метод deleteLast - удаляет последний элемент
        assertThat(list2.deleteLast(), is(5));
        assertThat(list2.deleteLast(), is(4));
        assertThat(list2.deleteLast(), is(3));
        assertThat(list2.deleteLast(), is(2));
        assertThat(list2.deleteLast(), is(1));
        list2.deleteLast();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteAllElementsAndGetWhenEmptyList() {
        SimpleDoublyLinkedList<Integer> list = new SimpleDoublyLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);

        list.deleteFirst();
        list.deleteFirst();
        list.deleteFirst();
        //System.out.println(list.getSize()); //для просмотра size списка
        list.get(0);
    }

    @Test
    public void whenCallIterator() {
        SimpleDoublyLinkedList<Integer> list = new SimpleDoublyLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);

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
        SimpleDoublyLinkedList<Integer> list = new SimpleDoublyLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);

        Iterator<Integer> itr = list.iterator();
        Integer next1 = itr.next();
        assertThat(next1, is(3));
        itr.next();
        itr.next();
        itr.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCallIteratorHasNextAndStructuralModifyThenException() {
        SimpleDoublyLinkedList<Integer> list = new SimpleDoublyLinkedList<>();
        list.addFirst(5);
        list.addFirst(15);

        Iterator<Integer> itr = list.iterator();
        assertThat(itr.next(), is(15));
        assertThat(itr.next(), is(5));
        list.addFirst(3);
        itr.hasNext();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCallIteratorAndStructuralModifyDeleteThenException() {
        SimpleDoublyLinkedList<Integer> list = new SimpleDoublyLinkedList<>();
        list.addLast(5);
        list.addLast(15);
        list.addLast(25);

        Iterator<Integer> itr = list.iterator();
        assertThat(itr.next(), is(5));
        assertThat(itr.next(), is(15));
        list.deleteFirst(); // удаляет первый элемент
        itr.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCallIteratorAndStructuralModifyAddThenException() {
        SimpleDoublyLinkedList<Integer> list = new SimpleDoublyLinkedList<>();
        list.addLast(5);
        list.addLast(15);
        list.addLast(25);

        Iterator<Integer> itr = list.iterator();
        assertThat(itr.next(), is(5));
        assertThat(itr.next(), is(15));
        //System.out.println(list.get(0));
        list.addFirst(3);
        //System.out.println(list.get(2));
        //System.out.println(list.get(0));

        itr.next();
    }

    @Test
    public void whenCallIteratorTwiceAndCallHasNextThenFalse() {
        SimpleDoublyLinkedList<Integer> list = new SimpleDoublyLinkedList<>();
        list.addFirst(5);
        list.addFirst(15);

        Iterator<Integer> itr = list.iterator();
        assertThat(itr.next(), is(15));
        assertThat(itr.next(), is(5));
        assertFalse(itr.hasNext());
    }

    @Test
    public void whenEmptyListAndHasNextShouldFalse() {
        SimpleDoublyLinkedList<Integer> list = new SimpleDoublyLinkedList<>();
        Iterator<Integer> iterator = list.iterator();

        assertFalse(iterator.hasNext());
    }
}