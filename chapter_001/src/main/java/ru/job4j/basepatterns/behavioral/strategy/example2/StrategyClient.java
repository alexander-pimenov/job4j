package ru.job4j.basepatterns.behavioral.strategy.example2;

/**
 * Context. Клиент
 * Содержит внутри себя поле Sorting strategy
 */
public class StrategyClient {
    Sorting strategy; //у него, у клиента, есть ссылка на сортировку Sorting

    /*сеттер для поля Sorting strategy*/
    public void setSrategy(Sorting strategy) { //сеттер
        this.strategy = strategy;
    }

    /**
     * executeStrategy вызывает метод sort нашего алгоритма нашей стратегии.
     * передаем в него массив arr,который хотим отсортировать
     * мы у нашего поля Sorting strategy вызываем метод sort
     */
    public void executeStrategy(int[] arr) {
        strategy.sort(arr);
    }
}
