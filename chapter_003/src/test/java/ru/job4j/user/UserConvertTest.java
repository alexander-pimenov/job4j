package ru.job4j.user;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserConvertTest {

    @Test
    public void whenListConvertHashMap() {
        UserConvert userConvert = new UserConvert();
        List<User> userList = new ArrayList<>();
        userList.add(new User(11, "Vova", "Moscow"));
        userList.add(new User(22, "Nina", "Kursk"));
        userList.add(new User(33, "Petr", "Omsk"));

        HashMap<Integer, User> result = userConvert.process(userList);

        assertThat(result.get(11).getName(), is("Vova"));
        assertThat(result.get(22).getName(), is("Nina"));
        assertThat(result.get(33).getCity(), is("Omsk"));
    }
}
