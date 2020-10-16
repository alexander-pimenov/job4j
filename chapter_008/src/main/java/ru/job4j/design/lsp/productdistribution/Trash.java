package ru.job4j.design.lsp.productdistribution;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс Trash отвечает за хранение продуктов в хранилище Trash (мусор).
 */
public class Trash implements Storage {
    private List<Food> foodStore;

    public Trash() {
        this.foodStore = new ArrayList<>();
    }

    /**
     * Метод добавляющий продукт в хранилище Trash
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
        if (percent >= 100) {
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

    /**
     * Метод очищающий список продуктов в хранилище.
     */
    @Override
    public void resetFoodList() {
        foodStore.clear();
    }

    @Override
    public String toString() {
        return "Trash{"
                + "foodStore=" + foodStore
                + '}';
    }
}
