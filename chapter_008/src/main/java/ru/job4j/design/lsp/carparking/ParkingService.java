package ru.job4j.design.lsp.carparking;

import java.util.ArrayList;
import java.util.List;

/**
 * ParkingService - сервис для учета парковки машин.
 * Существует парковка для грузовых и легковых машин.
 * Количество парковочных мест для каждого типа машин задается на этапе создания парковки.
 * Если не будет задано, то установиться по умолчанию 10 мест для легковых машин и
 * 5 для грузовых.
 * Легковая машина может занять только место, предназначенное для легковой машины.
 * Грузовая машина может разместиться на месте, предназначенном для грузовых машин,
 * либо на N парковочных мест для легковых машин, стоящих рядом.
 */
public class ParkingService implements Parking {
    private static final int DEFAULT_CAR_PLACES = 10;
    private static final int DEFAULT_TRUCK_PLACES = 5;
    private static final int CAR_SIZE = 1;

    /**
     * Количество парковочных мест для легковых авто.
     */
    private int carPlaces;
    /**
     * Количество парковочных мест для грузовых авто.
     */
    private int truckPlaces;
    /**
     * Общее количество машин на стоянке.
     */
    private int numberOfCars;

    private List<Vehicle> cars = new ArrayList<>();
    private List<Vehicle> trucks = new ArrayList<>();

    public ParkingService() {
        this.carPlaces = DEFAULT_CAR_PLACES;
        this.truckPlaces = DEFAULT_TRUCK_PLACES;
    }

    public ParkingService(int carPlaces, int truckPlaces) {
        this.carPlaces = carPlaces;
        this.truckPlaces = truckPlaces;
    }

    /**
     * Метод добавления транспортного средства на парковку.
     *
     * @param vehicle транспортное средство.
     * @return true или false в зависимости от результата.
     */
    @Override
    public boolean addVehicle(Vehicle vehicle) {
        boolean result = false;
        if (vehicle.vehicleSize() == 1) {
            if (carPlaces > 0) {
                cars.add(vehicle);
                result = true;
                carPlaces--;
                numberOfCars++;
            } else {
                throw new IllegalArgumentException("No free parking spaces for a passenger car.");
            }
        } else {
            if (truckPlaces > 0) {
                trucks.add(vehicle);
                result = true;
                truckPlaces--;
                numberOfCars++;
            } else if (truckPlaces == 0 && carPlaces >= vehicle.vehicleSize()) {
                trucks.add(vehicle);
                result = true;
                carPlaces -= vehicle.vehicleSize();
                numberOfCars++;
                System.out.println("Заняли грузовиком место на парковке для легкового авто!");
                if (carPlaces == 0) {
                    System.out.println("Мест на парковке для легкового авто не осталось!");
                }
            } else {
                throw new IllegalArgumentException("No free parking spaces for truck.");
            }
        }
        return result;
    }

    /**
     * Метод добавления списка транспортных средств на парковку.
     *
     * @param vehicleList список транспортных стредств.
     * @return true или false в зависимости от результата.
     */
    @Override
    public boolean addVehicleList(List<Vehicle> vehicleList) {
        boolean added = false;
        for (Vehicle v : vehicleList) {
            added = addVehicle(v);
        }
        return added;
    }

    /**
     * Метод удаления транспортных средств с парковки по
     * их гос.номеру
     *
     * @param carLicensePlate гос.номер транспортного средства.
     * @return объект транспортного средства.
     */
    @Override
    public Vehicle removeVehicle(String carLicensePlate) {
        if (getNumberOfCars() == 0) {
            throw new IllegalArgumentException("No vehicles in the parking lot.");
        }
        if (!cars.isEmpty()) {
            for (Vehicle v : cars) {
                if (v.getCarLicensePlate().equals(carLicensePlate)) {
                    cars.remove(v);
                    numberOfCars--;
                    carPlaces++;
                    return v;
                }
            }
        }
        if (!trucks.isEmpty()) {
            for (Vehicle v : trucks) {
                if (v.getCarLicensePlate().equals(carLicensePlate)) {
                    trucks.remove(v);
                    numberOfCars--;
                    truckPlaces++;
                    return v;
                }
            }
        }
        return null;
    }

    /**
     * Метод возвращающий все транспортные средства,
     * находящихся на парковке.
     *
     * @return список транспортных средств.
     */
    @Override
    public List<Vehicle> getVehicles() {
        if (cars.isEmpty()) {
            return trucks;
        }
        if (trucks.isEmpty()) {
            return cars;
        }
        List<Vehicle> result = new ArrayList<>();
        result.addAll(cars);
        result.addAll(trucks);
        return result;
    }

    /**
     * Метод возвращающий число всех транспортных средств,
     * находящихся на праковке.
     *
     * @return число транспорных средств.
     */
    @Override
    public int getNumberOfCars() {
        return numberOfCars;
    }
}
