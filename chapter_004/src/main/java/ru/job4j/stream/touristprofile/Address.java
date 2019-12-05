package ru.job4j.stream.touristprofile;

import java.util.Objects;

/*Карточка адреса клиента туристической компании.*/

public class Address implements Comparable<Address> {
    private String city;
    private String street;
    private int home;
    private int apartment;

    public Address(String city, String street, int home, int apartment) {
        this.city = city;
        this.street = street;
        this.home = home;
        this.apartment = apartment;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getHome() {
        return home;
    }

    public int getApartment() {
        return apartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return home == address.home
                && apartment == address.apartment
                && Objects.equals(city, address.city)
                && Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, home, apartment);
    }

    @Override
    public String toString() {
        return "Address{" + "city='" + city + '\''
                + ", street='" + street + '\''
                + ", home=" + home
                + ", apartment=" + apartment + '}';
    }

    @Override
    public int compareTo(Address o) {
        int result;
        result = this.city.compareTo(o.city);
        if (result != 0) {
            return result;
        }
        result = this.street.compareTo(o.street);
        if (result != 0) {
            return result;
        }
        result = Integer.compare(home, o.home);
        if (result != 0) {
            return result;
        }
        result = Integer.compare(apartment, o.apartment);
        return result;
    }

    //Упрощенный вариант, сортировка только по полю city
    //    @Override
//    public int compareTo(Address o) {
//        if (this.city == null) {
//            return -1;
//        }
//        if (o.city == null) {
//            return 1;
//        }
//        return this.city.compareTo(o.city);
//    }

}
