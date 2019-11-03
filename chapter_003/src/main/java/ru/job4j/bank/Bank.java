package ru.job4j.bank;


import java.util.*;
import java.util.stream.Stream;

public class Bank {
    /* Основное поле с данными.
     * У каждого пользователя набор уникальных аккаунтов,
     * уникальность аккаунта определяется по реквизитам.
     */
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
                break; //чтобы не вертеть цикл дальше
            }
        }
        return result;
    }

    public User getUser1(String passport) {
        return this.userAccounts.keySet().stream()
                .filter(user -> user.getPassport().equals(passport))
                .findAny().orElse(null);
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
                break; //чтобы не вертеть цикл дальше
            }
        }
    }

    public void addAccountToUser1(String passport, Account account) {
        this.getUserAccounts1(passport).add(account);
    }


    /*Удалить один счет пользователя.*/
    public void deleteAccountFromUser(String passport, Account account) {
        for (User user : this.userAccounts.keySet()) {
            if (user.getPassport().equals(passport)) {
                this.userAccounts.get(user).remove(account);
                break; //чтобы не вертеть цикл дальше
            }
        }
    }

    public void deleteAccountFromUser1(String passport, Account account) {
        this.getUserAccounts1(passport).remove(account);
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

    public List<Account> getUserAccounts1(String passport) {
        return this.userAccounts.get(findUser1(passport));
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

    private User findUser1(String passport) {
        Stream<User> userStream = this.userAccounts.keySet().stream();
        return userStream.filter(user -> user.getPassport().equals(passport))
                .findFirst().orElse(null);
    }

    /*Поиск аккаунта (одного счета) пользователя по данным паспорта и реквизитов счета*/
    private Account findAccount(String passport, String requisite) {
        Account resultAccount = null;
        for (Account account : getUserAccounts(passport)) {
            if (account.getRequisites().equals(requisite)) {
                resultAccount = account;
                break; //чтобы не вертеть цикл дальше
            }
        }
        return resultAccount;
    }

    private Account findAccount1(String passport, String requisite) {

        Stream<Account> accountStream = getUserAccounts1(passport).stream();

        return accountStream.filter(acc -> acc.getRequisites()
                .equals(requisite))
                .findFirst().orElse(null);
    }

    /*Метод для перечисления денег с одного счета на другой счет.
     * Если счет не найден или не хватает денег на счету srcAccount (
     * с которого переводят), то должен вернуть false.*/
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean canTransfer = false;

        //ищем аккаунты пользователя srcUser
        Account srcAccount = findAccount1(srcPassport, srcRequisite);
        //ищем аккаунты пользователя destUser
        Account destAccount = findAccount1(destPassport, destRequisite);

        if (srcAccount != null && destAccount != null) {
            if (srcAccount.getValue() >= amount) {
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

