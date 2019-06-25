package ru.job4j.basepatterns.creational.abstractfactory.website;

import ru.job4j.basepatterns.creational.abstractfactory.ProjectManager;

public class WebsitePM implements ProjectManager {
    @Override
    public void manageProject() {
        System.out.println("Website PM manages website project...");
    }
}
