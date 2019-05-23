package ru.job4j.tracker.singleton;

import ru.job4j.tracker.start.Tracker;

public class TrackerSingle4 extends Tracker {
    private TrackerSingle4() {
    }

    public static TrackerSingle4 getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final TrackerSingle4 INSTANCE = new TrackerSingle4();
    }
}
