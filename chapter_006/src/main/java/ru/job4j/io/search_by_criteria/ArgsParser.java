package ru.job4j.io.search_by_criteria;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgsParser {
    private String directory;
    private String searchFile;
    private String output;
    private final String[] args;

    //мапа для хранения ключа аргумента и его значения, полученных из строки аргументов
    private Map<String, String> arguments = new HashMap<>();

    //классы для работы с регулярными выражениями
    private Pattern p = null;
    private Matcher m = null;

    public ArgsParser(String[] args) throws Exception {

        this.args = args;
        parseArguments();
    }

    private void parseArguments() throws Exception {
        int countArguments = 7;
        if (args.length < countArguments) {
            throw new IllegalArgumentException("Invalid startup options. Fix it.");
        }
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-m")
                    || args[i].equals("-f")
                    || args[i].equals("-r")) {
                arguments.put(args[i], "");
            } else if (args[i].equals("-d")
                    || args[i].equals("-n")
                    || args[i].equals("-o")) {
                arguments.put(args[i], args[i + 1]);
            }
        }
        if (!valid()) {
            throw new IllegalArgumentException("Invalid startup options");
        }
        directory = arguments.get("-d");
        File topDirectory = new File(directory);
        if (!topDirectory.exists()) {
            throw new Exception("Error: The specified source directory path does not exist.");
        }
        if (!topDirectory.isDirectory()) {
            throw new Exception("Error: The specified path is not a directory.");
        }
    }

    private boolean valid() {
        boolean result = true;
        List<String> argsList = Arrays.asList(args);
        if (!argsList.contains("-d")) {
            System.out.println("Args is not valid. File search directory not entered.");
            result = false;
        } else if (!argsList.contains("-n")) {
            System.out.println("Args is not valid. File search parameter not entered.");
            result = false;
        } else if (!argsList.contains("-o")) {
            System.out.println("Args is not valid. File name not entered for recording the result.");
            result = false;
        } else if (!argsList.get(4).equals("-m")) {
            if (!argsList.get(4).equals("-f")) {
                if (!argsList.get(4).equals("-r")) {
                    System.out.println("Args is not valid. Invalid search key.\r\n"
                            + "There must be one key equal to m or f or r.");
                    result = false;
                }
            }
        }
        return result;
    }

    public String getDirectory() {
        return arguments.get("-d");
    }

    public String getSearchFile() {
        return arguments.get("-n");
    }

    public String getOutput() {
        return arguments.get("-o");
    }

    public Predicate<String> flagCheck() {
        Predicate<String> filePredicate = null;
        if (arguments.containsKey("-m")) {
            filePredicate = str -> str.endsWith(getSearchFile())
                    || str.startsWith(getSearchFile())
                    || str.contains(getSearchFile())
                    || str.equalsIgnoreCase(getSearchFile());
        } else if (arguments.containsKey("-f")) {
            filePredicate = str -> str.equals(getSearchFile());
        } else if (arguments.containsKey("-r")) {
            filePredicate = str -> {
                String pattern = preparePattern(getSearchFile());
                p = Pattern.compile(pattern, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
                m = p.matcher(str);
                return m.matches();
            };
        }
        return filePredicate;
    }

    private String preparePattern(String pattern) {
        if (pattern.contains("*.")) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pattern.length(); i++) {
                char c = pattern.charAt(i);
                if (c == '*') {
                    sb.append(".+");
                } else if (c == '.') {
                    sb.append("\\.");
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
        return pattern;
    }
}
