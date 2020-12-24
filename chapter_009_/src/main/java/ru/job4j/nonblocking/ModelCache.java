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

    public void print() {
        cache.forEach((i, data) -> System.out.println(data));
    }
}
