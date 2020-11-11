package ru.job4j.gc;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Simple program that illustrates how to use Java Flight Recorder.
 * <p>
 * This programs creates a list, inserts objects in it until
 * an OutOfMemoryError is thrown.
 * <p>
 * We use these VM settings:
 * -XX:+UseSerialGC -Xms256m -Xmx256m -Xlog:gc*::time -Xlog:gc:C:\projects\job4j\chapter_008\gcOutOfMemory.txt
 */
public class OutOfMemoryGCLimitExceed {

    public static void addRandomDataToMap() {
        Map<Integer, String> dataMap = new HashMap<>();
        Random r = new Random();
        while (true) {

            dataMap.put(r.nextInt(), String.valueOf(r.nextInt()));
        }
    }

    public static void main(String[] args) {
        OutOfMemoryGCLimitExceed.addRandomDataToMap();
    }
}
