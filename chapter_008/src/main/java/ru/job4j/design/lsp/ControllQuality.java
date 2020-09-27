package ru.job4j.design.lsp;

import java.util.List;

public class ControllQuality {


//    private Storage storage;

    private List<Storage> storageList;


    public ControllQuality(List<Storage> storageList) {
        this.storageList = storageList;
    }

    public boolean executeMovementFood(Food food) {
        boolean added = false;
        for (Storage storage : storageList) {
            if (storage.addFood(food)) {
                added = true;
                break;
            }
        }
        return added;
    }

    public void executeMovementFoodList(List<Food> foodList) {
        for (Food food : foodList) {
            executeMovementFood(food);
        }
    }
}
