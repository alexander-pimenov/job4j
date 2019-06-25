package ru.job4j.basepatterns.behavioral.state;

/**
 * Клиентский класс
 */
public class DeveloperDay {
    public static void main(String[] args) {
        Activity activity = new Sleeping(); //задаем активность (спать разработчику укажем в сеттере)
        Developer developer = new Developer(); //создаем разработчика

        developer.setActivity(activity);
    for (int i=0; i<10; i++){
        developer.justDoIt();//говорим разработчику - выполняй свою активность
        developer.changeActivity();//меняем активность у разработчика
    }
    }

}