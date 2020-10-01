package ru.job4j.design.lsp.productdistribution;

import java.util.List;

public interface Storage {

    boolean addFood(Food food);

    List<Food> showFood();

    int getNumberOfProducts();
}
