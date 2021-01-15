package ru.job4j.filedownload;

import java.io.File;
import java.net.URISyntaxException;

/*
 * Аргументы:
 * fileURL = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml"
 * localFileName = "pom_tmp_10.xml"
 * speed = 200 (скорость скачивания байт/сек)
 */
public class FileDownloadApp {
    public static void main(String[] args) throws InterruptedException {
        String fileURL = args[0];
        String localFileName = args[1];
        int speed = Integer.parseInt(args[2]);

        Thread fileDownload = new Thread(new FileDownload(fileURL, localFileName, speed));
        fileDownload.start();
        fileDownload.join();

        //C:\projects\job4j\chapter_009_\pom.xml
    }
}

