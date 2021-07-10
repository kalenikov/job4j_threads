package ru.job4j.io;

import java.util.function.Predicate;

public interface Reader<T> {
    String read(Predicate<T> filter);
}
