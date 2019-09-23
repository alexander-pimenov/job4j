package ru.job4j.bank;

/*Класс Account - банковский счет.*/

public class Account {

    /*Количество денег на счете*/
    private double value;

    /*Реквизиты счета, это банковский счет*/
    private String requisites;

    public Account(String requisites, double value) {
        this.requisites = requisites;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public String getRequisites() {
        return requisites;
    }

    public void setValue(double value) {
        this.value = value;
    }

    /*Метод увеличивающий деньги на счете*/
    public void addMoney(double amount) {
        this.value += amount;
    }

    /*Метод уменьшающий деньги на счете  */
    public void substractMoney(double amount) {
        this.value -= amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return requisites.equals(account.requisites);
    }

    @Override
    public int hashCode() {
        return requisites.hashCode();
    }

    @Override
    public String toString() {
        return "Account{" +
                "value=" + value +
                ", requisites='" + requisites + '\'' +
                '}';
    }
}
