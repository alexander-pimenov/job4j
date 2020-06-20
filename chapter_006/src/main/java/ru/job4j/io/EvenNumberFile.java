package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream(ReadFile.class.getResource("/even.txt").getFile())) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            //System.out.println(text);

            /*Получившийся текст разбиваем на строчки через метод String.split*/
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                int i = Integer.parseInt(line);
                if (i % 2 == 0) {
                    System.out.println(String.format("%d - четное число", i));
                } else {
                    System.out.println(String.format("%d - нечетное число", i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
