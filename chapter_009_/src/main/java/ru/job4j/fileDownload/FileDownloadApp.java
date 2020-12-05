package ru.job4j.fileDownload;

/*
 * Аргументы:
 * fileURL = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml"
 * speed = 200 (скорость скачивания байт/сек)
 * localFileName = "pom_tmp_10.xml"
 */
public class FileDownloadApp {
    public static void main(String[] args) throws InterruptedException {
        String fileURL = args[0];
        String localFileName = args[1];
        int speed = Integer.parseInt(args[2]);

        Thread fileDownload = new Thread(new FileDownload(fileURL, localFileName, speed));
        fileDownload.start();
        fileDownload.join();
    }
}
