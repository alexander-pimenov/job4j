package ru.job4j.generic;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {
    @Test
    public void whenAddTwoStringElementsShouldSeeTwoStringElementsInArray() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);

        simpleArray.add("qq");
        simpleArray.add("aa");
        System.out.println(simpleArray);

        String[] expected = {"qq", "aa", null, null, null};
        System.out.println(Arrays.toString(expected));

        assertThat(simpleArray.toString(), is(Arrays.toString(expected)));
    }

    @Test
    public void whenAddStringElementsAndNullShouldSeeStringElementsAndNullInArray() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);

        simpleArray.add("qq");
        simpleArray.add("aa");
        simpleArray.add(null);
        simpleArray.add(null);
        simpleArray.add("zz");
        //System.out.println(simpleArray);

        String[] expected = {"qq", "aa", null, null, "zz"};
        //System.out.println(Arrays.toString(expected));

        assertThat(simpleArray.toString(), is(Arrays.toString(expected)));
    }

    @Test
    public void whenAddTwoElementsShouldSeeTwoElementsAndOtherNullInArray() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);

        simpleArray.add("qq");
        simpleArray.add("aa");
        //System.out.println(simpleArray);

        String[] expected = {"qq", "aa", null, null, null};
        //System.out.println(Arrays.toString(expected));
        //System.out.println(simpleArray.get(3));
        //System.out.println(simpleArray.get(4));

        assertThat(simpleArray.toString(), is(Arrays.toString(expected)));
        assertNull(simpleArray.get(3));
        assertNull(simpleArray.get(4));
    }

    @Test
    public void whenAddThreeStringElementsShouldGetThreeStringElements() {
        SimpleArray<String> simpleArray = new SimpleArray<>(3);

        simpleArray.add("qq");
        simpleArray.add("aa");
        simpleArray.add("zz");

        String[] result = new String[3];
        result[0] = simpleArray.get(0); // "qq"
        result[1] = simpleArray.get(1); // "aa"
        result[2] = simpleArray.get(2); // "zz"
        String[] expected = {"qq", "aa", "zz"};

        assertThat(result, is(expected));
    }

    @Test
    public void whenAddThreeStringElementsAndNullShouldGetThreeStringElementsAndNull() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);

        simpleArray.add("qq");
        simpleArray.add("aa");
        simpleArray.add(null);
        simpleArray.add("zz");
        simpleArray.add(null);
        //System.out.println(simpleArray);

        String[] result = new String[5];
        result[0] = simpleArray.get(0); // "qq"
        result[1] = simpleArray.get(1); // "aa"
        result[2] = simpleArray.get(2); // null
        result[3] = simpleArray.get(3); // "zz"
        result[4] = simpleArray.get(4); // null
        String[] expected = {"qq", "aa", null, "zz", null};
        //System.out.println(Arrays.toString(result));

        assertThat(result, is(expected));
    }

    @Test
    public void whenAddTwoIntElementsShouldGetTwoIntElements() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(5);

        simpleArray.add(1);
        simpleArray.add(2);

        int[] result = new int[2];
        result[0] = simpleArray.get(0);
        result[1] = simpleArray.get(1);

        int[] expected = {1, 2};

        assertThat(result, is(expected));
    }

    @Test
    public void whenAddLastElementsShouldGetLastElements() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(5);

        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);

        assertThat(simpleArray.get(2), is(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddLastElementsAddCallIndexBiggerThanSize() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(5);

        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);

        simpleArray.get(6);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddLastElementButBiggerThanSize() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(2);

        simpleArray.add(1);
        simpleArray.add(2);
        //добавляем 3-й элемент, а размер списка равен 2
        simpleArray.add(3);
    }

    @Test
    public void whenAddElementsThenRemoveOneShouldGetNullAtTheEnd() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(5);

        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(4);
        simpleArray.add(5);
        simpleArray.remove(3);
        //System.out.println(simpleArray.toString());

        Integer[] expected = {1, 2, 3, 5, null};
        List<Integer> list = new ArrayList<>();
        for (Integer i : expected) {
            list.add(i);
        }
        //System.out.println(list.toString());

        String result = simpleArray.toString();

        assertThat(result, is(list.toString()));
    }

    @Test
    public void whenSetElementThenSets() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);

        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        //System.out.println(simpleArray);

        SimpleArray<Integer> result = simpleArray;
        result.set(1, 100);
        //System.out.println(result);

        assertThat(result.get(1), is(100));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetElementWithIndexBiggerThanRange() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);

        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);

        simpleArray.set(5, 100);
    }

    @Test
    public void whenUseSimpleArrayIterator() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);

        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);

        Iterator<Integer> iterator = simpleArray.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenUseSimpleArrayIteratorWithException() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);

        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);

        Iterator<Integer> iterator = simpleArray.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }

    // Нижний тест закомментирован, т.к. было внесено изменение в код get(), убрал проверку
//
//        if (this.objects[index] == null) {
//            throw new NoSuchElementException();
//        }
//
// для того чтоб мы могли получать null из SimpleArray, а не получать NoSuchElementException.
//
//    @Test(expected = NoSuchElementException.class)
//    public void whenAddLastElementsAddCallIndexBiggerLastButSmallerSize() {
//        SimpleArray<Integer> simpleArray = new SimpleArray<>(5);
//
//        simpleArray.add(1);
//        simpleArray.add(2);
//        simpleArray.add(3);
//
//        simpleArray.get(4);
//    }


}