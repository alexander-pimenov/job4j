package ru.job4j.design.lsp.productdistribution;

import java.time.Duration;
import java.time.LocalDate;

public class DateUtil {
    /**
     * Метод вычисляющий количество израсходованных дней срока годности
     * товара.
     *
     * @param start  дата изготовления товара (LocalDate).
     * @param finish окончание срока годности товара (LocalDate).
     * @return число в процентах.
     */
    public static int calculateExpirationDateConsumption(LocalDate start, LocalDate finish) {
        final LocalDate now = LocalDate.now();

        if (start.isAfter(finish)) {
            throw new IllegalArgumentException();
        }
        if (now.isBefore(start)) {
            return 0;
        }
        if (now.isAfter(finish)) {
            return 100;
        }

        //Весь промежуток срока годности.
        final float allPeriod = Duration.between(start.atStartOfDay(), finish.atStartOfDay()).toDays();
        //Сколько прошло времени от даты изготовления продукта до сегодняшней даты.
        final float wentTime = Duration.between(start.atStartOfDay(), now.atStartOfDay()).toDays();
        return (int) Math.round(wentTime / allPeriod * 100);
    }

    /**
     * Метод вычисляющий соотношение дат,
     * т.е.показывающий сколько дней прошло до сегодня и
     * сколько дней осталось до окончания срока годности.
     *
     * @param start  дата изготовления (LocalDate).
     * @param finish окончание срока годности (LocalDate).
     * @return строковое представление соотношений.
     */
    public static String calculateDateRatio(LocalDate start, LocalDate finish) {
        final LocalDate now = LocalDate.now();
        final long beforeToday = Duration.between(start.atStartOfDay(), now.atStartOfDay()).toDays();
        final long afterToday = Duration.between(now.atStartOfDay(), finish.atStartOfDay()).toDays();
        return String.format("beforeToday %d / afterToday %d", beforeToday, afterToday);
    }
}
