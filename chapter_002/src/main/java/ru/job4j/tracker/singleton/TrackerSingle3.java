package ru.job4j.tracker.singleton;

import ru.job4j.tracker.start.Tracker;

public class TrackerSingle3 extends Tracker {
    private static final TrackerSingle3 INSTANCE = new TrackerSingle3();

    private TrackerSingle3() {
    }

    public static TrackerSingle3 getInstance() {
        return INSTANCE;
    }
}
