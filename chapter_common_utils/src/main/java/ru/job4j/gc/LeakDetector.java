package ru.job4j.gc;

import org.assertj.core.api.Assertions;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * Created on 23.06.2018.
 * <p>
 * Provides the ability to check memory leaks for a particular object.
 * https://github.com/antkorwin/common-utils/blob/master/src/main/java/com/antkorwin/commonutils/gc/LeakDetector.java
 *
 * @author Korovin Anatoliy
 */
public class LeakDetector extends PhantomReference<Object> {
    private final String description;

    /**
     * Initialization of the memory leaks detector.
     *
     * @param referent the object(resource) for which we are looking for leaks.
     */
    public LeakDetector(Object referent) {
        super(referent, new ReferenceQueue<>());
        this.description = String.valueOf(referent);
    }

    /**
     * If exists memory leaks then throws a fail.
     * <p>
     * WARN: Run this method after delete all references on the checkable object(resource)
     */
    public void assertMemoryLeaksNotExist() {
        GcUtils.fullFinalization();

        Assertions.assertThat(isEnqueued())
                .as("Object: " + description + " was leaked")
                .isTrue();
    }

    /**
     * If not exists memory leaks then throws a fail.
     * <p>
     * WARN: Run this method after delete all references on the checkable object(resource)
     */
    public void assertMemoryLeaksExist() {
        GcUtils.fullFinalization();

        Assertions.assertThat(isEnqueued())
                .as("Object: " + description + " was collected by GC")
                .isFalse();
    }
}
