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
    void addUser(User user) {
        this.userAccounts.putIfAbsent(user, new ArrayList<>());
    }

    User getUser(String passport) {
        return this.userAccounts.keySet().stream()
                .filter(user -> user.getPassport().equals(passport))
                .findAny().orElse(null);
    }

    /*Удаление пользователя.*/
    void deleteUser(User user) {
        this.userAccounts.remove(user);
    }

    /*Добавить счет пользователю.*/
    void addAccountToUser(String passport, Account account) {
        this.getUserAccounts(passport).add(account);
    }

    /*Удалить один счет пользователя.*/
    void deleteAccountFromUser(String passport, Account account) {
        this.getUserAccounts(passport).remove(account);
    }

    /*Получить список счетов для пользователя по данным паспорта.*/
    List<Account> getUserAccounts(String passport) {
        return this.userAccounts.get(findUser(passport));
    }

    /*Поиск пользователя по данным паспорта*/
    private User findUser(String passport) {
        Stream<User> userStream = this.userAccounts.keySet().stream();
        return userStream.filter(user -> user.getPassport().equals(passport))
                .findFirst().orElse(null);
    }

    /*Поиск аккаунта (одного счета) пользователя по данным паспорта и реквизитов счета*/
    private Account findAccount(String passport, String requisite) {

        Stream<Account> accountStream = getUserAccounts(passport).stream();

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
        Account srcAccount = findAccount(srcPassport, srcRequisite);
        //ищем аккаунты пользователя destUser
        Account destAccount = findAccount(destPassport, destRequisite);

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

