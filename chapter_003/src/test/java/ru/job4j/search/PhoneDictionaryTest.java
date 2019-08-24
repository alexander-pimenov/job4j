package ru.job4j.search;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PhoneDictionaryTest {

    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );

        List<Person> persons = phones.find("Petr");
        assertThat(persons.iterator().next().getSurname(), is("Arsentev"));
    }

    @Test
    public void whenFindBySurname() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        phones.add(
                new Person("Igor", "Argonov", "555872", "Kursk")
        );
        List<Person> persons = phones.find("Arse");
        assertThat(persons.iterator().next().getSurname(), is("Arsentev"));
    }

    @Test
    public void whenFindByPhone() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        phones.add(
                new Person("Igor", "Argonov", "555872", "Kursk")
        );

        List<Person> persons = phones.find("534");
        assertThat(persons.iterator().next().getSurname(), is("Arsentev"));
    }

    @Test
    public void whenFindByPhoneSeveralPeople() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Sasha", "Chernov", "534777", "Voronezh")
        );
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        phones.add(
                new Person("Igor", "Argonov", "555872", "Kursk")
        );
        phones.add(
                new Person("Misha", "Alov", "534999", "Omsk")
        );

        List<Person> persons = phones.find("534");
        assertThat(persons.get(0).getSurname(), is("Chernov"));
        assertThat(persons.get(1).getSurname(), is("Arsentev"));
        assertThat(persons.get(2).getSurname(), is("Alov"));
    }
}

