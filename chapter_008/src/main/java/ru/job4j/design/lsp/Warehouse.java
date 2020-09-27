package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс Warehouse отвечает за хранение продуктов в хранилище Warehouse.
 */
public class Warehouse implements Storage {
    private List<Food> foodStore;

    public Warehouse() {
        this.foodStore = new ArrayList<>();
    }

    /**
     * Метод добавляющий продукт в хранилище Warehouse
     * согласно выполнению условий по израсходыванию
     * срока годности.
     *
     * @param food продукт.
     * @return true or false.
     */
    @Override
    public boolean addFood(Food food) {
        boolean result = false;
        final int percent = DateUtil.calculateExpirationDateConsumption(food.getCreateDate(), food.getExpiryDate());
        if (percent <= 25) {
            result = foodStore.add(food);
        }
        return result;
    }

    @Override
    public List<Food> showFood() {
        return foodStore;
    }

    /**
     * Метод возвращающий количество продуктов в хранилище.
     *
     * @return число продуктов.
     */
    @Override
    public int getNumberOfProducts() {
        return foodStore.size();
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "foodStore=" + foodStore +
                '}';
    }
}
