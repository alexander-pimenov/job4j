package ru.job4j.io.zip;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    /*Метод упаковывающий в архив несколько файлов.*/
    public void packFiles(List<File> sources, File target) {

        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {

            for (File file : sources) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(file)
                )) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Метод упаковывающий в архив 1 файл.*/
    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<File> findFiles(ArgZip argZip) throws IOException {
        String directory = argZip.getDirectory();
        List<String> exclude = argZip.getExclude();
        List<File> result = new ArrayList<>();
        Queue<File> list = new LinkedList<>();
        list.offer(new File(directory));
        while (!list.isEmpty()) {
            File file = list.poll();
            if (file != null && file.isDirectory() && file.canRead()) {
                list.addAll(Arrays.asList(Objects.requireNonNull(file.listFiles())));
            } else if (checkName(file, exclude)) {
                result.add(file);
            }
        }
        return result;
    }

    private static boolean checkName(File file, List<String> exclude) {
        boolean result = true;
        for (String exc : exclude) {
            if (file.getName().endsWith(exc)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        new Zip().packSingleFile(
//                new File("./chapter_005/pom.xml"),
//                new File("./chapter_005/pom.zip")
//        );

//        new Zip().packFiles(List.of(new File("./chapter_005/pom.xml"),
//                new File("./chapter_006/pom.xml")),
//                new File("./chapter_005/pom2.zip")
//        );

        args = new String[]{"java", "-jar", "pack.jar", "-d", "c:/projects/job4j/chapter_004", "-e", "class", "-o", "projects_chapter_004.zip"};
//        args = new String[]{"java", "-jar", "pack.jar", "-d", "./chapter_004", "-e", "class", "-o", "projects_chapter_004_v2.zip"};
        ArgZip argZip = new ArgZip(args);
        if (argZip.valid()) {
            try {
//                findFiles(argZip).forEach(System.out::println);
                List<File> fileList = findFiles(argZip);
                new Zip().packFiles(fileList, new File(argZip.getOutput()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
