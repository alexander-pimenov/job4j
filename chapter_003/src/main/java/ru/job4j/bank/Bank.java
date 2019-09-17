package ru.job4j.bank;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Bank {
    /**/
    private Map<User, ArrayList<Account>> userAccounts = new TreeMap<>();

    /*Добавление пользователя.
     * Если указанный пользователь не существует в банке,
     * добавляет пользователя с пустым списком счетов.*/
    public void addUser(User user) {
        this.userAccounts.putIfAbsent(user, new ArrayList<>());
    }

    public User getUser(String passport) {
        User result = null;
        for (User user : this.userAccounts.keySet()) {
            if (user.getPassport().equals(passport)) {
                result = user;
            }
        }
        return result;
    }

    /*Удаление пользователя.*/
    public void deleteUser(User user) {
        this.userAccounts.remove(user);
    }

    /*Добавить счет пользователю.*/
    public void addAccountToUser(String passport, Account account) {
        for (User user : this.userAccounts.keySet()) {
            if (user.getPassport().equals(passport)) {
                this.userAccounts.get(user).add(account);
            }
        }
    }

    /*Удалить один счет пользователя.*/
    public void deleteAccountFromUser(String passport, Account account) {
        for (User user : this.userAccounts.keySet()) {
            if (user.getPassport().equals(passport)) {
                this.userAccounts.get(user).remove(account);
            }
        }
    }

    /*Получить список счетов для пользователя.*/
    public List<Account> getUserAccounts(String passport) {
        List<Account> result = new ArrayList<>();
        for (User user : this.userAccounts.keySet()) {
            if (user.getPassport().equals(passport)) {
                result = this.userAccounts.get(user);
            }
        }
        return result;
    }

    /*Метод для перечисления денег с одного счета на другой счет.
     * Если счет не найден или не хватает денег на счету srcAccount (
     * с которого переводят), то должен вернуть false.*/
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean canTransfer = false;
        User srcUser = null;
        User destUser = null;
        Account srcAccount = null;
        Account destAccount = null;
        //ищем пользователей с указанными паспортами
        for (User user : this.userAccounts.keySet()) {
            if (user.getPassport().equals(srcPassport)) {
                srcUser = user;
            }
            if (user.getPassport().equals(destPassport)) {
                destUser = user;
            }
        }
        //ищем аккаунты пользователя srcUser
        for (Account account : getUserAccounts(srcPassport)) {
            if (account.getRequisites().equals(srcRequisite)) {
                srcAccount = account;
            }
        }

        //ищем аккаунты пользователя destUser
        for (Account account : getUserAccounts(destPassport)) {
            if (account.getRequisites().equals(destRequisite)) {
                destAccount = account;
            }
        }

        if (srcAccount != null && destAccount != null) {
            if (this.userAccounts.containsKey(srcUser)
                    && this.userAccounts.containsKey(destUser)
                    && getUserAccounts(srcPassport).contains(srcAccount)
                    && getUserAccounts(destPassport).contains(destAccount)
                    && srcAccount.getValue() >= amount) {
                srcAccount.substractMoney(amount);
                destAccount.addMoney(amount);
                canTransfer = true;
            }
        }

        return canTransfer;
    }

    public String toString() {
        return "Bank{" + "accounts=" + userAccounts + "}";
    }

}

//Посмотри, как можно применить методы Map.putIfAbsent и List.indexOf
