package ru.job4j.downloader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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
public class FileDownloadTask {
    public static void main(String[] args) throws Exception {
//        String file = args[0];
//        int size = Integer.parseInt(args[1]);
        String file = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
//        String file = "https://sefon.pro/api/mp3_download/direct/122437/3vUCAGijYRtmFxAN7pqhmox_c1CnSWwFJeOKWsBo_-o0Qu7eTL-hoJLvBWXrD6jCPKpZdlK--PmxvrKXNE_okJOQklJqd_y0uLsNfxt2qRLmpS6mXhQ7dmoM4Og8l9ZtMs3hFG3pf8y9MV3N4_igb_064C3oXh6Q7wUuvw/";
        try (InputStream in = new CustomInputStream(new URL(file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
//             FileOutputStream fileOutputStream = new FileOutputStream("ramm.mp3")) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                System.out.println(bytesRead + " [bytes]");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class CustomInputStream extends InputStream {
        private static final int MAX_SPEED = 8 * 1024;
        private static final long ONE_SECOND = 1000;
        private static long downloadedWithinOneSecond = 0L;
        private static long lastTime = System.currentTimeMillis();
        private InputStream inputStream;

        public CustomInputStream(InputStream inputStream) {
            this.inputStream = inputStream;
            lastTime = System.currentTimeMillis();
        }

        /**
         * Метод чтения данных с использованием паузы,
         * чтобы регулировать скорость загрузки.
         *
         * @return количество считанных байтов
         * @throws IOException выбрасывается исключение
         */
        @Override
        public int read() throws IOException {
            long currentTime = System.currentTimeMillis();
            if (
                    downloadedWithinOneSecond >= MAX_SPEED
                            && ((currentTime - lastTime) < ONE_SECOND)
            ) {
                try {
                    Thread.sleep(ONE_SECOND - (currentTime - lastTime));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                downloadedWithinOneSecond = 0;
                lastTime = System.currentTimeMillis();
            }
            int res = inputStream.read();
            if (res >= 0) {
                downloadedWithinOneSecond++;
            }
            return res;
        }

        @Override
        public int available() throws IOException {
            return inputStream.available();
        }

        @Override
        public void close() throws IOException {
            inputStream.close();
        }
    }
}
