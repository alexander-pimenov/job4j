package ru.job4j.bank;


import java.util.*;

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

    /*Получить список счетов для пользователя по данным паспорта.*/
    public List<Account> getUserAccounts(String passport) {
        List<Account> result = new ArrayList<>();
        for (User user : this.userAccounts.keySet()) {
            if (user.getPassport().equals(passport)) {
                result = this.userAccounts.get(user);
            }
        }
        return result;
    }

    /*Поиск пользователя по данным паспорта*/
    private User findUser(String passport) {
        User result = null;
        Set<User> keys = this.userAccounts.keySet();
        for (User key : keys) {
            if (key != null && key.getPassport().equals(passport)) {
                result = key;
                break;
            }
        }
        return result;
    }

    /*Поиск аккаунта (одного счета) пользователя по данным паспорта и реквизитов счета*/
    private Account findAccount(String passport, String requisite) {
        Account resultAccount = null;
        for (Account account : getUserAccounts(passport)) {
            if (account.getRequisites().equals(requisite)) {
                resultAccount = account;
            }
        }
        return resultAccount;
    }


    /*Метод для перечисления денег с одного счета на другой счет.
     * Если счет не найден или не хватает денег на счету srcAccount (
     * с которого переводят), то должен вернуть false.*/
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean canTransfer = false;
        //ищем пользователей с указанными паспортами
        User srcUser = findUser(srcPassport);
        User destUser = findUser(destPassport);

        //ищем аккаунты пользователя srcUser
        Account srcAccount = findAccount(srcPassport, srcRequisite);
        //ищем аккаунты пользователя destUser
        Account destAccount = findAccount(destPassport, destRequisite);

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

