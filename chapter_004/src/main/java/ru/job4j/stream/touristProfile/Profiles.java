package ru.job4j.stream.touristProfile;

import java.util.List;
import java.util.stream.Collectors;

public class Profiles {
    public static List<Address> collect(List<Profile> profiles) {
        return profiles.stream().map(Profile::getAddress).distinct().sorted().collect(Collectors.toList());
    }
    public static void main(String[] args) {
        List<Profile> profile = List.of(
                new Profile(new Address("Moskva", "Tverskay", 10, 120)),
                new Profile(new Address("Minsk", "Ivanova", 20, 220)),
                new Profile(new Address("Kiev", "Hreshatik", 30, 320)),
                new Profile(new Address("Kursk", "Svobody", 40, 420)),
                new Profile(new Address("Belgorod", "Belaya", 50, 520)),
                new Profile(new Address("Moskva", "Kitayskaya", 60, 620)),
                new Profile(new Address("Moskva", "Kitayskaya", 160, 620)),
                new Profile(new Address("Kiev", "Kievskaya", 70, 720)),
                new Profile(new Address("Harkov", "Sumskaya", 90, 920)),
                new Profile(new Address("Riga", "Petrova", 80, 820)),
                new Profile(new Address("Riga", "Petrova", 80, 820)),
                new Profile(new Address("Riga", "Petrova", 80, 820)),
                new Profile(new Address("Riga", "Petrova", 180, 820)
                ));

        profile.stream().map(Profile::getAddress).distinct().sorted().forEach(System.out::println);
        System.out.println("====================");
        //System.out.println(collect(profile));
    }
}
//Address{city='Belgorod', street='Belaya', home=50, apartment=520}
//Address{city='Harkov', street='Sumskaya', home=90, apartment=920}
//Address{city='Kiev', street='Hreshatik', home=30, apartment=320}
//Address{city='Kiev', street='Kievskaya', home=70, apartment=720}
//Address{city='Kursk', street='Svobody', home=40, apartment=420}
//Address{city='Minsk', street='Ivanova', home=20, apartment=220}
//Address{city='Moskva', street='Kitayskaya', home=60, apartment=620}
//Address{city='Moskva', street='Kitayskaya', home=160, apartment=620}
//Address{city='Moskva', street='Tverskay', home=10, apartment=120}
//Address{city='Riga', street='Petrova', home=80, apartment=820}
//Address{city='Riga', street='Petrova', home=180, apartment=820}
//====================
