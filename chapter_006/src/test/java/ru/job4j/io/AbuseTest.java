package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AbuseTest {
    /*Этот класс позволяет создавать файлы и
     * директории во временном каталоге.*/
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void drop() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");

        /*Здесь мы создаем файл и заполняем его содержимое.*/
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("hello foolish dude");
        }
        /*Здесь мы выполняем действие программы, а далее читаем полученный файл.*/
        Abuse.drop(source.getAbsolutePath(), target.getAbsolutePath(),
                List.of("foolish"));
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        /*В конце мы проверяем результаты.*/
        assertThat(rsl.toString(), is("hello dude "));
    }
}