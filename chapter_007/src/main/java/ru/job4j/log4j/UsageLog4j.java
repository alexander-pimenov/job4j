package ru.job4j.log4j;

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
