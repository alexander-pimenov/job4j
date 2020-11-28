package ru.job4j.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FileDownloadApp {

    private static final Logger LOG = LoggerFactory.getLogger(FileDownloadApp.class.getName());

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        LOG.trace("Приложение стартовало с позиции TRACE.");
        LOG.debug("Приложение прошло позицию DEBUG.");
        LOG.info("Приложение стартовало.");
//        String file = args[0];
//        int size = Integer.parseInt(args[1]);
        int size = 200;
        int fileSize = 0;
        String file = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileSize = fileSize + bytesRead;
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                if (bytesRead > size) {
                    int secondsToPause = ((bytesRead / size) + 1) * 1000;
                    System.out.println(bytesRead + " [bytes]");
//                    System.out.println("-= " + secondsToPause + " =-");
                    Thread.sleep(secondsToPause);
                }
            }
        } catch (InterruptedException e) {
            LOG.error("Thread {} was interrupted.", Thread.currentThread().getName(), e);
//            e.printStackTrace();
        } catch (IOException e) {
            LOG.error("Failed to read file {}.", file, e);
//            e.printStackTrace();
        }
        long passedTime = System.currentTimeMillis() - start;
        LOG.info("Потрачено {} милли секунд.", passedTime);
        LOG.info("Скачан файл размером {} bytes", fileSize);
        LOG.info("Приложение закончило работу.");
    }
}
