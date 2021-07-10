package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ContentReader implements Reader<Character> {
    private final File file;

    public ContentReader(File file) {
        this.file = file;
    }

    public synchronized String read(Predicate<Character> filter) {
        StringBuilder sb = new StringBuilder();
        try (InputStream i = new FileInputStream(file)) {
            int data;
            while ((data = i.read()) > 0) {
                if (filter.test((char) data)) {
                    sb.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}