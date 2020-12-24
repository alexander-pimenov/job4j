package ru.job4j.nonblocking;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.is;

public class ModelCacheTest {

    @Test
    public void whenThrowException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        ModelCache modelCache = new ModelCache();
        Base base = new Base(1, "Alex");
        modelCache.add(base);

        Thread thread1 = new Thread(
                () -> {
                    try {
                        modelCache.update(new Base(1, "Alex1"));
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    try {
                        modelCache.update(new Base(1, "Alex2"));
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        modelCache.print();
        Assert.assertThat(ex.get().getMessage(), is("Invalid version"));
    }
}