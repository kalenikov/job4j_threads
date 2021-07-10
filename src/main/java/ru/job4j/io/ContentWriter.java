package ru.job4j.io;

import java.io.*;

public class ContentWriter implements Writer {
    private final File file;

    public ContentWriter(File file) {
        this.file = file;
    }

    public synchronized void write(String content) {
        try (OutputStream o = new FileOutputStream(file)) {
            for (int i = 0; i < content.length(); i++) {
                o.write(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}