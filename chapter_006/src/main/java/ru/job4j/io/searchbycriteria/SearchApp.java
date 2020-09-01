package ru.job4j.io.searchbycriteria;

import com.google.common.base.Joiner;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class SearchApp {
    private final static String LN = System.lineSeparator();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean exit = false;
        ArgsParser argsParser = null;

        /*Используется цикл для множественных поисков и записей файлов.*/
        do {
            printMenu();
            System.out.println("If you change your mind to act, then press e(xit) to exit.");
            System.out.println("Or act as prompted.");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("e") || input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye.");
                exit = true;
            } else {
                System.out.println("continue");
                String[] split = input.split("\\s+"); //разделитель "\\s+" - это один или более пробелов.
                try {
                    argsParser = new ArgsParser(split);
                    List<Path> files = SearchAndSave.search(Paths.get(argsParser.getDirectory()), argsParser.flagCheck());
                    SearchAndSave.writeResult(files, argsParser.getOutput());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } while (!exit);
    }

    private static void printMenu() {
        System.out.println(
                Joiner.on(LN).join(
                        "Enter command line arguments like this: ",
                        "-d directoryForSearch -n criteriaForSearch -[mfr] -o fileForSaveResult",
                        "Key -d: search directory",
                        "the directory in which the search will be performed must match the format:",
                        "C:/.../...",
                        "Key -n: file name (mask, ful name, regular expression);",
                        "Key -m: Search by mask of file;",
                        "Key -f: Search by a complete match for a file name;",
                        "Key -r: Search by regular expression;",
                        "Key -o: file to write.\r"
                )
        );
    }
}

// комментарий для себя:
//-d c:/projects/job4j/chapter_001 -n B.+ -r -o log_found_files_chapter_001.txt
// файл "log_found_files_chapter_001.txt" появляется в корне проекта.
// A*.java, *.xml, M.+ - это поиск по маске
// *.* -(запись всех файлов папки) - попадают абсолютно все файлы, поскольку набор произвольных
// символов указывается как для имени, так и для расширения файла. - это поиск по маске
// .+ -(запись всех файлов папки) - это поиск по регулярке
// bo*.* - ищет все файлы начинающиеся на bo и потом какие-то символы и с любым расширением.
// - это поиск по маске
//
//поиск по регулярному выражению. отработал нормально.
//-d c:/projects/job4j/chapter_001 -n Matrix.+ -r -o log_found_files_chapter_001_12.txt
//-d c:/projects/job4j/chapter_001 -n [dD].+.* -r -o log_found_files_chapter_001_6.txt
//-d c:/projects/job4j/chapter_001 -n .+.xml -r -o log_found_files_chapter_001_7.txt
//-d c:/projects/job4j/chapter_001 -n [dD].+.java -r -o log_found_files_chapter_001_5.txt
//-d c:/projects/job4j/chapter_001 -n .+\Q.\Eclass -r -o log_found_files_chapter_001_11.txt - где \Q.\E экранируем точку
//-d c:/projects/job4j/chapter_001 -n .+ -r -o log_found_files_chapter_001_1.txt - поиск всех файлов в каталоге
//
//поиск по маске. отработал нормально
//-d c:/projects/job4j/chapter_001 -n M*.java -m -o log_found_files_chapter_001_14.txt
//-d c:/projects/job4j/chapter_001 -n *oin*.java -m -o log_found_files_chapter_001_16.txt
//-d c:/projects/job4j/chapter_001 -n M*.* -m -o log_found_files_chapter_001_18.txt
//-d c:/projects/job4j/chapter_001 -n [mM]??.* -m -o log_found_files_chapter_001_8.txt