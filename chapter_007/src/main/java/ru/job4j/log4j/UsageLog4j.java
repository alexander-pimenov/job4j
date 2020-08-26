package ru.job4j.log4j;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/*Этот класс обращается к файлу log4j.properties для правильного вывода информации в консоль,
 * т.е. это файл настройки вывода лога.*/
public class UsageLog4j {
    private static final Logger LOG = LogManager.getLogger(UsageLog4j.class.getName());

    /*В зависимости какой уровень в файле log4j.properties будет указан
     * log4j.rootLogger=INFO, console
     * те сообщения и попадут в лог.
     * Например, устанавливая rootLogger=INFO мы в логе
     * увидим собщения только INFO, WARN, ERROR уровней.*/
    public static void main(String[] args) {
        LOG.trace("trace massage");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
}
