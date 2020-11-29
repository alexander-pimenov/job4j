package ru.job4j.fileDownload;

public class FileDownloadApp {
    public static void main(String[] args) throws InterruptedException {
//        String fileURL = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
//        int speed = 200;
//        String localFileName = "pom_tmp_10.xml";

        String fileURL = args[0];
        String localFileName = args[1];
        int speed = Integer.parseInt(args[2]);

        Thread fileDownload = new Thread(new FileDownload(fileURL, localFileName, speed));
        fileDownload.start();
        fileDownload.join();
    }
}
