package ru.job4j.cache;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SoftCacheTest {

    private final String path = new File("src\\main\\resources").getAbsolutePath();

    @Test
    public void getDataAddress() {
        System.out.println(path);
        SoftCache softCache = new SoftCache(path);
        String actual = softCache.getData("Address.txt");
        System.out.println(actual);
        String expected = String.join(System.lineSeparator(),
                "Moscow", "Minsk", "Belgorod");
        assertThat(actual, is(expected));
    }

    @Test
    public void getDataNames() {
        SoftCache softCache = new SoftCache(path);
        String actual = softCache.getData("Names.txt");
        System.out.println(actual);
        String expected = String.join(System.lineSeparator(),
                "Alex", "Pavel", "Bob", "Nick");
        assertThat(actual, is(expected));
    }

    @Test
    public void whenInvalidFileNameThenException() {
        SoftCache softCache = new SoftCache(path);
        String actual = softCache.getData("Test.txt");
    }
}