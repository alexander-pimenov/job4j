package ru.job4j.stream.touristprofile;

public class Profile {
    private Address address;

    Profile(Address address) {
        this.address = address;
    }

    Address getAddress() {
        return address;
    }
}
