package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Search3Test {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenSearchFilesByExt() throws IOException {
        File file1 = folder.newFile("test1.abc");
        File file2 = folder.newFile("test2.abc");
        File file3 = folder.newFile("test3.abc");
        File file4 = folder.newFile("test4.bbb");
        File file5 = folder.newFile("test5.ccc");
        File file6 = folder.newFile("test6.ddd");
        File tempFolder = folder.newFolder("TempFolder");
        File file7 = folder.newFile("TempFolder/test7.bbb");
        File folderQQQ = folder.newFolder("TempFolder", "QQQ");
        File file8 = folder.newFile("TempFolder/QQQ/test8.ccc");
        File folderZZZ = folder.newFolder("TempFolder", "ZZZ");
        File file9 = folder.newFile("TempFolder/ZZZ/test9.abc");

        /* Чтобы в метод search() передать путь по которому искать файлы,
         * определим путь где создадутся временные файлы и потом
         * передадим его в параметр метода*/
        String parent = file1.getParent();
        //System.out.println(parent);

        List<Path> listExtABC = Search3.search(Paths.get(parent), "abc");
        List<Path> listExtBBB = Search3.search(Paths.get(parent), "bbb");
        //System.out.println(listExtABC);

        assertThat(listExtABC.toString(), is(List.of("test9.abc", "test1.abc", "test2.abc", "test3.abc").toString()));
        assertThat(listExtBBB.size(), is(2));

    }
}