package ru.job4j.design.srp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A class that converts the value of a Calendar object
 * to a string value according to the format yyy-MM-dd HH: mm
 */

public class DateConverter {

    public static String convert(Calendar calendar) {
        if (calendar == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(calendar.getTime());
    }
}
