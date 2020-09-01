package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) {

        String path = "c:/projects/lessons-job4j/chapter_001";

        Path start = Paths.get(".").toAbsolutePath();

//        try {
//            search(start, s -> s.endsWith(".csv")
//            //        || s.endsWith(".sample")
//            ).forEach(System.out::println);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            search(Paths.get(path), s ->
                            s.equals("pom.xml")
//                            ||s.endsWith(".xml")
//                          ||s.contains("xml")
            )
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Метод возвращающий полное название файла (т.е. содержит путь к нему).
     * В предикате можно указывать несколько условий.
     * Изменив return можно получать только название файлов без пути к ним*/
    public static List<Path> search(Path root, Predicate<String> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getFullPaths();
//        return searcher.getPaths();
    }
}

/*Экземпляр типа FileVisitor, будет определять поведение при обходе дерева.
 * Необходимо реализовать интерфейс FileVisitor, чтобы передать соответствующий объект в
 * метод walkFileTree(). Но если необходимости реализовывать все четыре метода этого интерфейса
 * нет, то можно просто расширить реализацию класса SimpleFileVisitor,
 * переопределив лишь необходимые методы.*/
class SearchFiles extends SimpleFileVisitor<Path> {
    private Predicate<String> condition;
    private List<Path> fullPaths = new ArrayList<>(); //собирает название файла и путь к нему
    private List<Path> paths = new ArrayList<>(); //собирает только имя искомого файла

    SearchFiles(Predicate<String> condition) {
        this.condition = condition;
    }

    public List<Path> getFullPaths() {
        return fullPaths;
    }

    public List<Path> getPaths() {
        return paths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        find(file);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        find(dir);
        return FileVisitResult.CONTINUE;
    }

    private void find(Path path) {
        Path name = path.getFileName();
        if (condition.test(path.toFile().getName())) {

            //Path parent = path.getParent();
            //System.out.println("Directory name:" + parent);
            //Path fileName = path.getFileName();
            //System.out.println("Matching file:" + fileName);

            paths.add(name); //собирает только имя файла
            fullPaths.add(path); //собирает полный путь файла
        }
    }
}
