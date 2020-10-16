package ru.job4j.design.lsp.productdistribution;

import java.util.LinkedList;
import java.util.List;

/**
 * Класс обработчик перераспределения продуктов в место использования.
 * Класс перераспределяет еду по хранилищам в зависимости от условий
 * прописанных для каждого из хранилищ индивидуально.
 */
public class ControllQuality {

    private List<Storage> storageList;


    public ControllQuality(List<Storage> storageList) {
        this.storageList = storageList;
    }

    /**
     * Метод выполняющий помещение одного продукта
     * в соответствующее хранилище.
     *
     * @param food продукт.
     * @return boolean result.
     */
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

    /**
     * Метод выполняющий помещение
     * списка продукта в
     * соответствующее хранилище.
     *
     * @param foodList список продуктов.
     */
    public void executeMovementFoodList(List<Food> foodList) {
        for (Food food : foodList) {
            executeMovementFood(food);
        }
    }

    /**
     * Метод извлекает все продукты из хранилищ и перераспределяет их заново.
     */
    public void resort() {
        List<Food> allProducts = new LinkedList<>();
        for (Storage storage : storageList) {
            allProducts.addAll(storage.showFood());
            storage.resetFoodList();
        }
        executeMovementFoodList(allProducts);
    }
}
