package ru.job4j.cache;

import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SoftCacheTest {

    @Test
    public void getDataAddress() {
        //Создадим путь к папке resources (test), где лежит файл Address.txt
        Path resourceDirectory = Paths.get("src", "test", "resources");
        final String path = resourceDirectory.toFile().getAbsolutePath();
        System.out.println(path);

        SoftCache softCache = new SoftCache(path);
        String actual = softCache.getData("Address.txt");
        System.out.println(actual);
        String expected = String.join(System.lineSeparator(),
                "Moscow", "Minsk", "Belgorod");
        assertThat(actual, is(expected));
    }

    @Test
    public void getDataNames() throws URISyntaxException {
        //Создадим путь к папке resources (test), где лежит файл Names.txt
        String partPath = "src/test/resources";
        File file = new File(partPath);
        String path = file.getAbsolutePath();
        System.out.println(path);

        SoftCache softCache = new SoftCache(path);
        String actual = softCache.getData("Names.txt");
        System.out.println(actual);
        String expected = String.join(System.lineSeparator(),
                "Alex", "Pavel", "Bob", "Nick");
        assertThat(actual, is(expected));
    }

    @Test
    public void whenInvalidFileNameThenException() {
        //Создадим путь к папке resources (test), где нет файла Test.txt
        Path resourceDirectory = Paths.get("src", "test", "resources");
        final String path = resourceDirectory.toFile().getAbsolutePath();
        System.out.println(path);
        SoftCache softCache = new SoftCache(path);
        String actual = softCache.getData("Test.txt");
    }
}