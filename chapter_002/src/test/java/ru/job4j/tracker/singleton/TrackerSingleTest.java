package ru.job4j.tracker.singleton;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerSingleTest {

    @Test
    public void whenCreateTrackerSingle1Enum1ThenSameObject() {
        TrackerSingle1 tracker1 = TrackerSingle1.INSTANCE;
        TrackerSingle1 tracker2 = TrackerSingle1.INSTANCE;
        assertThat(tracker1, is(tracker2));
    }

    @Test
    public void whenCreateTrackerSingle2ThenSameObject() {
        TrackerSingle2 tracker1 = TrackerSingle2.getInstance();
        TrackerSingle2 tracker2 = TrackerSingle2.getInstance();
        assertThat(tracker1, is(tracker2));
    }

    @Test
    public void whenCreateTrackerSingle3ThenSameObject() {
        TrackerSingle3 tracker1 = TrackerSingle3.getInstance();
        TrackerSingle3 tracker2 = TrackerSingle3.getInstance();
        assertThat(tracker1, is(tracker2));
    }

    @Test
    public void whenCreateTrackerSingle4ThenSameObject() {
        TrackerSingle4 tracker1 = TrackerSingle4.getInstance();
        TrackerSingle4 tracker2 = TrackerSingle4.getInstance();
        assertThat(tracker1, is(tracker2));
    }
}
