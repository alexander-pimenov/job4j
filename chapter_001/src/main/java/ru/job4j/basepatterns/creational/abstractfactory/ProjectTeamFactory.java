package ru.job4j.basepatterns.creational.abstractfactory;
/**
 *  ProjectTeamFactory - это некая сущность создающая команду
 *  это интерфейс возвращает нам одного разработчика Developer,
 *  одного тестировщика Tester и одного ProjectManager
 *  Количеством можем варьировать конечно
 *
 *
 */
public interface ProjectTeamFactory {
    Developer getDeveloper();
    Tester getTester();
    ProjectManager getProjectManager();

}
