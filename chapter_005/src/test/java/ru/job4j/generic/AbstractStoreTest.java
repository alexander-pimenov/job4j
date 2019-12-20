package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AbstractStoreTest {

    /*Проверяем объект User*/
    @Test
    public void whenAddUserThanShouldBeSame() {

        UserStore userStore = new UserStore(5);
        User user = new User("11");

        userStore.add(user);

        assertThat(userStore.findById("11"), is(user));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddUsersBiggerThanSize() {

        UserStore userStore = new UserStore(2);
        User user1 = new User("11");
        User user2 = new User("22");
        User user3 = new User("33");

        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);
    }

    @Test
    public void whenAddTreeUsersAdnDeleteTwoShouldLeftOne() {

        UserStore userStore = new UserStore(3);
        User user1 = new User("11");
        User user2 = new User("22");
        User user3 = new User("33");

        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);

        userStore.delete("11");
        userStore.delete("22");

        assertThat(userStore.findById("33"), is(user3));
    }

    @Test
    public void whenReplaceUserByIdThanReplaced() {
        UserStore userStore = new UserStore(2);

        User user1 = new User("11");
        User user2 = new User("22");

        userStore.add(user1);
        userStore.add(user2);

        User user3 = new User("555");
        boolean result = userStore.replace("11", user3);

        assertThat(result, is(true));
        assertThat(userStore.findById("555"), is(user3));
    }

    @Test
    public void whenDeleteUserWhitWrongId() {

        UserStore userStore = new UserStore(2);
        User user1 = new User("11");
        User user2 = new User("22");

        userStore.add(user1);
        userStore.add(user2);

        assertFalse(userStore.delete("33"));
    }

    @Test
    public void whenDeleteUserInEmptyArray() {

        UserStore userStore = new UserStore(0);

        assertFalse(userStore.delete("33"));
    }

    /*Проверяем объект Role*/
    @Test
    public void whenAddRoleThanShouldBeSame() {

        RoleStore roleStore = new RoleStore(5);
        Role role = new Role("11");

        roleStore.add(role);

        assertThat(roleStore.findById("11"), is(role));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddRolesBiggerThanSize() {

        RoleStore userStore = new RoleStore(2);
        Role role1 = new Role("11");
        Role role2 = new Role("22");
        Role role3 = new Role("33");

        userStore.add(role1);
        userStore.add(role2);
        userStore.add(role3);
    }

    @Test
    public void whenAddTreeRolesAdnDeleteTwoShouldLeftOne() {

        RoleStore roleStore = new RoleStore(3);
        Role role1 = new Role("11");
        Role role2 = new Role("22");
        Role role3 = new Role("33");

        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.add(role3);

        roleStore.delete("11");
        roleStore.delete("22");

        assertThat(roleStore.findById("33"), is(role3));
    }

    @Test
    public void whenReplaceRoleByIdThanReplaced() {
        RoleStore roleStore = new RoleStore(2);

        Role role1 = new Role("11");
        Role role2 = new Role("22");

        roleStore.add(role1);
        roleStore.add(role2);

        Role role3 = new Role("555");
        boolean result = roleStore.replace("11", role3);

        assertThat(result, is(true));
        assertThat(roleStore.findById("555"), is(role3));
    }

    @Test
    public void whenDeleteRoleWhitWrongId() {

        RoleStore roleStore = new RoleStore(2);
        Role role1 = new Role("11");
        Role role2 = new Role("22");

        roleStore.add(role1);
        roleStore.add(role2);

        assertFalse(roleStore.delete("33"));
    }

    @Test
    public void whenDeleteRoleInEmptyArray() {

        RoleStore roleStore = new RoleStore(0);

        assertFalse(roleStore.delete("33"));
    }

}