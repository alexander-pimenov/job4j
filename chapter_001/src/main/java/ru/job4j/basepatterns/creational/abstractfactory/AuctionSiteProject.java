package ru.job4j.basepatterns.creational.abstractfactory;

import ru.job4j.basepatterns.creational.abstractfactory.website.WebsiteTeamFactory;

/**
 * Пример: получаем заказ на сайт аукцион
 *
 */
public class AuctionSiteProject {
    public static void main(String[] args) {
        ProjectTeamFactory projectTeamFactory = new WebsiteTeamFactory();
        Developer developer = projectTeamFactory.getDeveloper();
        Tester tester = projectTeamFactory.getTester();
        ProjectManager projectManager = projectTeamFactory.getProjectManager();

        System.out.println("Creating auction website...");
        developer.writeCode();
        tester.testCode();
        projectManager.manageProject();
    }
}
