package ru.job4j.downloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/*Этот класс обращается к файлу log4j.properties для правильного вывода информации в консоль,
 * т.е. это файл настройки вывода лога.*/
public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
    //Для обращения к несуществующему файлу
    private static final String FILENAME = "/file/does/not/exist";

    /*В зависимости какой уровень в файле log4j.properties будет указан
     * log4j.rootLogger=INFO, console
     * те сообщения и попадут в лог.
     * Например, устанавливая rootLogger=INFO мы в логе
     * увидим собщения только INFO, WARN, ERROR уровней.*/
    public static void main(String[] args) {
        LOG.trace("trace massage");
        LOG.debug("debug message. Message for debug level.");
        LOG.info("info message. Application works. Just a log message.");
        LOG.warn("warn message. Something to warn");
        LOG.error("error message. Something failed.");
        try {
            LOG.trace("trace message. Зашли в блок try-catch");
            System.out.println("\u0000");
            System.out.println("\u007F");
            System.out.println(0x00);
            System.out.println(0x79);
            String text = "fuck the millenium!";
            CharSequence charSequence = text.subSequence(1, 6);
            String substring = text.substring(2, 8);
            System.out.println(charSequence);
            System.out.println(substring);
            String line = "aaabccdddc";
            System.out.println(line.chars().distinct().count());
            //
            String emojiString = "\uD83D\uDC7B";

//На один emojiString приходится 2 чара (т.к. не влезает в 16 бит)
            System.out.println(emojiString);

            System.out.println(emojiString.codePoints().count());
//1

            System.out.println(emojiString.chars().count());
//2

            Files.readAllBytes(Paths.get(FILENAME));
        } catch (IOException e) {
            //Используя org.slf4j.Logger мы можем использовать строку с параметром {}:
            LOG.error("Error message from catch-block. Failed to read file {}.", FILENAME, e);
//            LOG.error(e.getMessage(), e); //удобно искать ошибку в логе

            //Используя org.apache.log4j.Logger мы не сможем использовать {}
//            LOG.error("Error message from catch-block. Failed to read file.", e);
//            e.printStackTrace();
        }
    }
}