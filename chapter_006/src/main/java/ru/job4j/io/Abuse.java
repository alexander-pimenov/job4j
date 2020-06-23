package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

public class Abuse {
    /**
     * Метод по удалению из файла запрещенных слов.
     * Метод принимает два файла и список слов для удаления.
     *
     * @param source файл откуда считываются данные
     * @param target файл, куда записывается результат
     * @param words  список слов для удаления
     * @throws IOException может выкинуть исключение
     */
    public static void drop(String source, String target, List<String> words) throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            in.lines()
                    .flatMap(line -> Stream.of(line.split("\\s+")))
                    .filter(word -> !words.contains(word)).map(word -> word + " ")
                    .forEach(out::print);
        }
    }
}
