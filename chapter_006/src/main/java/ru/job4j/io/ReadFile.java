package ru.job4j.io;

import java.io.FileInputStream;

public class ReadFile {
    public static void main(String[] args) {
        //try (FileInputStream in = new FileInputStream("input.txt")) {
        try (FileInputStream in = new FileInputStream(ReadFile.class.getResource("/input.txt").getFile())) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            System.out.println(text);
            System.out.println("============");
            /*Получившийся текст разбиваем на строчки через метод String.split*/
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
