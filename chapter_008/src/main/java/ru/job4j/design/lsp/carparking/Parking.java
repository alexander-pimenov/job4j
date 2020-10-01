package ru.job4j.design.lsp.carparking;

import java.util.List;

/**
 * Интерфейс Parking описывает способности сервиса учета парковки.
 */
public interface Parking {
    boolean addVehicle(Vehicle vehicle);

    boolean addVehicleList(List<Vehicle> vehicleList);

    Vehicle removeVehicle(String carLicensePlate);

    List<Vehicle> getVehicles();

    int getNumberOfCars();

}
