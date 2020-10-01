package ru.job4j.design.lsp.carparking;

/**
 * Класс Car описывает легковой автомобиль.
 * Устанавливаем ему размер равный 1.
 */
public class Car extends AbstractVehicle {
    public Car(String model, String carLicensePlate) {
        super(model, carLicensePlate, 1);
    }

    public Car(String model, String carLicensePlate, int size) {
        super(model, carLicensePlate, size);
    }

    @Override
    public String toString() {
        return "Car{"
                + "model='" + model() + '\''
                + ", carLicensePlate='" + getCarLicensePlate() + '\''
                + '}';
    }
}
