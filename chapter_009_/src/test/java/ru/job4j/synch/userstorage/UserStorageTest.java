package ru.job4j.synch.userstorage;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserStorageTest {

    @Test
    public void whenAddUser() {
        UserStorage storage = new UserStorage();
        User user = new User(1, 100);
        storage.add(user);
        User expected = storage.findById(1);
        assertThat(expected, is(user));
    }

    @Test
    public void whenUpdateUser() {
        UserStorage storage = new UserStorage();
        User user = new User(1, 100);
        storage.add(user);
        User updated = new User(1, 200);
        storage.update(updated);
        User expected = storage.findById(1);
        assertThat(expected, is(updated));
    }

    @Test
    public void whenDeleteUser() {
        UserStorage storage = new UserStorage();
        User user = new User(1, 100);
        storage.add(user);
        storage.delete(user);
        User expected = storage.findById(1);
        assertThat(expected, is(nullValue()));
    }

    @Test
    public void whenTransferBetweenUsersTrue() {
        UserStorage storage = new UserStorage();
        User userFrom = new User(1, 100);
        User userTo = new User(2, 300);
        storage.add(userFrom);
        storage.add(userTo);
        assertTrue(storage.transfer(1, 2, 50));
    }

    @Test
    public void whenTransferBetweenUsersFalse() {
        UserStorage storage = new UserStorage();
        User userFrom = new User(1, 100);
        User userTo = new User(2, 300);
        storage.add(userFrom);
        storage.add(userTo);
        assertFalse(storage.transfer(2, 1, 500));
    }

    @Test
    public void whenTransferBetweenUsersAndSeeTheirMoney() {
        UserStorage storage = new UserStorage();
        User userFrom = new User(1, 100);
        User userTo = new User(2, 300);
        storage.add(userFrom);
        storage.add(userTo);
        storage.transfer(1, 2, 70);
        assertThat(storage.findById(1).getAmount(), is(30));
        assertThat(storage.findById(2).getAmount(), is(370));
    }
}