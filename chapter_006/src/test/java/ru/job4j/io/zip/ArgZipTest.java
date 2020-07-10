package ru.job4j.io.zip;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ArgZipTest {
    private ArgZip argZip;

    @Before
    public void setUp() {
        String[] args = new String[]{"-d", "./chapter_006", "-e", "class,xml", "-o", "project_006.zip"};
//        String[] args2 = new String[]{"java", "-jar", "pack.jar", "-d", "c:/projects/job4j/chapter_006", "-e", "class", "-o", "projects_chapter_006.zip"};
        argZip = new ArgZip(args);
    }

    @Test
    public void whenGetDirectory() {
        String directory = argZip.getDirectory();
        assertEquals(directory, "./chapter_006");
    }

    @Test
    public void whenGetListExclude() {
        List<String> exclude = argZip.getExclude();
        assertEquals(exclude, List.of("class", "xml"));
    }

    @Test
    public void whenGetOutput() {
        String output = argZip.getOutput();
        assertEquals(output, "project_006.zip");
    }
}