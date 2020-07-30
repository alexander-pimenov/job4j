package ru.job4j.io.search_by_criteria;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

/*В методе writeResult() можем раскомментировать строку и записывать в лог-файл только
 * имена найденных файлов, а не полный путь файла.
 * Так ж мы можем только во время поиска в методе search() сохранять простые имена файлов а не полное их имя.
 * */

public class SearchAndSave {
    private static final String LN = System.lineSeparator();

//    public static void main(String[] args) {
//        String path = "c:/projects/lessons-job4j/chapter_002";
//        String log = "logFile2.txt";
//
//        List<Path> pathList = null;
//        try {
//            pathList = search(Paths.get(path), s ->
//                    s.equals("pom.xml")
//                            || s.equals(".xml")
//                            || s.equals("xml"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        writeResult(pathList, log);
//    }

    /* Метод возвращающий полное название файла (т.е. содержит путь к нему).
     * В предикате можно указывать несколько условий.
     * Изменив return можно получать только название файлов без пути к ним*/
    public static List<Path> search(Path root, Predicate<String> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getFullPaths();
//        return searcher.getPaths(); //***этот вариант если нам нужн искать только имена файлов без пути к ним
    }

    /*Метод записи найденных файлов в файл-лог на компьютере*/
    public static void writeResult(List<Path> listFiles, String fileLog) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileLog))) {
            if (listFiles != null && !listFiles.isEmpty()) {
                bw.write("Found files: " + LN);
                for (Path str : listFiles) {
                    bw.write(str + LN);
//                    bw.write(str.getFileName() + LN); //***этот вариант если нам нужно записать только имена файлов
                }
                System.out.println("Save completed.");
            } else {
                bw.write("No files found.");
            }
            bw.flush();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}


