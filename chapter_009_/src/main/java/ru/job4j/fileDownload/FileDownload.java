package ru.job4j.fileDownload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FileDownload implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(FileDownload.class.getName());

    private final String url;
    private final String localFileName;
    private final int speed;

    FileDownload(String url, String localFileName, int speed) {
        this.url = url;
        this.localFileName = localFileName;
        this.speed = speed;
    }

    @Override
    public void run() {
//        LOG.info("Вошли в метод.");
        long start = System.currentTimeMillis();
        int sizeFile = 0;
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(localFileName)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                sizeFile = sizeFile + bytesRead;
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                if (bytesRead > speed) {
                    int secondsToPause = ((bytesRead / speed) + 1) * 1000;
                    System.out.println(bytesRead + " [bytes]");
//                    System.out.println("-= " + secondsToPause + " =-");
                    Thread.sleep(secondsToPause);
                }
            }
        } catch (FileNotFoundException e) {
            LOG.error("Failed to read file {}", url, e);
        } catch (IOException | InterruptedException e) {
            LOG.error(e.getMessage(), e);
        }
        long passedTime = System.currentTimeMillis() - start;
//        LOG.info("Файл {} загружен", url);
//        LOG.info("Размер {} файла {} байт", url, sizeFile);
//        LOG.info("Прошло {} милли секунд", passedTime);
//        LOG.info("Метод завершен.");
    }
}
