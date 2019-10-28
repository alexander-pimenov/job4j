package ru.job4j.stream.touristProfile;

import java.util.List;
import java.util.stream.Collectors;

public class Profiles {
    public List<Address> collect(List<Profile> profiles) {
        return profiles.stream().map(Profile::getAddress).distinct().sorted().collect(Collectors.toList());
    }
}

//Кроме проверки в Test мы можем проверить этот код и в main методе.
//проверяем работу цепочки стрима с методами map, distinct, sorted, forEach используем main метод.
//    public static void main(String[] args) {
//        List<Profile> profile = List.of(
//                new Profile(new Address("City1", "Street4", 10, 120)),
//                new Profile(new Address("City2", "Street1", 20, 220)),
//                new Profile(new Address("City1", "Street2", 30, 320)),
//                new Profile(new Address("City2", "Street3", 40, 420)),
//                new Profile(new Address("City1", "Street4", 50, 520)),
//                new Profile(new Address("City2", "Street1", 60, 620)),
//                new Profile(new Address("City1", "Street2", 70, 620)),
//                new Profile(new Address("City2", "Street3", 80, 720)),
//                new Profile(new Address("City4", "Street4", 90, 920)),
//                new Profile(new Address("City2", "Street1", 100, 820)),
//                new Profile(new Address("City3", "Street2", 110, 820)),
//                new Profile(new Address("City4", "Street3", 120, 820)),
//                new Profile(new Address("City4", "Street4", 120, 920)),
//                new Profile(new Address("City4", "Street4", 120, 920)),
//                new Profile(new Address("City4", "Street4", 120, 920)
//                ));
//        profile.stream().map(Profile::getAddress).forEach(System.out::println);
//        System.out.println("====================");
//
//        profile.stream().map(Profile::getAddress).distinct().sorted().forEach(System.out::println);
//        System.out.println("====================");
//        //System.out.println(collect(profile));
//    }
//}
//Output:
//Address{city='City1', street='Street4', home=10, apartment=120}
//Address{city='City2', street='Street1', home=20, apartment=220}
//Address{city='City1', street='Street2', home=30, apartment=320}
//Address{city='City2', street='Street3', home=40, apartment=420}
//Address{city='City1', street='Street4', home=50, apartment=520}
//Address{city='City2', street='Street1', home=60, apartment=620}
//Address{city='City1', street='Street2', home=70, apartment=620}
//Address{city='City2', street='Street3', home=80, apartment=720}
//Address{city='City4', street='Street4', home=90, apartment=920}
//Address{city='City2', street='Street1', home=100, apartment=820}
//Address{city='City3', street='Street2', home=110, apartment=820}
//Address{city='City4', street='Street3', home=120, apartment=820}
//Address{city='City4', street='Street4', home=120, apartment=920}
//Address{city='City4', street='Street4', home=120, apartment=920}
//Address{city='City4', street='Street4', home=120, apartment=920}
//====================
//Address{city='City1', street='Street2', home=30, apartment=320}
//Address{city='City1', street='Street2', home=70, apartment=620}
//Address{city='City1', street='Street4', home=10, apartment=120}
//Address{city='City1', street='Street4', home=50, apartment=520}
//Address{city='City2', street='Street1', home=20, apartment=220}
//Address{city='City2', street='Street1', home=60, apartment=620}
//Address{city='City2', street='Street1', home=100, apartment=820}
//Address{city='City2', street='Street3', home=40, apartment=420}
//Address{city='City2', street='Street3', home=80, apartment=720}
//Address{city='City3', street='Street2', home=110, apartment=820}
//Address{city='City4', street='Street3', home=120, apartment=820}
//Address{city='City4', street='Street4', home=90, apartment=920}
//Address{city='City4', street='Street4', home=120, apartment=920}
//====================
