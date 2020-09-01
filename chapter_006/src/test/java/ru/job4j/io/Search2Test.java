package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class Search2Test {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenSearchFileByExt() throws IOException {
        File file1 = folder.newFile("test1.abc");
        File file2 = folder.newFile("test2.abc");
        File file3 = folder.newFile("test3.abc");
        File file4 = folder.newFile("test4.bbb");
        File file5 = folder.newFile("test5.ccc");
        File file6 = folder.newFile("test6.ddd");

        /* Чтобы в метод search() передать путь по которому искать файлы,
         * определим путь где создадутся временные файлы и потом
         * передадим его в параметр метода: search(Paths.get(parent))*/
        String parent = file1.getParent();

        List<Path> listExt = Search2.search(Paths.get(parent), "abc");
        System.out.println(listExt);
        List<Path> fileNames = new ArrayList<>();
        for (Path path : listExt) {
            final Path fileName = path.getFileName();
            fileNames.add(fileName);
        }
        List<Searcher2> listExt2 = Search2.search2(Paths.get(parent), "abc");
        System.out.println(listExt2);

        assertThat(fileNames.toString(), is(List.of("test1.abc", "test2.abc", "test3.abc").toString()));
    }
}