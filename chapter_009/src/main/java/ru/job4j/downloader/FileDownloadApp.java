package ru.job4j.downloader;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;

public class FileDownloadApp {
    public static void main(String[] args) {
        String file = args[0];
        //String file = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        int size = Integer.parseInt(args[1]);
        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                if (bytesRead > size) {
                    int secondsToPause = ((bytesRead / size) + 1) * 1000;
                    System.out.println(bytesRead + " [bytes]");
//                    System.out.println("-= " + secondsToPause + " =-");
                    Thread.sleep(secondsToPause);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}