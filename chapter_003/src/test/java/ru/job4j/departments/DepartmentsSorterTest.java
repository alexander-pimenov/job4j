package ru.job4j.departments;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class DepartmentsSorterTest {

    DepartmentsSorter sorter = new DepartmentsSorter();

    @Test
    public void whenSortDeptRight() {
        List<String> orgs = new ArrayList<>();
        orgs.add("K1/SK1");
        orgs.add("K1/SK2");
        orgs.add("K1/SK1/SSK1");
        orgs.add("K1/SK1/SSK2");
        orgs.add("K2");
        orgs.add("K2/SK1/SSK1");
        orgs.add("K2/SK1/SSK2");

        List<String> orgsExpected = List.of(
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

        sorter.sortRight(orgs);
        assertThat(orgs, is(orgsExpected));
    }

    @Test
    public void whenSortDeptBack() {

        List<String> orgs = new ArrayList<>();
        orgs.add("K1/SK1");
        orgs.add("K1/SK2");
        orgs.add("K1/SK1/SSK1");
        orgs.add("K1/SK1/SSK2");
        orgs.add("K2");
        orgs.add("K2/SK1/SSK1");
        orgs.add("K2/SK1/SSK2");

        List<String> orgsExpected = List.of(
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

        sorter.sortBack(orgs);
        assertThat(orgs, is(orgsExpected));
    }
}
