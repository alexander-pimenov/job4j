package ru.job4j.fileDownload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.net.URL;

public class FileDownloadNIO implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(FileDownloadNIO.class.getName());

    private final String fileURL;
    private final String localFileName;

    public FileDownloadNIO(String fileURL, String localFileName) {
        this.fileURL = fileURL;
        this.localFileName = localFileName;
    }

    @Override
    public void run() {
        LOG.info("Вошли в метод run().");
        long start = System.currentTimeMillis();
        int available = 0;
        URL url = null;
        try {
            url = new URL(fileURL);
        } catch (MalformedURLException e) {
            LOG.error(e.getMessage(), e);
        }

        try (ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(localFileName);
             FileChannel fileChannel = fileOutputStream.getChannel()) {

            available = url.openStream().readAllBytes().length;
            fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            fileOutputStream.close();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        long passedTime = System.currentTimeMillis() - start;
        LOG.info("Файл {} загружен", url);
        LOG.info("Размер {} файла {} байт", url, available);
        LOG.info("Прошло {} милли секунд (NIO)", passedTime);
        LOG.info("Метод завершен.");
    }
}
