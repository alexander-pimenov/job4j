package ru.job4j.stream.touristProfile;

public class Profile {
    private Address address;

    Profile(Address address) {
        this.address = address;
    }

    Address getAddress() {
        return address;
    }
}
