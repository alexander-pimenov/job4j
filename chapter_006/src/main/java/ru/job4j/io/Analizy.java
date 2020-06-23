package ru.job4j.io;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Analizy {

    /*метод вычисляющий интервалы, когда сервер был не доступен*/
    public void unavailable(String source, String target) {
        List<String> listOfInterval = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String currentLine;
            /*вычитывание данных из лог файла*/
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.startsWith("500") || currentLine.startsWith("400")) {
                    StringBuilder interval =
                            new StringBuilder(currentLine.split(" ")[1]).append(";");
                    while ((currentLine = reader.readLine()) != null) {
                        if (currentLine.startsWith("200") || currentLine.startsWith("300")) {
                            interval.append(currentLine.split(" ")[1]).append("\n");
                            listOfInterval.add(interval.toString());
                            break;
                        }
                    }
                }
            }
            /*запись данных в файл*/
            saveIntervals(listOfInterval, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveIntervals(List<String> intervals, String target) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            for (String line : intervals) {
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Analizy analizy = new Analizy();

        analizy.unavailable("source.txt", "target.txt");
    }
}
