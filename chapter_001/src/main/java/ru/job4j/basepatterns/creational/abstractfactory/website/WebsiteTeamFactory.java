package ru.job4j.basepatterns.creational.abstractfactory.website;

import ru.job4j.basepatterns.creational.abstractfactory.Developer;
import ru.job4j.basepatterns.creational.abstractfactory.ProjectManager;
import ru.job4j.basepatterns.creational.abstractfactory.ProjectTeamFactory;
import ru.job4j.basepatterns.creational.abstractfactory.Tester;

public class WebsiteTeamFactory implements ProjectTeamFactory {
    @Override
    public Developer getDeveloper() {
        return new PhpDeveloper();
    }

    @Override
    public Tester getTester() {
        return new ManualTester();
    }

    @Override
    public ProjectManager getProjectManager() {
        return new WebsitePM();
    }
}
