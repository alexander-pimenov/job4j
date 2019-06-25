package ru.job4j.basepatterns.behavioral.strategy.example1;

/**
 * Это класс самого разработчика
 * Содержит внутри себя поле Активность
 * такой класс еще называют StrategyClient
 * или Context
 */
public class Developer {
    Activity activity; //разработчик имеет поле активность

    /*сеттер для поля activity*/
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /*метод выполнения активности, execute - выполнять, реализовать*/
    public void executeActivity (){
        activity.justDoIt(); //мы у нашего поля activity вызываем метод justDoIt
    }
}

