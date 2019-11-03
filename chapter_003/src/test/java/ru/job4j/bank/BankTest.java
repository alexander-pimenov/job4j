package ru.job4j.bank;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class BankTest {

    private Bank bank = new Bank();

    @Test
    public void whenAddUser1() {
        User petrov = new User("Petrov", "1111-5678");
        User ivanov = new User("Ivanov", "2222-5678");
        bank.addUser(petrov);
        bank.addUser(ivanov);
        assertThat(bank.getUser("1111-5678"), is(petrov));
        assertThat(bank.getUser("2222-5678"), is(ivanov));

    }

    @Test
    public void whenDeleteUser1() {
        Bank bank = new Bank();
        User petrov = new User("Petrov", "1111-5678");
        bank.addUser(petrov);
        bank.deleteUser(petrov);
        assertThat(bank.getUser("1111-5678"), is(nullValue()));
    }


    @Test
    public void whenAddTwoAccountsToUser1() {
        User petrov = new User("Petrov", "1111-5678");
        Account first = new Account("10-20-30", 1000);
        Account second = new Account("40-50-60", 2000);
        bank.addUser(petrov);
        bank.addAccountToUser("1111-5678", first);
        bank.addAccountToUser("1111-5678", second);
        List<Account> result = bank.getUserAccounts("1111-5678");
        assertThat(result.size(), is(2));
    }

    @Test
    public void whenDeleteAccountFromUser1() {
        User petrov = new User("Petrov", "1111-5678");
        Account first = new Account("10-20-30", 1000);
        Account second = new Account("40-50-60", 2000);
        Account third = new Account("70-80-90", 3000);
        bank.addUser(petrov);
        bank.addAccountToUser("1111-5678", first);
        bank.addAccountToUser("1111-5678", second);
        bank.addAccountToUser("1111-5678", third);
        List<Account> result = bank.getUserAccounts("1111-5678");
        assertThat(result.size(), is(3));
        bank.deleteAccountFromUser("1111-5678", first);
        result = bank.getUserAccounts("1111-5678");
        assertThat(result.size(), is(2));
    }

    @Test
    public void whenGetUserAccounts1() {
        User petrov = new User("Petrov", "1111-5678");
        Account first = new Account("10-20-30", 1000);
        Account second = new Account("40-50-60", 2000);
        Account third = new Account("70-80-90", 3000);
        bank.addUser(petrov);
        bank.addAccountToUser("1111-5678", first);
        bank.addAccountToUser("1111-5678", second);
        bank.addAccountToUser("1111-5678", third);
        List<Account> result = bank.getUserAccounts("1111-5678");
        assertThat(result.size(), is(3));

    }

    @Test
    public void whenTransferMoney1() {
        User petrov = new User("Petrov", "1111-5678");
        User ivanov = new User("Ivanov", "2222-5678");
        Account first = new Account("10-20-30", 1000);
        Account second = new Account("40-50-60", 2000);
        bank.addUser(petrov);
        bank.addUser(ivanov);
        Account third = new Account("70-80-90", 3000);
        bank.addAccountToUser("1111-5678", first);
        bank.addAccountToUser("1111-5678", second);
        bank.addAccountToUser("2222-5678", third);
        boolean result = bank.transferMoney("1111-5678", "10-20-30", "2222-5678", "70-80-90", 300);

        List<Account> srcUserAccounts = bank.getUserAccounts("1111-5678");
        List<Account> destUserAccounts = bank.getUserAccounts("2222-5678");

        double srcValue = srcUserAccounts.get(0).getValue();
        double destValue = destUserAccounts.get(0).getValue();
        assertThat(srcValue, is(700.0));
        assertThat(destValue, is(3300.0));
        assertThat(result, is(true));
    }

    @Test
    public void whenTransferMoneyFromOneUsersAccountToAnother1() {
        User petrov = new User("Petrov", "1111-5678");
        Account first = new Account("10-20-30", 1000);
        Account second = new Account("40-50-60", 2000);
        bank.addUser(petrov);
        bank.addAccountToUser("1111-5678", first);
        bank.addAccountToUser("1111-5678", second);
        bank.transferMoney("1111-5678", "10-20-30", "1111-5678", "40-50-60", 300);

        List<Account> userAccounts = bank.getUserAccounts("1111-5678");

        double srcValue = userAccounts.get(0).getValue();
        double destValue = userAccounts.get(1).getValue();

        assertThat(srcValue, is(700.0));
        assertThat(destValue, is(2300.0));
    }
}