package ru.job4j.basepatterns.creational.abstractfactory;

import ru.job4j.basepatterns.creational.abstractfactory.banking.BankihgTeamFactory;

/**
 * Получаем заказ на систему интернет банкинга для Супер Банка
 *
 *
 */
public class SuperBankSystem {
    public static void main(String[] args) {
        //для начала создаем фабрику
        ProjectTeamFactory projectTeamFactory = new BankihgTeamFactory();
        //создаем команду, вызывая у нашей фабрики BankihgTeamFactory геттеры
        //получаем разработчика
        Developer developer = projectTeamFactory.getDeveloper();
        //получаем тестировщика
        Tester tester = projectTeamFactory.getTester();
        //получаем мнеджера
        ProjectManager projectManager = projectTeamFactory.getProjectManager();

        System.out.println("Creating bank system...");
        //вызываем у разработчика его метод
        developer.writeCode();
        //у тестировщика его метод
        tester.testCode();
        //у менеджера его метод
        projectManager.manageProject();
    }
}
