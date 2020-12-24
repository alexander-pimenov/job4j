package ru.job4j.nonblocking;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ModelCache {
    private final Map<Integer, Base> cache = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return cache.putIfAbsent(model.getId(), model) == model;
    }

    public void delete(Base model) {
        cache.remove(model.getId());
    }

    public void update(Base model) {
        cache.computeIfPresent(model.getId(),
                (key, oldValue) -> {
                    if (model.getVersion() != oldValue.getVersion()) {
                        throw new OptimisticException("Invalid version");
                    }
                    oldValue.setVersion(oldValue.getVersion() + 1);
                    oldValue.setName(model.getName());
                    return oldValue;
                });
    }

    public void update2(Base model) {
        cache.computeIfPresent(model.getId(),
                (key, oldValue) -> {
                    int newVersion = model.getVersion();
                    if (oldValue.getVersion() != newVersion) {
                        throw new OptimisticException("Invalid version");
                    }
                    model.setVersion(++newVersion);
                    model.setName(model.getName());
                    return model;
                });
    }

    public void update3(Base model) {
        cache.computeIfPresent(model.getId(),
                (key, oldValue) -> {
                    if (oldValue.getVersion() != model.getVersion()) {
                        throw new OptimisticException("Invalid version");
                    }
                    int version = model.getVersion();
                    oldValue.setVersion(++version);
                    oldValue.setName(model.getName());
                    return oldValue;
                });
    }

    public void print() {
        cache.forEach((i, data) -> System.out.println(data));
    }
}
