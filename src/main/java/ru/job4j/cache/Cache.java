package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (k, stored) -> {
            if (stored.getVersion() != model.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            Base rsl = new Base(model.getId(), model.getVersion() + 1);
            rsl.setName(model.getName());
            return rsl;
        }) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId());
    }

    public Base get(Integer id) {
        return memory.get(id);
    }
}