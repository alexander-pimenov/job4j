package ru.job4j.io.search_by_criteria;

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
                String[] split = input.split("\\s+");
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
//есть проблемы с обработкой регулярных выражений, нужно исправлять, но есть и положительный результат с regEx:
// A*.java, *.xml, M.+,
// *.* -(запись всех файлов папки)
// .+ -(запись всех файлов папки)