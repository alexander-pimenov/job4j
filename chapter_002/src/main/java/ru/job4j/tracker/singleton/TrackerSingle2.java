package ru.job4j.tracker.singleton;


import ru.job4j.tracker.start.Tracker;

public class TrackerSingle2 extends Tracker {
    private static TrackerSingle2 instance;

    private TrackerSingle2() {
    }

    public static TrackerSingle2 getInstance() {
        if (instance == null) {
            instance = new TrackerSingle2();
        }
        return instance;
    }
}
