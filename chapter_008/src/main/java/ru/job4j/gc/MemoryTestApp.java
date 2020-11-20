package ru.job4j.gc;

import java.io.*;
import java.util.*;

/**
 * Программа для визуального тестирования памяти виртуальной машины
 * с использованием утилиты jvisualVM.
 * Пример взят из источника "Garbage Collection наглядно" https://habr.com/ru/post/112676/
 * <p>
 * Настройки VM напишем такие:
 * -Xms512m -Xmx512m -XX:NewRatio=3 -Xlog:gc* -Xlog:gc:C:\projects\job4j\chapter_008\gcTest.txt
 * или такие:
 * -Xms512m -Xmx512m -XX:NewRatio=3 -Xlog:gc:C:\projects\job4j\chapter_008\gcTest.txt
 */
public class MemoryTestApp {
    private static List objects = new ArrayList();
    private static boolean cont = true;
    private static String input;
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {

        System.out.println("Welcome to Memory Tool!");

        while (cont) {
            System.out.println(
                    "\n\nI have " + objects.size() + " objects in use, about "
                            + (objects.size() * 10) + " MB."
                            + "\nWhat would you like me to do?\n"
                            + "1. Create some objects\n"
                            + "2. Remove some objects\n"
                            + "0. Quit");
            input = in.readLine();
            if ((input != null) && (input.length() >= 1)) {
                if (input.startsWith("0")) {
                    cont = false;
                }
                if (input.startsWith("1")) {
                    createObjects();
                }
                if (input.startsWith("2")) {
                    removeObjects();
                }
            } else {
                System.out.println("Enter a valid number range.");
            }
        }

        System.out.println("Bye!");
    }

    /**
     * Метод создающий объект.
     */
    private static void createObjects() {
        System.out.println("Creating objects...");
        for (int i = 0; i < 2; i++) {
            objects.add(new byte[10 * 1024 * 1024]); //создаем объект размером 1 МВ
        }
    }

    /**
     * Метод удаляющий объект.
     */
    private static void removeObjects() {
        System.out.println("Removing objects...");
        int start = objects.size() - 1;
        int end = start - 2;
        for (int i = start; ((i >= 0) && (i > end)); i--) {
            objects.remove(i);
        }
    }
}
