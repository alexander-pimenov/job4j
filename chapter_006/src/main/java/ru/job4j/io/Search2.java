package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Search2 {

    public static void main(String[] args) throws IOException {
        /*Программа выводит содержимое всей директории.*/
//        Path start = Paths.get(".");
        Path start = Paths.get("C:/projects/lessons-job4j/chapter_001");

//        search(start, "csv").forEach(System.out::println);
        search(start, "xml").forEach(System.out::println);
        System.out.println("====================");
//        search2(start, "csv").forEach(p -> System.out.println(p.toString()));
        search2(start, "xml").forEach(p -> System.out.println(p.toString()));
    }

    /*Метод возвращает список файлов с искомым расширением*/
    public static List<Path> search(Path root, String ext) throws IOException {
        SearchFiles2 searcher = new SearchFiles2(ext);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    /*Метод возвращает список файлов с искомым расширением. Файл содержит в себе название директории,
    * где он лежит и его название с расширением.*/
    public static List<Searcher2> search2(Path root, String ext) throws IOException {
        SearchFiles2 searcher = new SearchFiles2(ext);
        Files.walkFileTree(root, searcher);
        return searcher.getSearchers();
    }
}

class SearchFiles2 extends SimpleFileVisitor<Path> {
    private PathMatcher matcher;
    private List<Path> paths = new ArrayList<>();
    private List<Searcher2> searchers = new ArrayList<>();

    SearchFiles2(String ext) {
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

    List<Searcher2> getSearchers() {
        return searchers;
    }

    public FileVisitResult visitFile(Path path,
                                     BasicFileAttributes fileAttributes) {
        find(path);
        return FileVisitResult.CONTINUE;
    }

    private void find(Path path) {
        Path name = path.getFileName();
        if (matcher.matches(name)) {
            Path parent = path.getParent();
            //System.out.println("Directory name:" + parent);
            Path fileName = path.getFileName();
            //System.out.println("Matching file:" + fileName);

            /*сохраняем имя файла в список*/
//            paths.add(name); //будет сохраняться только имя файла
            paths.add(path); //будет сохраняться файл с указанием его пути

            /*сохраняем не только имя файла, но и название его директории*/
            searchers.add(new Searcher2(parent.toString(), fileName.toString()));
        }
    }

    public FileVisitResult preVisitDirectory(Path path,
                                             BasicFileAttributes fileAttributes) {
        find(path);
        return FileVisitResult.CONTINUE;
    }
}

/*Класс для хранения данных о файле: директория и название файла*/
class Searcher2 {
    private String nameDir;
    private String nameFile;

    Searcher2(String nameDir, String nameFile) {
        this.nameDir = nameDir;
        this.nameFile = nameFile;
    }

    public String getNameDir() {
        return nameDir;
    }

    public String getNameFile() {
        return nameFile;
    }

    @Override
    public String toString() {
        return "{" +
                "nameDir='" + nameDir + '\'' +
                ", nameFile='" + nameFile + '\'' +
                '}';
    }
}