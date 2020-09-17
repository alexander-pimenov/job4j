package ru.job4j.design.srp;

/**
 * Class that converts the value of a salary in rubles to a dollar value.
 */
public class MoneyConverter {

    private static final double USD = 70;

    public static String inDollar(double rub) {
        return String.format("%.2f $", rub / USD);
    }
}
