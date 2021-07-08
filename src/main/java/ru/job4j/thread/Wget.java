package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Wget implements Runnable {
    private final String url;
    private final int speed;
    private final String outputFile;
    private static final int BUFF_SIZE = 1024;

    public Wget(String url, int speed, String outputFile) {
        this.url = url;
        this.speed = speed;
        this.outputFile = outputFile;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream out = new FileOutputStream(outputFile)) {
            byte[] buff = new byte[BUFF_SIZE];
            long start = System.nanoTime();
            int read = in.read(buff, 0, BUFF_SIZE);
            while (read != -1) {
                long elapsed = System.nanoTime() - start;
                long latency = speed - TimeUnit.MILLISECONDS.convert(elapsed, TimeUnit.NANOSECONDS);
                if (latency > 0) {
                    Thread.sleep(latency);
                }
                out.write(buff, 0, read);
                start = System.nanoTime();
                read = in.read(buff, 0, BUFF_SIZE);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args[0] == null || args[1] == null || args[2] == null) {
            throw new IllegalArgumentException();
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String outputFile = args[2];
        Thread wget = new Thread(new Wget(url, speed, outputFile));
        wget.start();
        wget.join();
    }
}