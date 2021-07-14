package ru.job4j.cas;

import java.util.concurrent.atomic.AtomicReference;

public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);
    public void increment() {
        Integer i;
        do {
            i = count.get();
        } while (!count.compareAndSet(i, i + 1));
    }
    public int get() {
        return count.get();
    }
}
