package ru.job4j.io.zip;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ArgZip {
    /**
     * Поле - хранит путь начального файла для архивации.
     */
    private String directory;
    /**
     * Поле - хранит расширения файлов, которые надо исключить из архива.
     */
    private List<String> exclude;
    /**
     * Поле - хранит путь расположение файла после архивации.
     */
    private String output;

    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

//     java -jar pack.jar -d=c:\project\job4j\ -e=class -o=project.zip

    /**
     * Проверяем соответствие входящего args необходимым критериям.
     */
    public boolean valid() {
        boolean result = true;
        List<String> stringList = Arrays.asList(args);
        if (!stringList.contains("-d")
                && !stringList.contains("-o")) {
            System.out.println("Args is not valid.");
            result = false;
        }
        return result;
    }

    /**
     * Метод возвращает путь начального файла для архивации.
     *
     * @return путь начального файла.
     */
    public String getDirectory() {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-d")) {
                directory = args[++i];
//                directory = new File(args[++i]).getAbsolutePath();
                break;
            }
        }
        return directory;
    }

    /**
     * Метод возвращает файл с исключающим расширением.
     *
     * @return файл с исключающем расширением
     */
    public List<String> getExclude() {
        List<String> result = new LinkedList<>();

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-e")) {
                result = Arrays.asList(args[++i].split(","));
                break;
            }
        }
        return result;
    }

    /**
     * Метод возвращает путь расположение файла после архивации.
     *
     * @return путь расположение файла после архивации
     */
    public String getOutput() {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-o")) {
                output = args[++i];
                break;
            }
        }
        return output;
    }
}
