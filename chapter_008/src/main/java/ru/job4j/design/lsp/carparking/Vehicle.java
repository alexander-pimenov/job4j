package ru.job4j.design.lsp.carparking;

/**
 * Интерфейс Vehicle описывает способности транспортного средства.
 */
public interface Vehicle {
    String model();

    String getCarLicensePlate();

    int vehicleSize();

    void setVehicleSize(int size);
}
