package ru.job4j.design.lsp.carparking;

/**
 * Класс Truck описывает грузовой автомобиль.
 * Устанавливаем ему размер равный 2.
 */

public class Truck extends AbstractVehicle {
    public Truck(String model, String carLicensePlate) {
        super(model, carLicensePlate, 2);
    }

    public Truck(String model, String carLicensePlate, int size) {
        super(model, carLicensePlate, size);
    }

    @Override
    public String toString() {
        return "Truck{"
                + "model='" + model() + '\''
                + ", carLicensePlate='" + getCarLicensePlate() + '\''
                + '}';
    }
}
