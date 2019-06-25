package ru.job4j.basepatterns.creational.abstractfactory.banking;

import ru.job4j.basepatterns.creational.abstractfactory.Developer;
import ru.job4j.basepatterns.creational.abstractfactory.ProjectManager;
import ru.job4j.basepatterns.creational.abstractfactory.ProjectTeamFactory;
import ru.job4j.basepatterns.creational.abstractfactory.Tester;

/**
 * Создаем фабрику для создания нашей команды сразу:
 * 1 разработчик, 1 тестировщик, 1 проджект-менеджер:
 * JavaDeveloper, QATester, BankingPM
 */

public class BankihgTeamFactory implements ProjectTeamFactory {
    @Override
    public Developer getDeveloper() {
        return new JavaDeveloper();
    }

    @Override
    public Tester getTester() {
        return new QATester();
    }

    @Override
    public ProjectManager getProjectManager() {
        return new BankingPM();
    }
}
