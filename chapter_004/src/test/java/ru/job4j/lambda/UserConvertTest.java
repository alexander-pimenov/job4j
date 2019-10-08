package ru.job4j.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserConvertTest {

    @Test
    public void whenConvert3NamesTo3Users() {
        List<String> names = Arrays.asList("Petr", "Nick", "Ben");
        UserConvert users = new UserConvert();
        List<UserConvert.User> data = users.convert(names, UserConvert.User::new);
        List<UserConvert.User> expected = new ArrayList<>();
        expected.add(new UserConvert.User("Petr"));
        expected.add(new UserConvert.User("Nick"));
        expected.add(new UserConvert.User("Ben"));

        assertThat(data, is(expected));
    }

}