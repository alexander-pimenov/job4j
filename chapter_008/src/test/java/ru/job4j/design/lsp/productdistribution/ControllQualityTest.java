package ru.job4j.design.lsp.productdistribution;

import org.junit.Test;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ControllQualityTest {

    @Test
    public void whenAddToShopOneProduct() {
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        ControllQuality controllQuality = new ControllQuality(List.of(warehouse, shop, trash));
        Food butter = new Food("butter", LocalDate.now().minusDays(5), LocalDate.now().plusDays(1), 100);
        controllQuality.executeMovementFood(butter);
        assertThat(warehouse.getNumberOfProducts(), is(0));
        assertThat(shop.getNumberOfProducts(), is(1));
        assertThat(trash.getNumberOfProducts(), is(0));
        assertThat(shop.showFood().get(0).getName(), is("butter"));
    }

    @Test
    public void whenAddToShopManyProducts() {
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        ControllQuality controllQuality = new ControllQuality(List.of(warehouse, shop, trash));
        Food butter = new Food("butter", LocalDate.now().minusDays(5), LocalDate.now().plusDays(1), 100); //израсходованно 83%
        Food milk = new Food("milk", LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), 100); //израсходованно 50%
        Food meet = new Food("meet", LocalDate.now().minusDays(20), LocalDate.now().plusDays(5), 100); //израсходованно  80%
        controllQuality.executeMovementFoodList(List.of(butter, milk, meet));
        assertThat(shop.getNumberOfProducts(), is(3));
    }

    @Test
    public void whenAddToShopProductWithDiscount() {
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        ControllQuality controllQuality = new ControllQuality(List.of(warehouse, shop, trash));
        Food butter = new Food("butter", LocalDate.now().minusDays(5), LocalDate.now().plusDays(1), 100); //израсходованно 83%
        controllQuality.executeMovementFoodList(List.of(butter));
        assertThat(shop.getNumberOfProducts(), is(1));
        assertThat(shop.showFood().get(0).getDiscount(), is(25));
    }

    @Test
    public void whenAddToWarehouseProduct() {
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        ControllQuality controllQuality = new ControllQuality(List.of(warehouse, shop, trash));
        Food butter = new Food("butter", LocalDate.now().minusDays(1), LocalDate.now().plusDays(5), 100);
        controllQuality.executeMovementFood(butter);
        assertThat(shop.getNumberOfProducts(), is(0));
        assertThat(warehouse.getNumberOfProducts(), is(1));
        assertThat(trash.getNumberOfProducts(), is(0));
        assertThat(warehouse.showFood().get(0).getName(), is("butter"));
    }

    @Test
    public void whenAddToTrash() {
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        ControllQuality controllQuality = new ControllQuality(List.of(warehouse, shop, trash));
        Food butter = new Food("butter", LocalDate.now().minusDays(5), LocalDate.now().plusDays(-1), 100);
        controllQuality.executeMovementFood(butter);
        assertThat(shop.getNumberOfProducts(), is(0));
        assertThat(warehouse.getNumberOfProducts(), is(0));
        assertThat(trash.getNumberOfProducts(), is(1));
        assertThat(trash.showFood().get(0).getName(), is("butter"));
    }

    @Test
    public void whenAddToStorageManyProductsAndGetDateRatio() {
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        ControllQuality controllQuality = new ControllQuality(List.of(warehouse, shop, trash));
        Food butter = new Food("butter", LocalDate.now().minusDays(5), LocalDate.now().plusDays(1), 100);
        Food milk = new Food("milk", LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), 70);
        Food meet = new Food("meet", LocalDate.now().minusDays(5), LocalDate.now().plusDays(25), 200);
        Food bread = new Food("bread", LocalDate.now().minusDays(4), LocalDate.now().plusDays(-1), 25);
        System.out.println("For butter: " + DateUtil.calculateDateRatio(butter.getCreateDate(), butter.getExpiryDate()));
        System.out.println("For milk: " + DateUtil.calculateDateRatio(milk.getCreateDate(), milk.getExpiryDate()));
        System.out.println("For meet: " + DateUtil.calculateDateRatio(meet.getCreateDate(), meet.getExpiryDate()));
        System.out.println("For bread: " + DateUtil.calculateDateRatio(bread.getCreateDate(), bread.getExpiryDate()));
        controllQuality.executeMovementFoodList(List.of(butter, milk, meet, bread));
        assertThat(shop.getNumberOfProducts(), is(2));
        assertThat(warehouse.getNumberOfProducts(), is(1));
        assertThat(trash.getNumberOfProducts(), is(1));
    }

    @Test
    public void whenAddToStorageManyProductsAndResortThem() {
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        ControllQuality controllQuality = new ControllQuality(List.of(warehouse, shop, trash));
        Food butter = new Food("butter", LocalDate.now().minusDays(5), LocalDate.now().plusDays(1), 100);
        Food milk = new Food("milk", LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), 70);
        Food meet = new Food("meet", LocalDate.now().minusDays(5), LocalDate.now().plusDays(25), 200);
        Food bread = new Food("bread", LocalDate.now().minusDays(4), LocalDate.now().plusDays(-1), 25);
        controllQuality.executeMovementFoodList(List.of(butter, milk, meet, bread));

        controllQuality.resort();

        System.out.println(warehouse.showFood());
        System.out.println(shop.showFood());
        System.out.println(trash.showFood());
    }
}