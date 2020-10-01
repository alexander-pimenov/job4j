package ru.job4j.design.lsp.carparking;

import org.junit.Before;
import org.junit.Test;

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
        assertThat(parkingService.getNumberOfCars(), is(2));
    }

    @Test
    public void whenRemoveVehicleFromParking() {
        ParkingService parkingService = new ParkingService(2, 1);
        parkingService.addVehicle(car1);
        parkingService.addVehicle(truck1);
        assertThat(parkingService.removeVehicle("A123AA77"), is(car1));
        assertThat(parkingService.getNumberOfCars(), is(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenRemoveVehicleFromEmptyParking() {
        ParkingService parkingService = new ParkingService(2, 1);
        parkingService.removeVehicle("A123AA77");
    }
}