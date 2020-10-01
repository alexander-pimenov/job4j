package ru.job4j.design.lsp.productdistribution;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс Shop отвечает за хранение продуктов в хранилище Shop.
 */
public class Shop implements Storage {
    private List<Food> foodStore;

    public Shop() {
        this.foodStore = new ArrayList<>();
    }

    /**
     * Метод добавляющий продукт в хранилище Shop
     * согласно выполнению условий по израсходыванию
     * срока годности.
     * Если срок годности израсходован более, чем
     * на 75%, то на продукт устанавливается скидка 25%.
     *
     * @param food продукт.
     * @return true or false.
     */
    @Override
    public boolean addFood(Food food) {
        boolean result = false;
        final int percent = DateUtil.calculateExpirationDateConsumption(food.getCreateDate(), food.getExpiryDate());
        if (percent > 25 && percent < 75) {
            result = foodStore.add(food);
        } else if (percent >= 75 && percent < 100) {
            food.setDiscount(25);
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
        return "Shop{"
                + "foodStore=" + foodStore
                + '}';
    }
}
