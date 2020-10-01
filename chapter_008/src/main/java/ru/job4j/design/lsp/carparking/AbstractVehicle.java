package ru.job4j.design.lsp.carparking;

import java.util.Objects;

/**
 * Абстрактный класс, описывающий транспортное средство.
 */
public abstract class AbstractVehicle implements Vehicle {
    private String model;
    private String carLicensePlate;
    private int size;

    public AbstractVehicle(String model, String carLicensePlate, int size) {
        this.model = model;
        this.carLicensePlate = carLicensePlate;
        this.size = size;
    }

    @Override
    public String model() {
        return this.model;
    }

    @Override
    public String getCarLicensePlate() {
        return this.carLicensePlate;
    }

    @Override
    public int vehicleSize() {
        return this.size;
    }

    @Override
    public void setVehicleSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractVehicle that = (AbstractVehicle) o;
        return size == that.size
                && Objects.equals(model, that.model)
                && Objects.equals(carLicensePlate, that.carLicensePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, carLicensePlate, size);
    }
}
