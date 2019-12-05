package ru.job4j.stream.touristprofile;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

public class ProfilesTest {

    /*Создаем адреса клиентов.*/
    private final Address addr1 = new Address("City1", "Street1", 1, 1);
    private final Address addr2 = new Address("City2", "Street2", 2, 2);
    private final Address addr3 = new Address("City3", "Street3", 3, 3);
    private final Address addr4 = new Address("City4", "Street4", 4, 4);

    @Test
    public void whenCollectAddressToOneList() {
        //создаем список профилей содержащих разные адреса.
        List<Profile> profs = Arrays.asList(new Profile(addr1), new Profile(addr2),
                new Profile(addr3), new Profile(addr4));
        Profiles profiles = new Profiles();
        assertThat(profiles.collect(profs), containsInAnyOrder(addr1, addr2, addr3, addr4));
    }

    @Test
    public void whenCollectAddressWithDuplicates() {
        //создаем список профилей содержащих разные адреса, но с некоторыми дубликатами.
        List<Profile> profs = Arrays.asList(new Profile(addr1), new Profile(addr1),
                new Profile(addr2), new Profile(addr2));
        Profiles profiles = new Profiles();
        assertThat(profiles.collect(profs), containsInAnyOrder(addr1, addr2));
    }

    @Test
    public void whenCollectAddressThenSortedResult() {
        //создаем список профилей содержащих разные адреса в неотсортированном порядке.
        List<Profile> profs = Arrays.asList(new Profile(addr4), new Profile(addr2),
                new Profile(addr1), new Profile(addr3));
        Profiles profiles = new Profiles();
        assertThat(profiles.collect(profs), contains(addr1, addr2, addr3, addr4));
    }

}
