package ru.job4j.design.lsp.productdistribution;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Food {
    /**
     * Название продукта.
     */
    private String name;
    /**
     * Дата изготовления продукта.
     */
    private LocalDate createDate;
    /**
     * Дата до которой продукт годен: "годен до".
     */
    private LocalDate expiryDate;
    /**
     * Цена продукта.
     */
    private double price;
    /**
     * Размер скидки на продукт в процентах.
     * Изначально присоздании продукта устанавливается в 0.
     */
    private int discount;

    /**
     * Конструктор объекта Food.
     * С последующим заданием параметров через сэттеры.
     */
    public Food() {
    }

    /**
     * Конструктор объекта Food.
     *
     * @param name       название продукта.
     * @param createDate дата изготовления продукта.
     *                   Вводится как LocalDate.parse("2020-09-03"), not null
     *                   или LocalDate.of(2020,9,3)
     * @param expiryDate дата до которой годен продукт.
     *                   Вводится как LocalDate.parse("2020-09-03"), not null
     *                   или LocalDate.of(2020,9,3), not null
     * @param price      цена продукта.
     */
    public Food(String name, LocalDate createDate, LocalDate expiryDate, double price) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
        this.discount = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        if (createDate.isBefore(expiryDate)) {
            this.createDate = createDate;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        if (expiryDate.isAfter(createDate)) {
            this.expiryDate = expiryDate;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double getPrice() {
        return price - (price / 100) * discount;
//        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + name + '\''
                + ", createDate=" + createDate
                + ", expiryDate=" + expiryDate
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }

    /**
     * Вычисляем количество дней между датой изготовления
     * и датой до которой продукт годен.
     *
     * @return количество дней годности.
     */
    public long getNumberOfDaysOfExpirationDate() {
        return DAYS.between(createDate, expiryDate);
    }
}