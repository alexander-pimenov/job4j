package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PhoneDictionary {
    private List<Person> persons = new ArrayList<>();

    //добавить объект Person в ArrayList
    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, которые содержат
     * ключ поиска key в любых полях объекта Person
     *
     * @param key Ключ поиска
     * @return Список подошедших пользователей
     */
    public List<Person> find(String key) {
//        List<Person> result = new ArrayList<>();
//        for (var person : persons) { //var=Person
//            if (person.getName().contains(key)
//                    || person.getSurname().contains(key)
//                    || person.getPhone().contains(key)
//                    || person.getAddress().contains(key)) {
//                result.add(person);
//            }
//        }
//        return result;

        return this.persons.stream()
                .filter(p -> p.getName().contains(key)
                        || p.getSurname().contains(key)
                        || p.getPhone().contains(key)
                        || p.getAddress().contains(key))
                .collect(Collectors.toList());
    }

}

