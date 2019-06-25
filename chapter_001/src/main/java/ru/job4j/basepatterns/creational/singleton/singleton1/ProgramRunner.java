package ru.job4j.basepatterns.creational.singleton.singleton1;

/**
 * Класс Клиент. Этот класс будет записывать данные
 * в наш лог.
 */
public class ProgramRunner {
    public static void main(String[] args) {

/*
        //закоментированный код сделан для примера, чтобы показать, что
        //независимо от количества вызовов мы имеем один и тот же файл,
        //будет видно, что хеш-код нашего файла будет одинаковым, это один и тотже экземпляр
        System.out.println(ProgramLogger.getProgramLogger().toString()); //ProgramLogger@1b6d3586
        System.out.println(ProgramLogger.getProgramLogger().toString()); //ProgramLogger@1b6d3586
        System.out.println(ProgramLogger.getProgramLogger().toString()); //ProgramLogger@1b6d3586
        System.out.println(ProgramLogger.getProgramLogger().toString()); //ProgramLogger@1b6d3586
*/

        //выполним запись в наш логфайл
        ProgramLogger.getProgramLogger().addLogInfo("First log...");
        ProgramLogger.getProgramLogger().addLogInfo("Second log...");
        ProgramLogger.getProgramLogger().addLogInfo("Third log...");

        //отобразим содержимое нашего логфайла
        ProgramLogger.getProgramLogger().showLogFile();
    }

    /*на выходе будет это:
    * This is log file.
    *
    * First log...
    * Second log...
    * Third log...
    */
}
