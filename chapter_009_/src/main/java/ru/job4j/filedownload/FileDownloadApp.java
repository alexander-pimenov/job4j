package ru.job4j.filedownload;

/*
 * Аргументы при запуске из консоли:
 * fileURL = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml"
 * localFileName = "pom_tmp_10.xml"
 * speed = 200 (скорость скачивания байт/сек)
 */

/**
 * java -jar FileDownloadTask.jar url 200
 * FileDownloadTask (url-ссылка) (200-скорость в килобайтах в секунду)
 * <p>
 * Для того, чтобы ограничить скорость скачивания,
 * нужно проверять сколько байтов загрузиться за 1 секунду.
 * Если объем больше, то нужно выставлять паузу.
 * <p>
 * Пауза должна вычисляться, а не быть константой.
 */
public class FileDownloadApp {
    public static void main(String[] args) throws InterruptedException {
//        String fileURL = args[0];
//        String localFileName = args[1];
//        int speed = Integer.parseInt(args[2]);

        String fileURL = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        String localFileName = "pom_tmp_10.xml";
        int speed = 200; //скорость скачивания байт/сек

        Thread fileDownload = new Thread(new FileDownload(fileURL, localFileName, speed));
        fileDownload.start();
        fileDownload.join();

        //C:\projects\job4j\chapter_009_\pom.xml
    }
}

