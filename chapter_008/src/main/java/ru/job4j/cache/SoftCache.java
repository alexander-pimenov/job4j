package ru.job4j.cache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class SoftCache implements Cache<String, String> {

    private static final Logger LOG = LogManager.getLogger(SoftCache.class);
    /**
     * Путь к каталогу с файлами
     */
    private final String sourceDir;

    /**
     * Поле для кеша:
     * имя файла = содержимое файла
     */
    private Map<String, SoftReference<String>> softCache = new HashMap<>();

    /**
     * Конструктор.
     *
     * @param sourceDir путь к катлогу.
     */
    public SoftCache(String sourceDir) {
        this.sourceDir = sourceDir;
    }

    @Override
    public String getData(String key) {
        if (softCache.get(key) == null) {
            try (BufferedReader reader = new BufferedReader(
                    new FileReader(new File(sourceDir, key)))) {
                StringJoiner content = new StringJoiner(System.lineSeparator());
                reader.lines().forEach(line -> content.add(line));
                softCache.put(key, new SoftReference<>(content.toString()));
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
//                e.getMessage();
            }
        }
        return softCache.get(key).get();
    }

    public static void main(String[] args) {
        String path = new File("chapter_008\\src\\main\\resources").getAbsolutePath();
//        System.out.println(path);
        SoftCache softCache = new SoftCache(path);
        String addresses = softCache.getData("Address.txt");
        String names = softCache.getData("Names.txt");
        System.out.println(addresses);
        System.out.println();

        System.out.println(names);
        System.out.println();
    }
}
