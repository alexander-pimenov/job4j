package ru.job4j.gc;


import java.util.ArrayList;
import java.util.List;

/**
 * Simple program that illustrates how to use Java Flight Recorder.
 * <p>
 * This programs creates a list, inserts objects in it until
 * an OutOfMemoryError is thrown.
 * <p>
 * We use these VM settings:
 * -XX:+UseSerialGC -Xms1024m -Xmx1024m -Xlog:gc*::time -Xlog:gc:C:\projects\job4j\chapter_008\gcFlightRecorder.txt
 */
public class FlightRecorder {
    public static void main(String[] args) {
        List<Object> items = new ArrayList<>(1);
        try {
            while (true) {
                items.add(new Object());
            }
        } catch (OutOfMemoryError e) {
            System.out.println(" => " + e.getMessage());
        }
        assert items.size() > 0;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(" ==> " + e.getMessage());
        }
    }
}
