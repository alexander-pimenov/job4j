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

    /**
     * Метод для чтения данных из файла.
     *
     * @param name название файла с расширением.
     * @return содержимое файла в строковом формате.
     */
    private String readFile(String name) {
        StringJoiner content = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(
                new FileReader(new File(sourceDir, name)))) {
            reader.lines().forEach(line -> content.add(line));
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return content.toString();
    }

    /**
     * Метод для получения данных из файла и сохранение его
     * в кеше.
     *
     * @param key название файла с расширением.
     * @return содержимое файла в строковом формате.
     */
    @Override
    public String getData(String key) {
        String content = "";
        //проверяем есть ли ключ в мапе
        if (softCache.containsKey(key)) {
            //если есть, то достаем строгую ссылку
            content = softCache.get(key).get();
            //проверяем не равна ли она null
            if (content == null) {
                //если равна, то читаем файл и добавляем в мапу
                content = readFile(key);
                softCache.put(key, new SoftReference<>(content));
            }
        } else {
            content = readFile(key);
            softCache.put(key, new SoftReference<>(content));
        }
        return content;
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
