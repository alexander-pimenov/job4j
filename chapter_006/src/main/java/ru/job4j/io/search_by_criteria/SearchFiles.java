package ru.job4j.io.search_by_criteria;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/*Экземпляр типа FileVisitor, будет определять поведение при обходе дерева.
 * Необходимо реализовать интерфейс FileVisitor, чтобы передать соответствующий объект в
 * метод walkFileTree(). Но если необходимости реализовывать все четыре метода этого интерфейса
 * нет, то можно просто расширить реализацию класса SimpleFileVisitor,
 * переопределив лишь необходимые методы.*/
public class SearchFiles extends SimpleFileVisitor<Path> {
    private Predicate<String> condition;
    private List<Path> fullPaths = new ArrayList<>();
    private List<Path> paths = new ArrayList<>();

    SearchFiles(Predicate<String> condition) {
        this.condition = condition;
    }

    List<Path> getFullPaths() {
        return fullPaths;
    }

    List<Path> getPaths() {
        return paths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        find(file);
        return FileVisitResult.CONTINUE;
    }

    private void find(Path path) {
        Path name = path.getFileName();
        if (condition.test(path.toFile().getName())) {
            paths.add(name); //список только имен файлов
            fullPaths.add(path); //список полных имен файлов, с путем к ним
        }
    }
}
