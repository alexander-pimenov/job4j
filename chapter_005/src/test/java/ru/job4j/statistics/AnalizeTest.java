package ru.job4j.statistics;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizeTest {

    Analize analize = new Analize();
    List<Analize.User> prevList = new ArrayList<>();
    List<Analize.User> currList = new ArrayList<>();

    @Before
    public void setUp() {
        prevList.add(new Analize.User(1, "Nick"));
        prevList.add(new Analize.User(2, "Bob"));
        prevList.add(new Analize.User(3, "Alex"));
    }

    @Test
    public void whenAddOne() {
        currList.add(new Analize.User(1, "Nick"));
        currList.add(new Analize.User(2, "Bob"));
        currList.add(new Analize.User(3, "Alex"));
        currList.add(new Analize.User(4, "NewName"));

        Analize.Info info = analize.diff(prevList, currList);
        assertThat(info.getAdded(), is(1));
        assertThat(info.getDeleted(), is(0));
        assertThat(info.getChanged(), is(0));
    }

    @Test
    public void whenAddOneAndChangedOne() {
        currList.add(new Analize.User(1, "Nick"));
        currList.add(new Analize.User(2, "Bob"));
        currList.add(new Analize.User(3, "Alex Changed"));
        currList.add(new Analize.User(4, "NewName"));

//        for (Analize.User user :currList){
//            System.out.println(user.toString());
//        }

        Analize.Info info = analize.diff(prevList, currList);
        assertThat(info.getAdded(), is(1));
        assertThat(info.getDeleted(), is(0));
        assertThat(info.getChanged(), is(1));
    }

    @Test
    public void whenDeletedOne() {
        currList.add(new Analize.User(1, "Nick"));
        currList.add(new Analize.User(2, "Bob"));

        Analize.Info info = analize.diff(prevList, currList);
        assertThat(info.getAdded(), is(0));
        assertThat(info.getDeleted(), is(1));
        assertThat(info.getChanged(), is(0));
    }

    @Test
    public void whenAddOneAndDeletedOne() {
        currList.add(new Analize.User(1, "Nick"));
        currList.add(new Analize.User(2, "Bob"));
        currList.add(new Analize.User(4, "NewName"));

        Analize.Info info = analize.diff(prevList, currList);
        assertThat(info.getAdded(), is(1));
        assertThat(info.getDeleted(), is(1));
        assertThat(info.getChanged(), is(0));
    }

    @Test
    public void whenAddOneAndDeletedOneAndChangedOne() {
        currList.add(new Analize.User(1, "Nick"));
        currList.add(new Analize.User(3, "Alex Changed"));
        currList.add(new Analize.User(4, "NewName"));

        Analize.Info info = analize.diff(prevList, currList);
        assertThat(info.getAdded(), is(1));
        assertThat(info.getDeleted(), is(1));
        assertThat(info.getChanged(), is(1));
    }

}