package ru.job4j.design.lsp.productdistribution;

import java.util.List;

public interface Storage {

    /**
     * Метод добавляющий продукт в хранилище,
     * согласно выполнению условий по израсходыванию
     * срока годности.
     *
     * @param food продукт.
     * @return true or false.
     */
    boolean addFood(Food food);

    /**
     * Метод показывающий все продукты хранилище
     * одним списоком.
     *
     * @return список продуктов.
     */
    List<Food> showFood();

    /**
     * Метод возвращающий количество продуктов в хранилище.
     *
     * @return число продуктов.
     */
    int getNumberOfProducts();

    /**
     * Метод очищающий список продуктов в хранилище.
     */
    void resetFoodList();

}
