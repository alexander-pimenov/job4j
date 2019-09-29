package ru.job4j.departments;

import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DepartmensSorter2Test {

    DepartmensSorter2 sorter2 = new DepartmensSorter2();

    @Test
    public void whenSortDeptRightAndAddNames(){

        Set<String> orgsTreeSet = new TreeSet<>();

        orgsTreeSet.add("K1/SK1");
        orgsTreeSet.add("K1/SK2");
        orgsTreeSet.add("K1/SK1/SSK1");
        orgsTreeSet.add("K1/SK1/SSK2");
        orgsTreeSet.add("K2");
        orgsTreeSet.add("K2/SK1/SSK1");
        orgsTreeSet.add("K2/SK1/SSK2");

        Set<String> orgsTreeExpected = Set.of(
                "K1",
                "K1/SK1",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K1/SK2",
                "K2",
                "K2/SK1",
                "K2/SK1/SSK1",
                "K2/SK1/SSK2"
        );

        TreeSet<String> stringTreeSet = sorter2.addDeptRightSort(orgsTreeSet);
        assertThat(stringTreeSet, is(orgsTreeExpected));

    }


    @Test
    public void whenSortDeptBackAndAddNames(){

        Set<String> orgsTreeSet = new TreeSet<>();

        orgsTreeSet.add("K1/SK1");
        orgsTreeSet.add("K1/SK2");
        orgsTreeSet.add("K1/SK1/SSK1");
        orgsTreeSet.add("K1/SK1/SSK2");
        orgsTreeSet.add("K2");
        orgsTreeSet.add("K2/SK1/SSK1");
        orgsTreeSet.add("K2/SK1/SSK2");

        Set<String> orgsTreeExpected = Set.of(
                "K2",
                "K2/SK1",
                "K2/SK1/SSK2",
                "K2/SK1/SSK1",
                "K1",
                "K1/SK2",
                "K1/SK1",
                "K1/SK1/SSK2",
                "K1/SK1/SSK1"
        );

        Set<String> stringTreeSet = sorter2.sortBack(orgsTreeSet);
        assertThat(stringTreeSet, is(orgsTreeExpected));
    }
}