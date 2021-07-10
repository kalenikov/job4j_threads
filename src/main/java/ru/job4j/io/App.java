package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class App {
    public static void main(String[] args) throws IOException, URISyntaxException {
        File in = new File(args[0]);
        if (in.exists()) {
            throw new IllegalArgumentException();
        }
        File out = new File(args[1]);
        Reader<Character> reader = new ContentReader(in);
        Writer writer = new ContentWriter(out);
        String content = reader.read(c -> c < 0x80);
        writer.write(content);
    }
}
