package ru.job4j.design.lsp.carparking;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParkingServiceTest {

    private Vehicle car1;
    private Vehicle car2;
    private Vehicle truck1;
    private Vehicle truck2;

    @Before
    public void setUp() throws Exception {
        car1 = new Car("VW", "A123AA77");
        car2 = new Car("Volvo", "B123BB77");
        truck1 = new Truck("Man", "X111XX77");
        truck2 = new Truck("Kamaz", "Y111YY77");
    }

    @Test
    public void testAddVehicle() {
        ParkingService parkingService = new ParkingService(2, 1);
        assertTrue(parkingService.addVehicle(car1));
        assertTrue(parkingService.addVehicle(car2));
        assertTrue(parkingService.addVehicle(truck1));
        System.out.println(parkingService.getCars());
//        System.out.println(parkingService.getCars().size());
        System.out.println(parkingService.getTrucks());
//        System.out.println(parkingService.getTrucks().size());
        assertThat(parkingService.getNumberOfCars(), is(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenAddCarsMoreThanParkingLotsForPassengerCars() {
        ParkingService parkingService = new ParkingService(1, 1);
        parkingService.addVehicle(car1);
        parkingService.addVehicle(car2);
    }

    @Test
    public void whenAddTrucksMoreThanParkingLotsForTrucks() {
        ParkingService parkingService = new ParkingService(2, 1);
        parkingService.addVehicle(truck1);
        parkingService.addVehicle(truck2);
        //Видим, что truck1 занял места двух car
        System.out.println(parkingService.getCarPlaces());
        //И что truck2 занял место одного truck
        System.out.println(parkingService.getTruckPlaces());
        System.out.println(parkingService.getTrucks());
        assertThat(parkingService.getNumberOfCars(), is(2));
    }

    @Test
    public void whenRemoveVehicleFromParking() {
        ParkingService parkingService = new ParkingService(2, 1);
        parkingService.addVehicle(car1);
        parkingService.addVehicle(truck1);
        System.out.println(parkingService.getCarPlaces());
        System.out.println(parkingService.getTruckPlaces());
        assertThat(parkingService.removeVehicle("A123AA77"), is(car1));
        System.out.println(parkingService.getCarPlaces());
        System.out.println(parkingService.getTruckPlaces());
        assertThat(parkingService.getNumberOfCars(), is(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenRemoveVehicleFromEmptyParking() {
        ParkingService parkingService = new ParkingService(2, 1);
        parkingService.removeVehicle("A123AA77");
    }

    @Test
    public void whenAddVehicleList() {
        List<Vehicle> vehicleList = List.of(car1, car2, truck1, truck2);
        ParkingService parkingService = new ParkingService(2, 2);
        assertTrue(parkingService.addVehicleList(vehicleList));
        System.out.println(parkingService.getCarPlaces());
        System.out.println(parkingService.getTruckPlaces());
        assertThat(parkingService.getNumberOfCars(), is(4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenAddedVehicleListMoreThanParkingLots() {
        List<Vehicle> vehicleList = List.of(car1, car2, truck1, truck2);
        ParkingService parkingService = new ParkingService(2, 1);
        parkingService.addVehicleList(vehicleList);
    }
}