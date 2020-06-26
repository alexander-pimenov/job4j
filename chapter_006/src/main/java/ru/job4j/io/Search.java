package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Search {

    public static void main(String[] args) throws IOException {
        /*Программа выводит содержимое всей директории.*/
        Path start = Paths.get(".");

        search(start, "csv").forEach(System.out::println);
    }

    /*Метод возвращает список файлов с искомым расширением*/
    public static List<Path> search(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(ext);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}

/*Экземпляр типа FileVisitor, будет определять поведение при обходе дерева.
 * Необходимо реализовать интерфейс FileVisitor, чтобы передать соответствующий объект в
 * метод walkFileTree(). Но если необходимости реализовывать все четыре метода этого интерфейса
 * нет, то можно просто расширить реализацию класса SimpleFileVisitor,
 * переопределив лишь необходимые методы.*/
class SearchFiles extends SimpleFileVisitor<Path> {
    private PathMatcher matcher;
    private List<Path> paths = new ArrayList<>();

    SearchFiles(String ext) {
        String pattern = "glob:*." + ext;
        try {
            this.matcher = FileSystems.getDefault().getPathMatcher(pattern);
        } catch (IllegalArgumentException iae) {
            System.exit(1);
        }
    }

    List<Path> getPaths() {
        return paths;
    }

    public FileVisitResult visitFile(Path path,
                                     BasicFileAttributes fileAttributes) {
        find(path);
        return FileVisitResult.CONTINUE;
    }

    private void find(Path path) {
        Path name = path.getFileName();
        if (matcher.matches(name)) {
            //Path parent = path.getParent();
            //System.out.println("Directory name:" + parent);
            //Path fileName = path.getFileName();
            //System.out.println("Matching file:" + fileName);

            /*сохраняем имя файла в список*/
            paths.add(name);
        }
    }

    public FileVisitResult preVisitDirectory(Path path,
                                             BasicFileAttributes fileAttributes) {
        find(path);
        return FileVisitResult.CONTINUE;
    }
}


