package ru.job4j.basepatterns.behavioral.strategy.example1;
/**
 * это клиентский класс для проверки работы программы
 * применяя шаблон стратегия мы
 * в клиенском коде меняем активность разработчика
 * Этот класс создает внутри себя как разработчика, так и различные виды активности
 * и выполняет эти активности
 */
public class DeveloperRunner {
    public static void main(String[] args) {
        Developer developer = new Developer();//создаем разработчика

        developer.setActivity(new Sleeping()); //устанавливаем разработчику активность
        developer.executeActivity(); //просим разработчика выполнить свою активность

        developer.setActivity(new Training()); //устанавливаем разработчику активность
        developer.executeActivity(); //просим разработчика выполнить свою активность

        developer.setActivity(new Coding());
        developer.executeActivity();

        developer.setActivity(new Reading());
        developer.executeActivity();

        developer.setActivity(new Sleeping());//после рабочего дня наш разработчик идет свать
        developer.executeActivity();

    }
}
